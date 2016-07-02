package com.qcloud.component.personalcenter.web.vo;


public class MyBankCardVO {

    // ID
    private long   id;

    // 银行
    private String bank;

    // 卡
    private String card;
    
    private String cardholder;
    
    private String mobile;
    

    public MyBankCardVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setBank(String bank) {

        this.bank = bank;
    }

    public String getBank() {

        return bank;
    }

    public void setCard(String card) {

        this.card = card;
    }

    public String getCard() {

        return card;
    }

    
    public String getCardholder() {
    
        return cardholder;
    }

    
    public void setCardholder(String cardholder) {
    
        this.cardholder = cardholder;
    }

    
    public String getMobile() {
    
        return mobile;
    }

    
    public void setMobile(String mobile) {
    
        this.mobile = mobile;
    }
}
