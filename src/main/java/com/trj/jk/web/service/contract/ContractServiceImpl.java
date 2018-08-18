package com.trj.jk.web.service.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.trj.jk.web.domain.Attachment;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.mapper.AttachmentMapper;
import com.trj.jk.web.mapper.jdbc.JdbcDao;
import com.trj.jk.web.util.UtilConstant;

@Service("contractService")
public class ContractServiceImpl implements IContractService{

	@Autowired
	private  JdbcDao JdbcDao;
	
	@Autowired
	private  AttachmentMapper attachmentMapper;
	
	@Value("${app.upload.download}")
	private String downloadImgUrl;	
	
	@Override
	public List<Map<String, Object>> lookContract(Integer loanLimitId,Integer flag) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> dataList = JdbcDao.getContractByLoanLimitId(loanLimitId);
		if(dataList!=null && !dataList.isEmpty()){
			for(Map<String, Object> map:dataList){
				if(map.get("tplNo") != null && map.get("tplNo").equals("CT009")){
					continue;
				}
				if(UtilConstant.CONTRACT_LOOK_TPLNO.contains(String.valueOf(map.get("tplNo")))){//app端要展示的合同
					Map<String, Object> resultMap = new HashMap<String, Object>();
					if(map.get("signImgAttachId")!=null){
						Attachment attachment = attachmentMapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(map.get("signImgAttachId"))));
						if(attachment!=null){
							String contractName = String.valueOf(map.get("contractName"));
							if(flag == 1 && contractName != null && contractName.equals("融资居间服务协议")){
								resultMap.put("clickTitle", "点击查看"+"平台服务协议");
								resultMap.put("title","平台服务协议");
							}else{
								resultMap.put("clickTitle", "点击查看"+contractName);
								resultMap.put("title",contractName);
							}
							resultMap.put("url", downloadImgUrl+attachment.getAttachPath()+attachment.getSaveName());
						}
					}else {
						throw new CheckException(ErrorMessageConstant.ERR_NULL_CONTRACT_IMG_ATTACH);
					}
						
					result.add(resultMap);
				}
			}
		}else{
			throw new CheckException(ErrorMessageConstant.ERR_NULL_CONTRACT);
		}
		return result;
	}

	
}
