package com.trj.jk.web.service.impl;

import com.trj.jk.web.domain.BankBranch;
import com.trj.jk.web.domain.BankBranchCriteria;
import com.trj.jk.web.domain.City;
import com.trj.jk.web.domain.CityCriteria;
import com.trj.jk.web.mapper.BankBranchMapper;
import com.trj.jk.web.mapper.CityMapper;
import com.trj.jk.web.service.BankBranchService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xierongli on 17/9/30.
 */
@Service
public class BankBranchServiceImpl implements BankBranchService {

    @Autowired
    private BankBranchMapper bankBranchMapper;
    @Autowired
    private CityMapper cityMapper;


    @Override
    public List<BankBranch> queryByCityNameAndName(String cityName, String bankBranchName) {
        CityCriteria cityCriteria = new CityCriteria();
        cityCriteria.createCriteria().andNameEqualTo(cityName);
        List<City> cityList = cityMapper.selectByCriteria(cityCriteria);
        if(CollectionUtils.isNotEmpty(cityList)){
            BankBranchCriteria bankBranchCriteria = new BankBranchCriteria();
            bankBranchCriteria.createCriteria().andCityCodeEqualTo(cityList.get(0).getCode()).andNameEqualTo(bankBranchName);
            return bankBranchMapper.selectByCriteria(bankBranchCriteria);
        }
        return null;
    }
}
