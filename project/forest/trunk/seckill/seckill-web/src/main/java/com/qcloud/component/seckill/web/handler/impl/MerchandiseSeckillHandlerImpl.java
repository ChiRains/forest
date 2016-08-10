package com.qcloud.component.seckill.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.CommoditycenterClient;
import com.qcloud.component.goods.QUnifiedMerchandise;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.service.SeckillConfigService;
import com.qcloud.component.seckill.web.handler.MerchandiseSeckillHandler;
import com.qcloud.component.seckill.web.vo.MerchandiseSeckillVO;
import com.qcloud.component.seckill.web.vo.admin.AdminMerchandiseSeckillVO;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;

@Component
public class MerchandiseSeckillHandlerImpl implements MerchandiseSeckillHandler {

    @Autowired
    private CommoditycenterClient commoditycenterClient;

    @Autowired
    private FileSDKClient         fileSDKClient;

    @Autowired
    private SeckillConfigService  seckillConfigService;

    @Override
    public List<MerchandiseSeckillVO> toVOList(List<MerchandiseSeckill> list) {

        List<MerchandiseSeckillVO> voList = new ArrayList<MerchandiseSeckillVO>();
        for (MerchandiseSeckill merchandiseSeckill : list) {
            voList.add(toVO(merchandiseSeckill));
        }
        return voList;
    }

    @Override
    public MerchandiseSeckillVO toVO(MerchandiseSeckill merchandiseSeckill) {

        QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(merchandiseSeckill.getUnifiedMerchandiseId());
        AssertUtil.assertNotNull(unifiedMerchandise, "秒杀商品不存在" + merchandiseSeckill.getUnifiedMerchandiseId());
        MerchandiseSeckillVO vo = new MerchandiseSeckillVO();
        vo.setId(merchandiseSeckill.getUnifiedMerchandiseId());
        vo.setPrice(unifiedMerchandise.getPrice());
        vo.setSeckillPrice(unifiedMerchandise.getDiscount());
        vo.setStock(unifiedMerchandise.getStock());
        vo.setName(unifiedMerchandise.getName());
        vo.setImage(fileSDKClient.getFileServerUrl() + unifiedMerchandise.getImage());
        vo.setSalesVolume(merchandiseSeckill.getSalesVolume());
        if (merchandiseSeckill.getOriginalStock() > 0) {
            vo.setRate(merchandiseSeckill.getSalesVolume() * 100 / merchandiseSeckill.getOriginalStock());
        } else {
            vo.setRate(100);
        }
        vo.setKillName(seckillConfigService.get().getKillName());
        return vo;
    }

    @Override
    public List<AdminMerchandiseSeckillVO> toVOList4Admin(List<MerchandiseSeckill> list) {

        List<AdminMerchandiseSeckillVO> voList = new ArrayList<AdminMerchandiseSeckillVO>();
        for (MerchandiseSeckill adminMerchandiseSeckill : list) {
            voList.add(toVO4Admin(adminMerchandiseSeckill));
        }
        return voList;
    }

    @Override
    public AdminMerchandiseSeckillVO toVO4Admin(MerchandiseSeckill merchandiseSeckill) {

        String json = Json.toJson(merchandiseSeckill);
        return Json.toObject(json, AdminMerchandiseSeckillVO.class, true);
    }
}
