package com.qcloud.component.personalcenter.web.handler;
import java.util.List;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.web.vo.GradeVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminGradeVO;
public interface GradeHandler {
    
    List<GradeVO> toVOList(List<Grade> list);

    GradeVO toVO(Grade grade);

    List<AdminGradeVO> toVOList4Admin(List<Grade> list);

    List<AdminGradeVO> toVOList4Admin(List<Grade> list, long gradeId);

    AdminGradeVO toVO4Admin(Grade grade);
}
