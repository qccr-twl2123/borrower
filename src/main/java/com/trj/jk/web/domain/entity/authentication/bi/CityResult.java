package com.trj.jk.web.domain.entity.authentication.bi;

import com.trj.jk.web.domain.City;

import java.util.ArrayList;
import java.util.List;

public class CityResult {
    private String retmessage;
    private Integer retcode;
    private List<CityRetResult> retresult;

    public String getRetmessage() {
        return retmessage;
    }

    public void setRetmessage(String retmessage) {
        this.retmessage = retmessage;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public List<CityRetResult> getRetresult() {
        return retresult;
    }

    public void setRetresult(List<CityRetResult> retresult) {
        this.retresult = retresult;
    }

    @Override
    public String toString() {
        return "CityResult [retmessage=" + retmessage + ", retcode=" + retcode + ", retresult=" + retresult + "]";
    }

    public List<CityRetResult> queryCity(String searchKey) {
        if (searchKey == null || searchKey == "") {
            return this.getRetresult();
        }
        List<CityRetResult> list = new ArrayList<CityRetResult>();
        List<CityRetResult> results = new ArrayList<CityRetResult>();
        //不需要省，仅城市
        for (CityRetResult city : this.getRetresult()) {
            if (city.getSub().size() > 0) {
                list.addAll(city.getSub());
            }
        }
        for (CityRetResult cityLevel : list) {
            if (cityLevel.getName().indexOf(searchKey) != -1) {
                results.add(cityLevel);
            }
        }
        return results;
    }

    public List<CityRetResult> buildHotCity(List<City> cityList) {
        if (cityList == null) {
            return this.getRetresult();
        }
        List<CityRetResult> list = new ArrayList<CityRetResult>();
        List<CityRetResult> results = new ArrayList<CityRetResult>();
        for (CityRetResult city : this.getRetresult()) {
            if (city.getSub().size() > 0) {
                list.addAll(city.getSub());
            }
        }
        for (City city : cityList) {
            for (CityRetResult cityLevel : list) {
                if (city.getName().indexOf(cityLevel.getName()) != -1) {
                    results.add(cityLevel);
                }
            }
        }
        return results;
    }

}
