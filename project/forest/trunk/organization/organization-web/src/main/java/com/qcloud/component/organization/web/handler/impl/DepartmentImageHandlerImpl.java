package com.qcloud.component.organization.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.organization.model.DepartmentImage;
import com.qcloud.component.organization.web.handler.DepartmentImageHandler;
import com.qcloud.component.organization.web.vo.DepartmentImageVO;
import com.qcloud.component.organization.web.vo.admin.AdminDepartmentImageVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class DepartmentImageHandlerImpl implements DepartmentImageHandler {

    @Autowired
    private FileSDKClient fileSDKClient;

    @Override
    public List<DepartmentImageVO> toVOList(List<DepartmentImage> list) {

        List<DepartmentImageVO> voList = new ArrayList<DepartmentImageVO>();
        for (DepartmentImage departmentImage : list) {
            voList.add(toVO(departmentImage));
        }
        return voList;
    }

    @Override
    public DepartmentImageVO toVO(DepartmentImage departmentImage) {

        String json = Json.toJson(departmentImage);
        return Json.toObject(json, DepartmentImageVO.class, true);
    }

    @Override
    public List<AdminDepartmentImageVO> toVOList4Admin(List<DepartmentImage> list) {

        List<AdminDepartmentImageVO> voList = new ArrayList<AdminDepartmentImageVO>();
        for (DepartmentImage adminDepartmentImage : list) {
            voList.add(toVO4Admin(adminDepartmentImage));
        }
        return voList;
    }

    @Override
    public AdminDepartmentImageVO toVO4Admin(DepartmentImage departmentImage) {

        String json = Json.toJson(departmentImage);
        AdminDepartmentImageVO adminDepartmentImageVO = Json.toObject(json, AdminDepartmentImageVO.class, true);
        adminDepartmentImageVO.setImage(fileSDKClient.getFileServerUrl() + adminDepartmentImageVO.getImage());
        return adminDepartmentImageVO;
    }
}
