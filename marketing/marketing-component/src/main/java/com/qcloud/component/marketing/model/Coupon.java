package com.qcloud.component.marketing.model;

import java.util.Date;
import com.qcloud.component.marketing.QCoupon;

public class Coupon implements QCoupon{

    private long   id;

    // 开始时间
    private Date   startDate;

    // 结束时间
    private Date   endDate;

    // 有效时间
    private Date   validDate;

    // 总共领取张数/人
    private int    totalOfPerson;

    // 总共领取金额数/人
    private int    priceOfPerson;

    // 描述
    private String description;

    private int    enable;

    private String image;

    private int    interval;

    private Long   merchantId;
    
    private int type ;

    public Coupon() {

    }

    public Coupon(long id, Date startDate, Date endDate, Date validDate, int totalOfPerson, int priceOfPerson, String description, int enable) {

        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.validDate = validDate;
        this.totalOfPerson = totalOfPerson;
        this.priceOfPerson = priceOfPerson;
        this.description = description;
        this.enable = enable;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public Date getStartDate() {

        return startDate;
    }

    public void setEndDate(Date endDate) {

        this.endDate = endDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setValidDate(Date validDate) {

        this.validDate = validDate;
    }

    public Date getValidDate() {

        return validDate;
    }

    public void setTotalOfPerson(int totalOfPerson) {

        this.totalOfPerson = totalOfPerson;
    }

    public int getTotalOfPerson() {

        return totalOfPerson;
    }

    public void setPriceOfPerson(int priceOfPerson) {

        this.priceOfPerson = priceOfPerson;
    }

    public int getPriceOfPerson() {

        return priceOfPerson;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public void setEnable(int enable) {

        this.enable = enable;
    }

    public int getEnable() {

        return enable;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public int getInterval() {

        return interval;
    }

    public void setInterval(int interval) {

        this.interval = interval;
    }

    public Long getMerchantId() {

        return merchantId;
    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    
    public int getType() {
    
        return type;
    }

    
    public void setType(int type) {
    
        this.type = type;
    }
}
