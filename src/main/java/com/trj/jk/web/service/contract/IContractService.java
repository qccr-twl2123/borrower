package com.trj.jk.web.service.contract;

import java.util.List;
import java.util.Map;

public interface IContractService {

	public List<Map<String, Object>> lookContract(Integer loanLimitId,Integer flag);
}
