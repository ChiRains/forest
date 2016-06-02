package com.qcloud.component.personalcenter.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.key.TypeEnum.WithdrawCashStateType;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.component.personalcenter.web.handler.MyCommissionWithdrawCashHandler;
import com.qcloud.component.personalcenter.web.vo.MyCommissionWithdrawCashVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyCommissionWithdrawCashVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyCommissionWithdrawCashVOExport;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.DateUtil;

@Component
public class MyCommissionWithdrawCashHandlerImpl implements MyCommissionWithdrawCashHandler {

    @Autowired
    private UserService userService;

    @Override
    public List<MyCommissionWithdrawCashVO> toVOList(List<MyCommissionWithdrawCash> list) {

        List<MyCommissionWithdrawCashVO> voList = new ArrayList<MyCommissionWithdrawCashVO>();
        for (MyCommissionWithdrawCash myCommissionWithdrawCash : list) {
            voList.add(toVO(myCommissionWithdrawCash));
        }
        return voList;
    }

    @Override
    public MyCommissionWithdrawCashVO toVO(MyCommissionWithdrawCash myCommissionWithdrawCash) {

        String json = Json.toJson(myCommissionWithdrawCash);
        MyCommissionWithdrawCashVO vo = Json.toObject(json, MyCommissionWithdrawCashVO.class, true);
        if (myCommissionWithdrawCash.getCompleteTime() != null) {
            vo.setCompleteTimeStr(DateUtil.date2String(myCommissionWithdrawCash.getCompleteTime()));
        }
        vo.setTimeStr(DateUtil.date2String(myCommissionWithdrawCash.getTime()));
        return vo;
    }

    @Override
    public List<AdminMyCommissionWithdrawCashVO> toVOList4Admin(List<MyCommissionWithdrawCash> list) {

        List<AdminMyCommissionWithdrawCashVO> voList = new ArrayList<AdminMyCommissionWithdrawCashVO>();
        for (MyCommissionWithdrawCash adminMyCommissionWithdrawCash : list) {
            voList.add(toVO4Admin(adminMyCommissionWithdrawCash));
        }
        return voList;
    }

    @Override
    public AdminMyCommissionWithdrawCashVO toVO4Admin(MyCommissionWithdrawCash myCommissionWithdrawCash) {

        String json = Json.toJson(myCommissionWithdrawCash);
        AdminMyCommissionWithdrawCashVO adminMyCommissionWithdrawCashVO = Json.toObject(json, AdminMyCommissionWithdrawCashVO.class, true);
        for (WithdrawCashStateType withdrawCashStateType : WithdrawCashStateType.values()) {
            if (adminMyCommissionWithdrawCashVO.getState() == withdrawCashStateType.getKey()) {
                adminMyCommissionWithdrawCashVO.setStateName(withdrawCashStateType.getName());
            }
        }
        User user = userService.get(adminMyCommissionWithdrawCashVO.getUserId());
        if (user != null) {
            adminMyCommissionWithdrawCashVO.setAccount(user.getMobile());
            adminMyCommissionWithdrawCashVO.setUsername(user.getNickname());
        }
        return adminMyCommissionWithdrawCashVO;
    }

    @Override
    public List<AdminMyCommissionWithdrawCashVOExport> toVOList4Export(List<MyCommissionWithdrawCash> list) {

        List<AdminMyCommissionWithdrawCashVOExport> voList = new ArrayList<AdminMyCommissionWithdrawCashVOExport>();
        for (MyCommissionWithdrawCash myCommissionWithdrawCash : list) {
            voList.add(toVO4Export(myCommissionWithdrawCash));
        }
        return voList;
    }

    public AdminMyCommissionWithdrawCashVOExport toVO4Export(MyCommissionWithdrawCash myCommissionWithdrawCash) {

        String json = Json.toJson(myCommissionWithdrawCash);
        AdminMyCommissionWithdrawCashVOExport vo1 = Json.toObject(json, AdminMyCommissionWithdrawCashVOExport.class, true);
        AdminMyCommissionWithdrawCashVOExport vo = new AdminMyCommissionWithdrawCashVOExport();
        vo.setBank(vo1.getBank());
        vo.setCard(vo1.getCard());
        vo.setCardholder(vo1.getCardholder());
        vo.setCommissionCash(vo1.getCommissionCash());
        vo.setUserId(String.valueOf(vo1.getUserId()) + "  ");
        User user = userService.get(myCommissionWithdrawCash.getUserId());
        if (user != null) {
            if (user.getMobile() == null || user.getMobile() == "") {
                vo.setAccount(" ");
            } else if (user.getMobile() != null || user.getMobile() != "") {
                vo.setAccount(user.getMobile() + "  ");
            }
            if (user.getMobile() == null || user.getNickname() == "") {
                vo.setUsername(" ");
            } else if (user.getMobile() != null || user.getNickname() != "") {
                vo.setUsername(user.getNickname() + "  ");
            }
        }
        for (WithdrawCashStateType withdrawCashStateType : WithdrawCashStateType.values()) {
            if (vo.getState() == withdrawCashStateType.getKey()) {
                vo.setStateName(withdrawCashStateType.getName());
            }
        }
        return vo;
    }
}
