package com.qcloud.component.personalcenter.web.handler.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.personalcenter.web.handler.GradeHandler;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.web.vo.GradeVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminGradeVO;
@Component
public class GradeHandlerImpl implements GradeHandler {
    @Autowired
    private FileSDKClient fileSDKClient;
    
    @Override
    public List<GradeVO> toVOList(List<Grade> list) {
        List<GradeVO> voList = new ArrayList<GradeVO>();
        for (Grade grade : list) {
            voList.add(toVO(grade));
        }
        return voList;
    }

    @Override
    public GradeVO toVO(Grade grade) {
        String json = Json.toJson(grade);
        return Json.toObject(json, GradeVO.class, true);
    }

    @Override
    public List<AdminGradeVO> toVOList4Admin(List<Grade> list) {
        List<AdminGradeVO> voList = new ArrayList<AdminGradeVO>();
        for (Grade adminGrade : list) {
            voList.add(toVO4Admin(adminGrade));
        }
        return voList;
    }

    @Override
    public AdminGradeVO toVO4Admin(Grade grade) {
        String json = Json.toJson(grade);
        AdminGradeVO vo=Json.toObject(json, AdminGradeVO.class, true);
        vo.setImageUid(fileSDKClient.urlToUid(grade.getImage()));
        return vo;
    }

    @Override
    public List<AdminGradeVO> toVOList4Admin(List<Grade> list, long gradeId) {
        List<AdminGradeVO> voList = new ArrayList<AdminGradeVO>();
        for (Grade adminGrade : list) {
            AdminGradeVO vo = toVO4Admin(adminGrade);
            vo.setSelected(adminGrade.getId() == gradeId ? "selected" : "");
            voList.add(vo);
        }
        return voList;
    }
}
