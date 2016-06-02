package com.qcloud.component.sellercenter.web.vo.admin;


public class AdminMerchantSortVO {
    private long id;
    
    //商家ID
    private long merchantId;
    
    //名称
    private String name;
    
    //LOGO
    private String logo;
    
    //排序
    private long sort;
    //
    
    private String logoUID;

    
   

    public AdminMerchantSortVO() {

    }


    public AdminMerchantSortVO(long id, long merchantId, String name, String logo, long sort, String logoUID) {

        this.id = id;
        this.merchantId = merchantId;
        this.name = name;
        this.logo = logo;
        this.sort = sort;
        this.logoUID = logoUID;
    }


    public long getId() {
    
        return id;
    }

    
    public void setId(long id) {
    
        this.id = id;
    }

    
    public long getMerchantId() {
    
        return merchantId;
    }

    
    public void setMerchantId(long merchantId) {
    
        this.merchantId = merchantId;
    }

    
    public String getName() {
    
        return name;
    }

    
    public void setName(String name) {
    
        this.name = name;
    }

    
    public String getLogo() {
    
        return logo;
    }

    
    public void setLogo(String logo) {
    
        this.logo = logo;
    }

    
    public long getSort() {
    
        return sort;
    }

    
    public void setSort(long sort) {
    
        this.sort = sort;
    }


    
    public String getLogoUID() {
    
        return logoUID;
    }


    
    public void setLogoUID(String logoUID) {
    
        this.logoUID = logoUID;
    }
}
