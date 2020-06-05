package com.spro.entity.email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spro.entity.sys.DictionaryEntries;

import java.util.Date;

/**
 * @description: 邮件实体类
 * @package_name: com.spro.entity.email
 * @data: 2020-5-22 10:59
 * @author: Sean
 * @version: V1.0
 */
public class Email {

    private Long id;                //邮件Id

    private Long slipId;           //单号id

    private String slipCode;       //单号Code

    private String slipType;       //单号类型

    private String type;           //邮件类型

    @JsonIgnore
    private Integer status;        //邮件状态

    private DictionaryEntries statusEntries;

    @JsonFormat(pattern = "yyyy-MM-DD hh:mm:ss",locale = "zh")
    private Date createTime;        //邮件生成时间

    @JsonFormat(pattern = "yyyy-MM-DD hh:mm:ss",locale = "zh")
    private Date sendTime;          //邮件发送时间

    private String subject;         //邮件主题

    private String body;            //邮件内容

    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private String addr;            //邮件发送地址

    private String cc;              //抄送人员

    private String bcc;             //邮件密送人员

    private String errorMsg;        //错误信息

    private Integer tryCount;       //重试次数

    private String remark;          //备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSlipId() {
        return slipId;
    }

    public void setSlipId(Long slipId) {
        this.slipId = slipId;
    }

    public String getSlipCode() {
        return slipCode;
    }

    public void setSlipCode(String slipCode) {
        this.slipCode = slipCode;
    }

    public String getSlipType() {
        return slipType;
    }

    public void setSlipType(String slipType) {
        this.slipType = slipType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DictionaryEntries getStatusEntries() {
        return statusEntries;
    }

    public void setStatusEntries(DictionaryEntries statusEntries) {
        this.statusEntries = statusEntries;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", slipId=" + slipId +
                ", slipCode='" + slipCode + '\'' +
                ", slipType='" + slipType + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", statusEntries=" + statusEntries +
                ", createTime=" + createTime +
                ", sendTime=" + sendTime +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", addr='" + addr + '\'' +
                ", cc='" + cc + '\'' +
                ", bcc='" + bcc + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", tryCount=" + tryCount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
