package com.trj.jk.web.controller;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.trj.jk.web.domain.UserBasic;
import com.trj.jk.web.domain.entity.page.PageBean;
import com.trj.jk.web.service.IUserService;
import com.xiaoleilu.hutool.lang.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class BaseController {

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * getPageBounds(获取分页的信息)
     *
     * @param bean
     * @return PageBounds
     * @throws
     */
    public PageBounds getPageBounds(PageBean bean) {
        int page = 1;
        int limit = 10;
        boolean containsTotalCount = true;
        if (bean != null) {
            if (bean.getPage() != null) {
                page = bean.getPage();
            }
            if (bean.getLimit() != null) {
                limit = bean.getLimit();
            }
            if (bean.getContainsTotalCount() != null) {
                containsTotalCount = bean.getContainsTotalCount();
            }
        }

        return new PageBounds(page, limit, containsTotalCount);
    }

    /**
     * 从Oauth认证获取用户ID
     *
     * @return
     */
    public Integer getCurrentUid() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("授权认证用户：" + userName);
        UserBasic user = userService.getUserBasicByMobile(userName);
        return user != null ? user.getId() : null;
    }

    /**
     * 客户验证
     *
     * @param request
     * @return
     */
    protected Boolean checkOauthClient(HttpServletRequest request) {
        Enumeration headers = request.getHeaders("Authorization");
        String value;
        do {
            if (!headers.hasMoreElements()) {
                return false;
            }
            value = (String) headers.nextElement();
        } while (!value.toLowerCase().startsWith("Basic".toLowerCase()));

        String clientIdValue = value.substring("Basic".length()).trim();
        byte[] encodeBase64 = Base64.decode(clientIdValue);
        String client = new String(encodeBase64);
        String[] clientId = client.split(":");
        System.out.println("RESULT: " + clientId[0]);
        try {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId[0]);
            if (null != clientDetails && clientId.length == 2 && clientDetails.getClientSecret().equals(clientId[1])) {
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

}
