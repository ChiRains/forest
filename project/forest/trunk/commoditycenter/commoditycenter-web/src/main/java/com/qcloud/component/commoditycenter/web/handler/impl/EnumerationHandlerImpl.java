package com.qcloud.component.commoditycenter.web.handler.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.commoditycenter.model.Enumeration;
import com.qcloud.component.commoditycenter.service.EnumerationService;
import com.qcloud.component.commoditycenter.web.handler.EnumerationHandler;
import com.qcloud.component.commoditycenter.web.vo.EnumerationVO;
import com.qcloud.component.commoditycenter.web.vo.admin.AdminEnumerationVO;
import com.qcloud.pirates.core.json.Json;
@Component
public class EnumerationHandlerImpl implements EnumerationHandler {
    @Autowired
    private EnumerationService enumerationService;

    @Override
    public List<EnumerationVO> toVOList(List<Enumeration> list) {
        List<EnumerationVO> voList = new ArrayList<EnumerationVO>();
        for (Enumeration enumeration : list) {
            voList.add(toVO(enumeration));
        }
        return voList;
    }

    @Override
    public EnumerationVO toVO(Enumeration enumeration) {
        String json = Json.toJson(enumeration);
        return Json.toObject(json, EnumerationVO.class, true);
    }

    @Override
    public List<AdminEnumerationVO> toVOList4Admin(List<Enumeration> list) {
        List<AdminEnumerationVO> voList = new ArrayList<AdminEnumerationVO>();
        for (Enumeration adminEnumeration : list) {
            voList.add(toVO4Admin(adminEnumeration));
        }
        return voList;
    }

    @Override
    public AdminEnumerationVO toVO4Admin(Enumeration enumeration) {
        String json = Json.toJson(enumeration);
        AdminEnumerationVO vo = Json.toObject(json, AdminEnumerationVO.class, true);
        List<Enumeration> list = enumerationService.listByName(enumeration.getName());
        StringBuffer sb = new StringBuffer();
        for (int index = 0; index < list.size(); index++) {
            sb.append(list.get(index).getValue());
            if (index != list.size() - 1) {
                sb.append(",");
            }
        }
        vo.setValue(sb.toString());
        return vo;
    }

    @Override
    public AdminEnumerationVO toVO4Admin(String name) {
        Enumeration enumeration = new Enumeration();
        enumeration.setName(name);
        return toVO4Admin(enumeration);
    }

    @Override
    public List<AdminEnumerationVO> toVOList4AdminWithoutSpec(List<Enumeration> list) {
        List<AdminEnumerationVO> voList = new ArrayList<AdminEnumerationVO>();
        for (Enumeration adminEnumeration : list) {
            voList.add(toVO4AdminWithoutSpec(adminEnumeration));
        }
        return voList;
    }

    @Override
    public AdminEnumerationVO toVO4AdminWithoutSpec(Enumeration enumeration) {

        String json = Json.toJson(enumeration);
        AdminEnumerationVO vo = Json.toObject(json, AdminEnumerationVO.class, true);
        return vo;
    }
}
