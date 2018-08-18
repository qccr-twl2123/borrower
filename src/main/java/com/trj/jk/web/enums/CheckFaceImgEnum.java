package com.trj.jk.web.enums;

import com.alibaba.fastjson.JSON;
import com.trj.commons.result.Result;
import com.trj.commons.result.Results;
import com.trj.jk.web.model.response.CheckFaceImgInvokeRes;

import java.math.BigDecimal;

/**
 * 人脸识别结果枚举
 * Created by xierongli on 17/10/30.
 */
public enum CheckFaceImgEnum {
    SUCCESS(0,"成功"),
    ERROR_8464(8464,"拍摄头像照片模糊,请重试"),
    ERROR_65537(65537,"识别过程出现错误");

    private Integer code;
    private String description;

    CheckFaceImgEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     *  封装人脸识别接口返回结果
     * @param json 接口返回数据
     * @param idcardScore 正常值
     * @return
     */
    public static Result<Boolean> validate(String json, BigDecimal idcardScore){
        CheckFaceImgInvokeRes checkFaceImgInvokeRes = JSON.parseObject(json,CheckFaceImgInvokeRes.class);
        if(checkFaceImgInvokeRes != null){
            if(checkFaceImgInvokeRes.getCode().equals(SUCCESS.getCode()) && checkFaceImgInvokeRes.getScore().compareTo(idcardScore) <0){
               return Results.newFailedResult("联网核查失败");
            }
            for(CheckFaceImgEnum checkFaceImgEnum : CheckFaceImgEnum.values()){
                if(checkFaceImgInvokeRes.getCode().equals(SUCCESS.getCode())){continue;}
                if(checkFaceImgInvokeRes.getCode().equals(checkFaceImgEnum.getCode())){
                    return Results.newFailedResult(checkFaceImgEnum.getDescription());
                }
            }
        }
        return Results.newSuccessResult(true);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
