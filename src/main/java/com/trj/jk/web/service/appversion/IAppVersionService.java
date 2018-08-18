package com.trj.jk.web.service.appversion;

import java.util.Map;
import com.trj.jk.web.domain.AppVer;

/**
 * Created by maievshabu on 2017/7/24.
 */
public interface IAppVersionService {

    Map<String, Object> getAppVersionInfoByType(Integer type, String version);

    Integer hasForceVersion(Integer type, Integer currentAppVerId, Integer userAppVerId);

    AppVer getInfoByVersion(Integer type, String version);
}
