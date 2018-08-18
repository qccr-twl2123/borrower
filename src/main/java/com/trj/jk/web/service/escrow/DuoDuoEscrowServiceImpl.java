package com.trj.jk.web.service.escrow;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mybatis.repository.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.UserBasicCriteria;
import com.trj.jk.web.domain.UserEscrow;
import com.trj.jk.web.domain.UserEscrowCriteria;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.enums.DDRetCodeEnum;
import com.trj.jk.web.mapper.UserBasicMapper;
import com.trj.jk.web.mapper.UserEscrowMapper;
import com.trj.jk.web.model.dto.EscrowConfirmDTO;
import com.trj.jk.web.model.dto.EscrowOpenDTO;
import com.trj.jk.web.util.UtilConstant;
import com.trj.jk.web.validator.Assert;
import com.trj.jk.web.validator.ValidatorUtils;

/**
 * 多多理财银行存管服务实现服务类
 *
 * @author -ming-
 */
@Service
public class DuoDuoEscrowServiceImpl implements IEscrowService {

    @Value("${app.remote.ddlc.domain}")
    private String ddlcDomain = null;

    private static String DDLC_ESCROW_SERVICE = "/ddlc/service/ProjectScattered/register";

    @Autowired
    private UserBasicMapper userBasicMapper;

    @Autowired
    private UserEscrowMapper userEscrowMapper;

    @Override
    @Transactional
    public EscrowOpenDTO escrowOpen(Integer uid, byte clientType) {
        EscrowOpenDTO result = new EscrowOpenDTO();
        UserBasic userBasic = userCheck(uid);
        UserEscrowCriteria criteria = new UserEscrowCriteria();
        criteria.createCriteria().andUidEqualTo(uid);
        List<UserEscrow> userEscrows = userEscrowMapper.selectByCriteria(criteria);
        if (CollectionUtils.isEmpty(userEscrows)) {//未调用存管
            result.setEscrow(false);
            //生成用户存管数据
            generateUserEscrow(userBasic, uid, clientType);
        } else {
            UserEscrow userEscrow = userEscrows.get(0);
            if (userEscrow.getStatus() != 1) {//用户未存管成功
                result.setEscrow(false);
            } else {//用户已经存管
                result.setEscrow(true);
            }
        }
        //禁用存管开户操作
        result.setEscrow(true);
        if (!result.isEscrow()) {//未存管
            result.setDdUrl(getDdUrl(userBasic));
        }
        return result;
    }

    /**
     * 用户信息检测
     *
     * @param uid
     */
    private UserBasic userCheck(Integer uid) {
        Assert.isNull(uid, ErrorMessageConstant.UN_LOGIN);
        UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
        Assert.isNull(userBasic, ErrorMessageConstant.ERR_NO_USEREXT_ERROR);
        Assert.isTrue(!userBasic.getIsIdentityAuth().equals(UtilConstant.AUTHENTICATION_STATUS_2), "用户未实名认证成功");
        return userBasic;
    }

    /**
     * 生成用户存管信息数据
     *
     * @param userBasic
     * @param uid
     * @param clientType
     */
    @Transactional
    public void generateUserEscrow(UserBasic userBasic, Integer uid, byte clientType) {
        UserEscrow newUserEscrow = new UserEscrow();
        newUserEscrow.setUid(uid);
        newUserEscrow.setClientType(clientType);
        newUserEscrow.setName(userBasic.getName());
        newUserEscrow.setMobile(userBasic.getMobile());
        newUserEscrow.setIdentityId(userBasic.getIdentityId());
        userEscrowMapper.insertSelective(newUserEscrow);
    }

    /**
     * 组装多多理财url
     *
     * @param userBasic
     * @return
     */
    private String getDdUrl(UserBasic userBasic) {
        StringBuilder ddUrl = new StringBuilder();
        ddUrl.append(ddlcDomain).append(DDLC_ESCROW_SERVICE).append("?")
                .append("realName=").append(userBasic.getName()).append("&")
                .append("mobile=").append(userBasic.getMobile()).append("&")
                .append("idCardNo=").append(userBasic.getIdentityId()).append("&")
                .append("channelSign=").append(userBasic.getTenant());
        return ddUrl.toString();
    }


    @Override
    public void escrowConfirm(EscrowConfirmDTO escrowConfirmDTO) {
        ValidatorUtils.validateEntity(escrowConfirmDTO);
        UserBasicCriteria criteria = new UserBasicCriteria();
        criteria.createCriteria().andMobileEqualTo(escrowConfirmDTO.getMobile()).andTenantEqualTo(UtilConstant.TENANT_JKWEB);
        List<UserBasic> userBasics = userBasicMapper.selectByCriteria(criteria);
        Assert.isTrue(CollectionUtils.isEmpty(userBasics), "未找到用户");
    	UserEscrowCriteria userEscrowCriteria = new UserEscrowCriteria();
		userEscrowCriteria.createCriteria().andUidEqualTo(userBasics.get(0).getId());
		List<UserEscrow> userEscrows = userEscrowMapper.selectByCriteria(userEscrowCriteria);
		Assert.isTrue(CollectionUtils.isEmpty(userEscrows), "未找到用户对应的存管信息");
		UserEscrow userEscrow = userEscrows.get(0);
        Assert.isNull(userEscrow, "未找到用户对应的存管信息");
        Assert.isTrue(!escrowConfirmDTO.getName().equals(userEscrow.getName()), "存管用户姓名不匹配");
        Assert.isTrue(!escrowConfirmDTO.getMobile().equals(userEscrow.getMobile()), "存管用户手机号码不匹配");
        Assert.isTrue(!escrowConfirmDTO.getIdentityId().equals(userEscrow.getIdentityId()), "存管用户身份证号码不匹配");
        if (escrowConfirmDTO.getCode().equals(DDRetCodeEnum.RCM_3304.getCode())
                || escrowConfirmDTO.getCode().equals(DDRetCodeEnum.RCM_3305.getCode())) {//开户成功
            userEscrow.setStatus((byte) 1);
        }
        userEscrow.setBankCardNo(escrowConfirmDTO.getBankCardNo());
        userEscrow.setRemark(StringUtils.isEmpty(userEscrow.getRemark()) ? escrowConfirmDTO.getMsg() : userEscrow.getRemark() + "&" + escrowConfirmDTO.getMsg());
        userEscrowMapper.updateByPrimaryKey(userEscrow);
    }

}
