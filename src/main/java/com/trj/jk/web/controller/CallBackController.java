package com.trj.jk.web.controller;

import com.google.gson.Gson;
import com.trj.jk.web.domain.entity.FuDataResponse;
import com.trj.jk.web.domain.entity.authentication.bi.GjjCallbackResult;
import com.trj.jk.web.service.IUserService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/callback")
public class CallBackController {

    private static final Logger LOG = LoggerFactory.getLogger(CallBackController.class);

    @Value("${app.service.path}")
    private String servicePath;

    @Autowired
    private IUserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/fudata", method = RequestMethod.POST)
    public Object fudataCallback(@RequestBody String notify) {
        try {
            LOG.info(String.format("富数回调:%s", notify));
            GjjCallbackResult gjjCallbackResult = new Gson().fromJson(notify, GjjCallbackResult.class);
            if (null != gjjCallbackResult && null != gjjCallbackResult.getTask_info()) {
                LOG.info(String.format("富数回调结果:%s", gjjCallbackResult.getTask_info().getStatus()));
                LOG.info(String.format("富数回调结果open_id:%s", gjjCallbackResult.getTask_info().getOpen_id()));
                //缓存记录，公积金回调仅能open_id识别uid
                String uid = stringRedisTemplate.opsForValue().get("gjj-get-uid::open_id::" + gjjCallbackResult.getTask_info().getOpen_id());
                LOG.info(String.format("富数回调读取缓存用户:%s", uid));
                if (gjjCallbackResult.getTask_info().getStatus().equals("CRAWLING") || gjjCallbackResult.getTask_info().getStatus().equals("COMPLETE") || gjjCallbackResult.getTask_info().getStatus().equals("EXTRACTING") || gjjCallbackResult.getTask_info().getStatus().equals("INTERACTEND")) {
                    if (null != uid && !"".equals(uid)) {
                        userService.updateUserHouseFundCertStatus(Integer.valueOf(uid), true);
                    }
                } else {
                    if (null != uid && !"".equals(uid)) {
                        userService.updateUserHouseFundCertStatus(Integer.valueOf(uid), false);
                    }
                }
            }
            String url = servicePath + "fus_gjj_cb/";
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(notify, "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse resp = httpClient.execute(httpPost);

            if (resp.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(resp.getEntity(), "utf-8").trim();
                LOG.info(String.format("博士盾:%s", result));
            } else {
                return FuDataResponse.error(String.format("请求错误:%s", resp.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return FuDataResponse.error("错误描述");
        }
        return FuDataResponse.success("正确描述");
    }

}
