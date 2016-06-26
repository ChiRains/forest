package com.qcloud.component.goods.web.vo.admin;
import java.util.Date;
import java.math.BigDecimal;
public class AdminClassifyAttributeVO {
    private long   id;
    // 分类定义
    private long   classifyId;
    // 属性定义
    private long   attributeId;
    //排序
    private int sort;
    
    private int isParent;

    private long parentId;
    
    private String classify;
    
    private String path;
    
    private String attributeStr;
    
    private String checked;

    public AdminClassifyAttributeVO() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setClassifyId(long classifyId) {
        this.classifyId = classifyId;
    }

    public long getClassifyId() {
        return classifyId;
    }

    public void setAttributeId(long attributeId) {
        this.attributeId = attributeId;
    }

    public long getAttributeId() {
        return attributeId;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getAttributeStr() {
        return attributeStr;
    }

    public void setAttributeStr(String attributeStr) {
        this.attributeStr = attributeStr;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    
    public int getSort() {
    
        return sort;
    }

    
    public void setSort(int sort) {
    
        this.sort = sort;
    }

    
    public int getIsParent() {
    
        return isParent;
    }

    
    public void setIsParent(int isParent) {
    
        this.isParent = isParent;
    }
}
