package com.trj.jk.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trj.jk.web.domain.Code;
import com.trj.jk.web.domain.CodeCriteria;
import com.trj.jk.web.mapper.CodeMapper;
import com.trj.jk.web.service.ICodeService;

@Service
public class CodeServiceImpl implements ICodeService{
	
	@Autowired
	private CodeMapper codeMapper;
	
	public List<Code> getCodeByKey(String key){
		CodeCriteria criteria= new CodeCriteria();
		criteria.createCriteria().andCodeKeyEqualTo(key).andIsValidIsNull();
		List<Code> codeList=codeMapper.selectByCriteria(criteria);
		return codeList;
	}

	@Override
	public Code getCodeByKeyAndNo(String key, String no) {
		CodeCriteria criteria= new CodeCriteria();
		criteria.createCriteria().andCodeKeyEqualTo(key).andCodeNoEqualTo(no).andIsValidIsNull();
		List<Code> codeList=codeMapper.selectByCriteria(criteria);
		if(codeList!=null && codeList.size()>0){
			return codeList.get(0);
		}
		return null;
	}
	
	

}
