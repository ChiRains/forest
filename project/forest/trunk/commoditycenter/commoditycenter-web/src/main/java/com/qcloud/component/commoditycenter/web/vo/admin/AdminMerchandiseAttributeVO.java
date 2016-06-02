package com.qcloud.component.commoditycenter.web.vo.admin;
import java.util.ArrayList;
import java.util.List;
import com.qcloud.component.publicdata.KeyValueVO;
public class AdminMerchandiseAttributeVO {
    private long             id;
    private long             attributeId;
    private String           name;
    private long             merchandiseId;
    // 值
    private String           value;
    private List<KeyValueVO> list = new ArrayList<KeyValueVO>();

    public AdminMerchandiseAttributeVO() {
    }
 
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setAttributeId(long attributeId) {
        this.attributeId = attributeId;
    }

    public long getAttributeId() {
        return attributeId;
    }

    public void setMerchandiseId(long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public long getMerchandiseId() {
        return merchandiseId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<KeyValueVO> getList() {
        return list;
    }

    public void setList(List<KeyValueVO> list) {
        this.list = list;
    }       
}
