package com.trj.jk.web.service.appversion;

import com.trj.jk.web.domain.AppVer;
import com.trj.jk.web.domain.AppVerCriteria;
import com.trj.jk.web.mapper.AppVerMapper;
import com.trj.jk.web.mapper.jdbc.JdbcDao;
import com.trj.jk.web.validator.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by maievshabu on 2017/7/24.
 */
@Service("AppVersionService")
public class AppVersionServiceImpl implements IAppVersionService{

    @Autowired
    private AppVerMapper appVerMapper;

    public Map<String, Object> getAppVersionInfoByType(Integer type, String version){

        Map<String, Object> map = new HashMap<String, Object>();
        AppVerCriteria appVerCriteria = new AppVerCriteria();
        appVerCriteria.setOrderByClause("id desc");
        appVerCriteria.createCriteria().andTypeEqualTo(new Byte(type.toString())).andAppIdEqualTo(2);

        List<AppVer> appVerList = appVerMapper.selectByCriteria(appVerCriteria);
        appVerMapper.countByCriteria(appVerCriteria);
        Assert.isTrue(CollectionUtils.isEmpty(appVerList),"手机类型的版本数据为空");

        AppVer appVer = appVerList.get(0);
        AppVer userAppVer = getInfoByVersion(type, version);

        map.put("isForce", appVer.getIsForce().toString().equals("1") ? appVer.getIsForce() : hasForceVersion(type, appVer.getId(), userAppVer.getId()));
        map.put("isUpdate", version.equals(appVer.getVerion()) ? 0 : 1);
        map.put("versionNumber", appVer.getVerion());
        map.put("versionInfo", appVer.getIntro());
        map.put("url", appVer.getUrl());
        map.put("createTime", appVer.getCreateTime());

        return map;
    }

    public AppVer getInfoByVersion(Integer type, String version){
        AppVerCriteria appVerCriteria = new AppVerCriteria();
        appVerCriteria.createCriteria().andVerionEqualTo(version)
                .andTypeEqualTo(new Byte(type.toString()))
                .andAppIdEqualTo(2);

        List<AppVer> appVerList = appVerMapper.selectByCriteria(appVerCriteria);
        Assert.isTrue(CollectionUtils.isEmpty(appVerList),"用户版本数据为空");
        return  appVerList.get(0);
    }

    public Integer hasForceVersion(Integer type, Integer currentAppVerId, Integer userAppVerId){

        AppVerCriteria appVerCriteria = new AppVerCriteria();
        AppVerCriteria.Criteria criteria = appVerCriteria.createCriteria();
        criteria.andAppIdEqualTo(2);
        criteria.andTypeEqualTo(new Byte(type.toString()));
        criteria.andIdGreaterThan(userAppVerId);
        criteria.andIdLessThan(currentAppVerId);
        criteria.andIsForceEqualTo(new Byte("1"));

        Long count = appVerMapper.countByCriteria(appVerCriteria);

        if (null == count || 0 == count){
            return 0;
        }

        return 1;
    }
}
