package com.lql.db.mybatis.beans.jspang;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private BigDecimal id;

    private String objid;

    private String objname;

    private Date crtTime;

    private Date updTime;

    private String account;

    private String password;

    private String crtPsn;

    private String updPsn;

    private BigDecimal isDelete;

    private Long phone;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid == null ? null : objid.trim();
    }

    public String getObjname() {
        return objname;
    }

    public void setObjname(String objname) {
        this.objname = objname == null ? null : objname.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCrtPsn() {
        return crtPsn;
    }

    public void setCrtPsn(String crtPsn) {
        this.crtPsn = crtPsn == null ? null : crtPsn.trim();
    }

    public String getUpdPsn() {
        return updPsn;
    }

    public void setUpdPsn(String updPsn) {
        this.updPsn = updPsn == null ? null : updPsn.trim();
    }

    public BigDecimal getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(BigDecimal isDelete) {
        this.isDelete = isDelete;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}