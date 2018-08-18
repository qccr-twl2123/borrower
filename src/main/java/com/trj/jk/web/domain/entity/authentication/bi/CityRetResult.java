package com.trj.jk.web.domain.entity.authentication.bi;

import java.util.List;

public class CityRetResult {

    private String social_insurance;
    private Integer status;
    private String code;
    private String fullcode;
    private String name;
    private Integer level;
    private String id;

    private List<CityRetResult> sub;

    public String getSocial_insurance() {
        return social_insurance;
    }

    public void setSocial_insurance(String social_insurance) {
        this.social_insurance = social_insurance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullcode() {
        return fullcode;
    }

    public void setFullcode(String fullcode) {
        this.fullcode = fullcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<CityRetResult> getSub() {
        return sub;
    }

    public void setSub(List<CityRetResult> sub) {
        this.sub = sub;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CityRetResult [" +
                "social_insurance=" + social_insurance + "," +
                " status=" + status + "" +
                " code=" + code + "" +
                " fullcode=" + fullcode + "" +
                " name=" + name + "" +
                " level=" + level + "" +
                "]";
    }
}
