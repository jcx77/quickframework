package com.framework.api.sys.user.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "ACCOUNT_")
    private String account;

    @Column(name = "PASSWORD_")
    private String password;

    @Column(name = "NAME_")
    private String name;

    @Column(name = "XB")
    private String xb;

    @Column(name = "WHCD")
    private String whcd;

    @Column(name = "ZYWHCD")
    private String zywhcd;

    @Column(name = "ZYJSZW")
    private String zyjszw;

    @Column(name = "ZJLX")
    private String zjlx;

    @Column(name = "ZJHM")
    private String zjhm;

    @Column(name = "SJHM")
    private String sjhm;

    @Column(name = "CSRQ")
    private Date csrq;

    @Column(name = "LOCKED_")
    private String locked;

    @Column(name = "ORGAN_ID")
    private String organId;

    @Column(name = "DATAID")
    private String dataid;

    @Column(name = "RYJB")
    private String ryjb;

    @Column(name = "QZQX")
    private String qzqx;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    @Column(name = "DELETE_FLAG")
    private String deleteFlag;
    @Transient
    private String[] organIds;

    @Transient
    private String[] roleIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }

    public String getZywhcd() {
        return zywhcd;
    }

    public void setZywhcd(String zywhcd) {
        this.zywhcd = zywhcd;
    }

    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public Date getCsrq() {
        return csrq;
    }

    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getRyjb() {
        return ryjb;
    }

    public void setRyjb(String ryjb) {
        this.ryjb = ryjb;
    }

    public String getQzqx() {
        return qzqx;
    }

    public void setQzqx(String qzqx) {
        this.qzqx = qzqx;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String[] getOrganIds() {
        return organIds;
    }

    public void setOrganIds(String[] organIds) {
        this.organIds = organIds;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
}