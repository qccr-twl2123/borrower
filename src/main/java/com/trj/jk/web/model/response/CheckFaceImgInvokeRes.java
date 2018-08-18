package com.trj.jk.web.model.response;

import java.math.BigDecimal;

/**
 * 人脸识别调用返回模型
 * Created by xierongli on 17/10/30.
 */
public class CheckFaceImgInvokeRes {

     private Integer code;
     private Integer flowId;
     private String msg;
     private BigDecimal score;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
