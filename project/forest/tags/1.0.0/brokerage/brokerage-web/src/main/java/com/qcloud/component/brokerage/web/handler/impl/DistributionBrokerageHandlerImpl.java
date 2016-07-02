package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.component.brokerage.service.DistributionDataPoolTypeService;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.web.handler.DistributionBrokerageHandler;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.key.TypeEnum.DistributionBrokerageStateType;
import com.qcloud.component.brokerage.web.vo.DistributionBrokerageVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionBrokerageVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.IntKeyValue;

@Component
public class DistributionBrokerageHandlerImpl implements DistributionBrokerageHandler {

    @Autowired
    private DistributionDataPoolTypeService distributionDataPoolTypeService;

    @Autowired
    private DistributionGradeService        distributionGradeService;

    @Autowired
    private PersonalcenterClient            personalcenterClient;

    @Override
    public List<DistributionBrokerageVO> toVOList(List<DistributionBrokerage> list) {

        List<DistributionBrokerageVO> voList = new ArrayList<DistributionBrokerageVO>();
        for (DistributionBrokerage distributionBrokerage : list) {
            voList.add(toVO(distributionBrokerage));
        }
        return voList;
    }

    @Override
    public DistributionBrokerageVO toVO(DistributionBrokerage distributionBrokerage) {

        String json = Json.toJson(distributionBrokerage);
        return Json.toObject(json, DistributionBrokerageVO.class, true);
    }

    @Override
    public List<AdminDistributionBrokerageVO> toVOList4Admin(List<DistributionBrokerage> list) {

        List<AdminDistributionBrokerageVO> voList = new ArrayList<AdminDistributionBrokerageVO>();
        for (DistributionBrokerage adminDistributionBrokerage : list) {
            voList.add(toVO4Admin(adminDistributionBrokerage));
        }
        return voList;
    }

    @Override
    public AdminDistributionBrokerageVO toVO4Admin(DistributionBrokerage distributionBrokerage) {

        String json = Json.toJson(distributionBrokerage);
        AdminDistributionBrokerageVO vo = Json.toObject(json, AdminDistributionBrokerageVO.class, true);
        DistributionGrade grade = distributionGradeService.get(vo.getGradeId());
        AssertUtil.assertNotNull(grade, "等级不存在.");
        vo.setGradeName(grade.getName());
        List<IntKeyValue> list = distributionDataPoolTypeService.listDataPoolType();
        for (IntKeyValue str : list) {
            if (str.getKey() == vo.getType()) {
                vo.setTypeStr(str.getValue());
            }
        }
        String stateStr = DistributionBrokerageStateType.NOT_PASS.getName();
        if (vo.getState() == DistributionBrokerageStateType.TO_AUDIT.getKey()) {
            stateStr = DistributionBrokerageStateType.TO_AUDIT.getName();
        } else if (vo.getState() == DistributionBrokerageStateType.PASS.getKey()) {
            stateStr = DistributionBrokerageStateType.PASS.getName();
        }
        vo.setStateStr(stateStr);
        //
        QUser quser = personalcenterClient.getUser(vo.getOwner());
        vo.setOwnerName(quser.getName());
        //
        List<String> routes = new ArrayList<String>();
        String[] route = vo.getRoute().split(";");
        for (String str : route) {
            QUser user = personalcenterClient.getUser(Long.valueOf(str));
            routes.add(user.getName());
        }
        vo.setRoutes(routes);
        return vo;
    }

    public static void main(String[] args) {

        
        
        
        
        
        
        String[] abc = { "1", "2", "3"};
        List<String> abcList = Arrays.asList(abc);
        String[] after = abcList.toArray(new String[] {});
        System.out.println(abcList.size());
        System.out.println(after.length);
    }
}
