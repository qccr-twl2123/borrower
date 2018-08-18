package com.trj.jk.web.service;

import java.util.List;

import com.trj.jk.web.domain.Code;

public interface ICodeService {
	List<Code> getCodeByKey(String key);
	
	
	Code getCodeByKeyAndNo(String key, String no);
}


