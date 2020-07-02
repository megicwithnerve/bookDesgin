package com.magician.book.pojo;

import com.magician.book.utils.RechargeEnum;

import java.util.Date;

public class RechargeRecordEntity {

    private Integer rechargerecordId;

    private Reader reader;

    private Integer ticketNumber;

    private Integer discount;

    private Integer price;

    private Integer status;

    private RechargeEnum statusEnum;

    private String outTradeNo;

    private String tradeNo;

    private Date createdtime;

    public RechargeRecordEntity() {
    }

//    public RechargeRecordEntity(Rechargerecord rechargerecord) {
//        this.rechargerecordId = rechargerecord.getRechargerecordId();
//        this.createdtime = rechargerecord.getCreatedtime();
//        this.readerId = rechargerecord.getReaderId();
//        this.ticketNumber = rechargerecord.getTicketNumber();
//        this.discount = rechargerecord.getDiscount();
//        this.price = rechargerecord.getPrice();
//        this.outTradeNo = rechargerecord.getOutTradeNo();
//        this.tradeNo = rechargerecord.getTradeNo();
//        this.status = RechargeEnum.getRechargeEnum(rechargerecord.getStatus());
//    }

    public Integer getRechargerecordId() {
        return rechargerecordId;
    }

    public void setRechargerecordId(Integer rechargerecordId) {
        this.rechargerecordId = rechargerecordId;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RechargeEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(RechargeEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }
}
