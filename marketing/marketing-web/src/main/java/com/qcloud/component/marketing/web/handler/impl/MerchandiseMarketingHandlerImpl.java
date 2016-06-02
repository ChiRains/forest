package com.qcloud.component.marketing.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.model.MerchandiseItem;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.marketing.web.handler.MerchandiseMarketingHandler;
import com.qcloud.component.marketing.web.vo.MerchandiseMarketingVO;
import com.qcloud.component.marketing.web.vo.admin.AdminMerchandiseMarketingVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class MerchandiseMarketingHandlerImpl implements MerchandiseMarketingHandler{
    @Autowired
    private CommoditycenterClient commoditycenterClient;
    @Override
    public List<MerchandiseMarketingVO> toVOList(List<MerchandiseMarketing> list) {
        List<MerchandiseMarketingVO> voList = new ArrayList<MerchandiseMarketingVO>();
        for (MerchandiseMarketing merchandiseMarketing : list) {
            voList.add(toVO(merchandiseMarketing));
        }
        return voList;
    }

    @Override
    public MerchandiseMarketingVO toVO(MerchandiseMarketing merchandiseMarketing) {
        String json = Json.toJson(merchandiseMarketing);
        return Json.toObject(json, MerchandiseMarketingVO.class, true);
    }

    @Override
    public List<AdminMerchandiseMarketingVO> toVOList4Admin(List<MerchandiseMarketing> list) {
        List<AdminMerchandiseMarketingVO> voList = new ArrayList<AdminMerchandiseMarketingVO>();
        for (MerchandiseMarketing merchandiseMarketing : list) {
            AdminMerchandiseMarketingVO vo=new AdminMerchandiseMarketingVO();
            vo=toVO4Admin(merchandiseMarketing);
           /* MerchandiseItem items=commoditycenterClient.getMerchandiseItem(vo.getMerchandiseItemId());
            if(items!=null&&vo.getName()==null){
                vo.setName(items.getName());
            }*/
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public AdminMerchandiseMarketingVO toVO4Admin(MerchandiseMarketing merchandiseMarketing) {
        String json = Json.toJson(merchandiseMarketing);
        return Json.toObject(json, AdminMerchandiseMarketingVO.class, true);
    }
}
