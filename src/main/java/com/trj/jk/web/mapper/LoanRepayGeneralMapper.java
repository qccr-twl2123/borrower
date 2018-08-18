package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.LoanRepayGeneral;
import com.trj.jk.web.domain.LoanRepayGeneralCriteria;
import com.trj.jk.web.model.response.MyLoanInfoRes;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface LoanRepayGeneralMapper extends BaseMapper<LoanRepayGeneral, LoanRepayGeneralCriteria, Integer> {


    MyLoanInfoRes getMyLoanInfo(@Param("uid") Integer uid);

    BigDecimal getPenaltyInterestAmount(@Param("uid") Integer uid);
}