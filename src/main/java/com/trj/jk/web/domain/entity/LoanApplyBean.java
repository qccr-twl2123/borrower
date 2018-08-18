package com.trj.jk.web.domain.entity;

import com.trj.jk.web.domain.LoanApply;
import com.trj.jk.web.domain.UserContacts;

import java.util.List;

public class LoanApplyBean extends LoanApply {
    String productCode;
    List<UserContacts> contacts;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<UserContacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<UserContacts> contacts) {
        this.contacts = contacts;
    }
}
