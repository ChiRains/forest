package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.web.handler.ConfigHandler;
import com.qcloud.project.forest.web.vo.ConfigVO;
import com.qcloud.project.forest.web.vo.GiftCouponUseRuleVO;
import com.qcloud.project.forest.web.vo.admin.AdminConfigVO;

@Component
public class ConfigHandlerImpl implements ConfigHandler {

    @Override
    public List<ConfigVO> toVOList(List<Config> list) {

        List<ConfigVO> voList = new ArrayList<ConfigVO>();
        for (Config config : list) {
            voList.add(toVO(config));
        }
        return voList;
    }

    @Override
    public ConfigVO toVO(Config config) {

        String json = Json.toJson(config);
        return Json.toObject(json, ConfigVO.class, true);
    }

    @Override
    public List<AdminConfigVO> toVOList4Admin(List<Config> list) {

        List<AdminConfigVO> voList = new ArrayList<AdminConfigVO>();
        for (Config adminConfig : list) {
            voList.add(toVO4Admin(adminConfig));
        }
        return voList;
    }

    @Override
    public AdminConfigVO toVO4Admin(Config config) {

        String json = Json.toJson(config);
        return Json.toObject(json, AdminConfigVO.class, true);
    }

    @Override
    public GiftCouponUseRuleVO toGiftCouponUseRuleVO(String config) {

        GiftCouponUseRuleVO vo = Json.toObject(config, GiftCouponUseRuleVO.class);
        return vo == null ? new GiftCouponUseRuleVO() : vo;
    }
}
