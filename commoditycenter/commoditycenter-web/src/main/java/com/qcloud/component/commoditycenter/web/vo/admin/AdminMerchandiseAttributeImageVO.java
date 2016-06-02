package com.qcloud.component.commoditycenter.web.vo.admin;
import java.util.ArrayList;
import java.util.List;
public class AdminMerchandiseAttributeImageVO {
    // 商品ID
    private long                          merchandiseId;
    //
    private long                          attributeId;
    // 值
    private String                        value;
    // 图片
    private String                        images;
    //
    private List<AdminMerchandiseImageVO> list = new ArrayList<AdminMerchandiseImageVO>();

    public long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(long attributeId) {
        this.attributeId = attributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<AdminMerchandiseImageVO> getList() {
        return list;
    }

    public void setList(List<AdminMerchandiseImageVO> list) {
        this.list = list;
    }
}
