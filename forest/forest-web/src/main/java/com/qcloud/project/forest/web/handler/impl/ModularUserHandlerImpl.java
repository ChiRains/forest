package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.service.ModularService;
import com.qcloud.project.forest.web.handler.ModularUserHandler;
import com.qcloud.project.forest.web.vo.ModularUserVO;
import com.qcloud.project.forest.web.vo.admin.AdminModularUserVO;

@Component
public class ModularUserHandlerImpl implements ModularUserHandler {

    @Autowired
    private ModularService modularService;

    @Override
    public List<ModularUserVO> toVOList(List<ModularUser> list) {

        List<ModularUserVO> voList = new ArrayList<ModularUserVO>();
        for (ModularUser modularUser : list) {
            voList.add(toVO(modularUser));
        }
        Iterator<ModularUserVO> iter = voList.iterator();
        while (iter.hasNext()) {
            ModularUserVO s = iter.next();
            if (s.getEnable() == 0) {
                iter.remove();
            }
        }
        return voList;
    }

    @Override
    public ModularUserVO toVO(ModularUser modularUser) {

        String json = Json.toJson(modularUser);
        ModularUserVO modularUserVO = Json.toObject(json, ModularUserVO.class, true);
        Modular modular = modularService.get(modularUserVO.getModularId());
        modularUserVO.setEnable(modular.getEnable());
        modularUserVO.setName(modular.getName());
        modularUserVO.setCode(modular.getCode());
        return modularUserVO;
    }

    @Override
    public List<AdminModularUserVO> toVOList4Admin(List<ModularUser> list) {

        List<AdminModularUserVO> voList = new ArrayList<AdminModularUserVO>();
        for (ModularUser adminModularUser : list) {
            voList.add(toVO4Admin(adminModularUser));
        }
        return voList;
    }

    @Override
    public AdminModularUserVO toVO4Admin(ModularUser modularUser) {

        String json = Json.toJson(modularUser);
        return Json.toObject(json, AdminModularUserVO.class, true);
    }
}
