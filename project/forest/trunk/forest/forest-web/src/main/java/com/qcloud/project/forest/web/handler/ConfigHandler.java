package com.qcloud.project.forest.web.handler;

import java.util.List;
import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.web.vo.ConfigVO;
import com.qcloud.project.forest.web.vo.GiftCouponUseRuleVO;
import com.qcloud.project.forest.web.vo.admin.AdminConfigVO;

public interface ConfigHandler {

    List<ConfigVO> toVOList(List<Config> list);

    ConfigVO toVO(Config config);

    List<AdminConfigVO> toVOList4Admin(List<Config> list);

    AdminConfigVO toVO4Admin(Config config);

    GiftCouponUseRuleVO toGiftCouponUseRuleVO(String config);
}
