package com.qcloud.component.brokerage.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.web.handler.DistributionGradeHandler;
import com.qcloud.component.brokerage.web.vo.DistributionGradeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionGradeTreeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionGradeVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.core.json.Json;

@Component
public class DistributionGradeHandlerImpl implements DistributionGradeHandler {

    @Autowired
    private PublicdataClient         publicdataClient;

    @Autowired
    private DistributionGradeService distributionGradeService;

    @Override
    public List<DistributionGradeVO> toVOList(List<DistributionGrade> list) {

        List<DistributionGradeVO> voList = new ArrayList<DistributionGradeVO>();
        for (DistributionGrade distributionGrade : list) {
            voList.add(toVO(distributionGrade));
        }
        return voList;
    }

    @Override
    public DistributionGradeVO toVO(DistributionGrade distributionGrade) {

        String json = Json.toJson(distributionGrade);
        return Json.toObject(json, DistributionGradeVO.class, true);
    }

    @Override
    public List<AdminDistributionGradeVO> toVOList4Admin(List<DistributionGrade> list) {

        List<AdminDistributionGradeVO> voList = new ArrayList<AdminDistributionGradeVO>();
        for (DistributionGrade adminDistributionGrade : list) {
            voList.add(toVO4Admin(adminDistributionGrade));
        }
        return voList;
    }

    @Override
    public AdminDistributionGradeVO toVO4Admin(DistributionGrade distributionGrade) {

        String json = Json.toJson(distributionGrade);
        return Json.toObject(json, AdminDistributionGradeVO.class, true);
    }

    @Override
    public List<AdminDistributionGradeTreeVO> toTree() {

        List<DistributionGrade> list = distributionGradeService.listAll();
        List<AdminDistributionGradeTreeVO> treeList = new ArrayList<AdminDistributionGradeTreeVO>();
        for (DistributionGrade grade : list) {
            AdminDistributionGradeTreeVO treeVO = new AdminDistributionGradeTreeVO();
            Classify classify = publicdataClient.getClassify(grade.getClassifyId());
            if (classify.getParentId() == -1) {
                treeVO.setId(grade.getId());
                treeVO.setName(grade.getName());
                treeVO.setBsid(grade.getBsid());
                treeVO.setCash(grade.getCash());
                treeVO.setMonthLimit(grade.getMonthLimit());
                treeVO.setType(grade.getType());
                treeVO.setUserResource(grade.getUserResource());
                treeVO.setClassifyId(grade.getClassifyId());
                fillTree(treeVO, list);
                treeList.add(treeVO);
            }
        }
        return treeList;
    }

    private void fillTree(AdminDistributionGradeTreeVO treeVO, List<DistributionGrade> list) {

        List<AdminDistributionGradeTreeVO> childrenList = new ArrayList<AdminDistributionGradeTreeVO>();
        for (DistributionGrade grade : list) {
            Classify treeClassify = publicdataClient.getClassify(treeVO.getClassifyId());
            Classify gradeClassify = publicdataClient.getClassify(grade.getClassifyId());
            if (gradeClassify.getParentId() == treeClassify.getId()) {
                AdminDistributionGradeTreeVO child = new AdminDistributionGradeTreeVO();
                child.setId(grade.getId());
                child.setName(grade.getName());
                child.setBsid(grade.getBsid());
                child.setCash(grade.getCash());
                child.setMonthLimit(grade.getMonthLimit());
                child.setType(grade.getType());
                child.setUserResource(grade.getUserResource());
                child.setClassifyId(grade.getClassifyId());
                fillTree(child, list);
                childrenList.add(child);
            }
        }
        treeVO.setChildrenList(childrenList);
    }
}
