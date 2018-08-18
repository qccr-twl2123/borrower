package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.BankBranch;
import com.trj.jk.web.domain.BankBranchCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BankBranchMapper extends BaseMapper<BankBranch, BankBranchCriteria, String> {
}