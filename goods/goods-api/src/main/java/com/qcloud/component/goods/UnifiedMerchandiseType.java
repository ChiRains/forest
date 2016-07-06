package com.qcloud.component.goods;

import java.util.List;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.exception.PiratesRuntimeException;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

public class UnifiedMerchandiseType {

    public static UnifiedMerchandiseType SINGLE      = new UnifiedMerchandiseType(1, "单一商品");

    public static UnifiedMerchandiseType COMBINATION = new UnifiedMerchandiseType(2, "组合商品");

    private int                          key;

    private String                       name;

    private UnifiedMerchandiseType(int key, String name) {

        super();
        this.key = key;
        this.name = name;
    }

    public int getKey() {

        return key;
    }

    public String getName() {

        return name;
    }
    public static class Factory {

        public static UnifiedMerchandiseType get(int type) {

            if (type == 1) {
                return SINGLE;
            } else if (type == 2) {
                return COMBINATION;
            } else {
                Xml xml = XmlFactory.get("Goods-UnifiedMerchandise-Type");
                AssertUtil.assertNotNull(xml, "统一商品类型尚未配置." + type);
                List<XmlItem> list = xml.getItemList();
                for (XmlItem xmlItem : list) {
                    if (String.valueOf(type).equals(StringUtil.nullToEmpty(xmlItem.getAttrMap().get("key")).trim())) {
                        return new UnifiedMerchandiseType(type, StringUtil.nullToEmpty(xmlItem.getAttrMap().get("name")).trim());
                    }
                }
                throw new PiratesRuntimeException("统一商品类型尚未配置." + type);
            }
        }
    }
}
