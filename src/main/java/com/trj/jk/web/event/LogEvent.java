package com.trj.jk.web.event;

/**
 * 日志事件
 * Created by xierongli on 17/6/20.
 */
public class LogEvent {


    /**
     * 耗时
     */
    private Integer cost;
    /**
     * 类and方法
     */
    private String classExpression;
    /**
     * 参数
     */
    private String params;
    /**
     * 异常信息
     */
    private String exception;
    /**
     * IP地址
     */
    private String ip;
    /**
     * url
     */
    private String url;

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getClassExpression() {
        return classExpression;
    }

    public void setClassExpression(String classExpression) {
        this.classExpression = classExpression;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
