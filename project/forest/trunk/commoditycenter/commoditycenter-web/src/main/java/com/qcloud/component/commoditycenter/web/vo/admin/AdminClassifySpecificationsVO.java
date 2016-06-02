package com.qcloud.component.commoditycenter.web.vo.admin;

public class AdminClassifySpecificationsVO {

    private long   id;

    private long   classifyId;

    // 属性定义
    private long   attributeId;

    private String attributeStr;
    
    //排序
    private int sort;

    // 纳入价格计算
    private int    uploadImage;

    private String uploadImageStr;

    private String uploadImageChecked;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getAttributeId() {

        return attributeId;
    }

    public void setAttributeId(long attributeId) {

        this.attributeId = attributeId;
    }

    public String getAttributeStr() {

        return attributeStr;
    }

    public void setAttributeStr(String attributeStr) {

        this.attributeStr = attributeStr;
    }

    public long getClassifyId() {

        return classifyId;
    }

    public void setClassifyId(long classifyId) {

        this.classifyId = classifyId;
    }

    public int getUploadImage() {

        return uploadImage;
    }

    public void setUploadImage(int uploadImage) {

        this.uploadImage = uploadImage;
    }

    public String getUploadImageStr() {

        return uploadImageStr;
    }

    public void setUploadImageStr(String uploadImageStr) {

        this.uploadImageStr = uploadImageStr;
    }

    public String getUploadImageChecked() {

        return uploadImageChecked;
    }

    public void setUploadImageChecked(String uploadImageChecked) {

        this.uploadImageChecked = uploadImageChecked;
    }

    
    public int getSort() {
    
        return sort;
    }

    
    public void setSort(int sort) {
    
        this.sort = sort;
    }
}
