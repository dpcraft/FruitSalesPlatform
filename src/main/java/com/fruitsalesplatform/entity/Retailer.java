package com.fruitsalesplatform.entity;

/**
 * @author dpcraft
 * @date 2018/12/31
 * @time 12:44
 */
public class Retailer {
    private String retailerId;
    private String name;
    private String telphone;
    private String address;
    private Integer status;
    private String createTime;

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Retailer{" +
                "retailerId='" + retailerId + '\'' +
                ", name='" + name + '\'' +
                ", telphone='" + telphone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
