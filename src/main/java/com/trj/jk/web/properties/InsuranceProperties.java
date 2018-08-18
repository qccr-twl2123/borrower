package com.trj.jk.web.properties;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xierongli
 * @version $$Id: trj-jk-web, v 0.1 2018/5/4 上午10:02 mark1xie Exp $$
 */
@Configuration
@ConfigurationProperties(prefix = "insurance")
public class InsuranceProperties {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String productCode;
    @NotBlank
    private String policyReqUrl;
    @NotBlank
    private String publicKey;
    @NotBlank
    private String privateKey;


    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPolicyReqUrl() {
        return policyReqUrl;
    }

    public void setPolicyReqUrl(String policyReqUrl) {
        this.policyReqUrl = policyReqUrl;
    }
}
