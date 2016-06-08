package com.qcloud.project.forest.web.vo;

public class OrderGiftCouponVO {

    private long   id;

    private long   giftCouponId;

    private String validDateStr;

    private String inValidDateStr;

    private String name;

    private String image;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getGiftCouponId() {

        return giftCouponId;
    }

    public void setGiftCouponId(long giftCouponId) {

        this.giftCouponId = giftCouponId;
    }

    public String getValidDateStr() {

        return validDateStr;
    }

    public void setValidDateStr(String validDateStr) {

        this.validDateStr = validDateStr;
    }

    public String getInValidDateStr() {

        return inValidDateStr;
    }

    public void setInValidDateStr(String inValidDateStr) {

        this.inValidDateStr = inValidDateStr;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }
}
