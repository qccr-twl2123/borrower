package com.trj.jk.web.controller.position;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.trj.commons.result.Result;
import com.trj.jk.web.domain.MobileLocationInterval;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.entity.ResultMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.service.position.IPositionService;
import com.trj.jk.web.util.SessionUtil;



/**
 * 手机定位controller类
 * @author l46001
 *
 */
@RestController
@RequestMapping(value={"/position"})
public class PositionController {
	private static final Logger LOG = Logger.getLogger(PositionController.class);
	
	@Resource
	private IPositionService positionService;

	/**
	 * 定位信息提交接口
	 * @param applyId
	 * @param userExt
	 * @param type
	 * @return
	 */
	@RequestMapping(value={"/get"},method=RequestMethod.POST)
	@ResponseBody		
	public Result<Object> position(MobileLocationInterval mobileLocationInterval){
		final Result<Object> result = new Result<Object>();
		LOG.info("手机定位接口开始执行....");
		LOG.info(String.format("参数：mobileLocationInterval=%s",mobileLocationInterval.toString()));
		try {
			Integer uid = (Integer)SessionUtil.getUserLogonInfo();
			if(uid!=null){
				mobileLocationInterval.setUid(uid);
				positionService.position(mobileLocationInterval);
				result.setSuccess(true);
				result.setMessage(ResultMessageConstant.ADD_POSITION_SUCCESS);
				result.setData(mobileLocationInterval.getId());
			}else {
				result.setSuccess(false);
				result.setMessage(ErrorMessageConstant.ERR_PARAM_ERROR);
			}
		} catch(CheckException e){
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());				
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			result.setSuccess(false);
			result.setMessage(ErrorMessageConstant.ERR_OCCURS);	
		}
		LOG.info(String.format("手机定位接口返回：%s",result.toString()));
		return result;	
			
	}
	
	
}


