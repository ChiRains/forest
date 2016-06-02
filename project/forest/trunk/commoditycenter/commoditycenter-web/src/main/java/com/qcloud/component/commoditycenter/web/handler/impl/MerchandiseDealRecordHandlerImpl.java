package com.qcloud.component.commoditycenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.model.MerchandiseDealRecord;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseDealRecordHandler;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseDealRecordVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;

@Component
public class MerchandiseDealRecordHandlerImpl implements MerchandiseDealRecordHandler {

    @Autowired
    PersonalcenterClient personalcenterClient;

    @Override
    public List<MerchandiseDealRecordVO> toVOList(List<MerchandiseDealRecord> list) {

        List<MerchandiseDealRecordVO> voList = new ArrayList<MerchandiseDealRecordVO>();
        for (MerchandiseDealRecord merchandiseDealRecord : list) {
            voList.add(toVO(merchandiseDealRecord));
        }
        return voList;
    }

    @Override
    public MerchandiseDealRecordVO toVO(MerchandiseDealRecord merchandiseDealRecord) {

        String json = Json.toJson(merchandiseDealRecord);
        MerchandiseDealRecordVO vo = Json.toObject(json, MerchandiseDealRecordVO.class, true);
        QUser recordUser = personalcenterClient.getUser(merchandiseDealRecord.getUserId());
        AssertUtil.assertNotNull(recordUser, "用户不存在." + merchandiseDealRecord.getUserId());
        String name = recordUser.getMobile();
        if (name != null && name.length() > 7) {
            name = name.substring(0, 3) + "****" + name.substring(7, name.length());
        } else {
            name = recordUser.getName();
        }
        if (name == null) {
            name = "匿名用户";
        }
        vo.setUserName(name);
        vo.setGradeName(recordUser.getGrade().getName());
        vo.setTimeStr(DateUtil.date2String(merchandiseDealRecord.getTime(), "【yyyy.MM.dd HH:mm:ss】"));
        vo.setNumber(merchandiseDealRecord.getNumber());
        vo.setSpecifications(merchandiseDealRecord.getSpecifications());
        return vo;
    }
}
