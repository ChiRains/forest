package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.web.handler.UpgradeOrderHandler;
import com.qcloud.component.brokerage.web.vo.admin.AdminUpgradeOrderVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class UpgradeOrderHandlerImpl implements UpgradeOrderHandler {

    @Autowired
    private DistributionGradeService distributionGradeService;

    @Override
    public List<AdminUpgradeOrderVO> toVOList4Admin(List<UpgradeOrder> list) {

        List<AdminUpgradeOrderVO> voList = new ArrayList<AdminUpgradeOrderVO>();
        for (UpgradeOrder adminUpgradeOrder : list) {
            voList.add(toVO4Admin(adminUpgradeOrder));
        }
        return voList;
    }

    @Override
    public AdminUpgradeOrderVO toVO4Admin(UpgradeOrder upgradeOrder) {

        String json = Json.toJson(upgradeOrder);
        AdminUpgradeOrderVO vo = Json.toObject(json, AdminUpgradeOrderVO.class, true);
        DistributionGrade originalGrade = distributionGradeService.get(vo.getOriginalGradeId());
        DistributionGrade upgradeGrade = distributionGradeService.get(vo.getUpgradeGradeId());
        vo.setOriginalGrade(originalGrade.getName());
        vo.setUpgradeGrade(upgradeGrade.getName());
        return vo;
    }
}
