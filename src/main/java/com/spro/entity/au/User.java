package com.spro.entity.au;

import com.spro.entity.base.BaseEntity;

import java.util.Date;

/**
 * 用户信息类
 */
public class User extends BaseEntity {
    private Integer id;

    private String loginName;

    private String username;

    private String userMd5Pwd;

    private String userPwd;

    private String phone;

    private String email;

    private Date lastLoginTime;

    private String lastLoginIp;

    private Boolean isValid;

    private String remark;

    private Integer userPictureId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserMd5Pwd() {
        return userMd5Pwd;
    }

    public void setUserMd5Pwd(String userMd5Pwd) {
        this.userMd5Pwd = userMd5Pwd;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserPictureId() {
        return userPictureId;
    }

    public void setUserPictureId(Integer userPictureId) {
        this.userPictureId = userPictureId;
    }
}