package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.key.TypeEnum.ModularType;
import com.qcloud.project.forest.web.handler.ModularUserHandler;
import com.qcloud.project.forest.web.vo.ModularUserVO;
import com.qcloud.project.forest.web.vo.admin.AdminModularUserVO;

@Component
public class ModularUserHandlerImpl implements ModularUserHandler {

    @Override
    public List<ModularUserVO> toVOList(List<ModularUser> list) {

        List<ModularUserVO> voList = new ArrayList<ModularUserVO>();
        for (ModularUser modularUser : list) {
            voList.add(toVO(modularUser));
        }
        return voList;
    }

    @Override
    public ModularUserVO toVO(ModularUser modularUser) {

        String json = Json.toJson(modularUser);
        ModularUserVO modularUserVO = Json.toObject(json, ModularUserVO.class, true);
        for (ModularType modularType : ModularType.values()) {
            if (modularUserVO.getModularCode() == modularType.getKey()) {
                modularUserVO.setName(modularType.getName());
                modularUserVO.setCode(modularType.getKey());
            }
        }
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
