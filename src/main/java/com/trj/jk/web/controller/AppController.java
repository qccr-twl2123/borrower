package com.trj.jk.web.controller;

import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.service.appversion.IAppVersionService;
import com.trj.jk.web.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by maievshabu on 2017/7/24.
 */
@RestController
@RequestMapping("/app")
public class AppController extends BaseController{


    @Autowired
    private IAppVersionService appVersionService;

    @RequestMapping(value="/getVersion", method=RequestMethod.POST)
    public Result<Map<String, Object>> getVersion(String type, String version){
        logger.info("App获得最新版本号开始...type[{}]",type);
        logger.info("version --", version);
        Assert.isBlank(type,"type值不能为空");
        return Results.newSuccessResult(appVersionService.getAppVersionInfoByType(Integer.valueOf(type), version));
    }
}
