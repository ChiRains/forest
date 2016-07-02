//package com.qcloud.component.mall.web.eximpl;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.evaluationcenter.EvaluationcenterClient;
//import com.qcloud.component.evaluationcenter.QEvaluationcentUser;
//import com.qcloud.component.evaluationcenter.model.query.UserEvaluationQuery;
//import com.qcloud.component.personalcenter.web.handler.EvaluationcenterGetter;
//
//@Component
//public class EvaluationcenterGetterImpl implements EvaluationcenterGetter {
//
//    @Autowired
//    private EvaluationcenterClient evaluationcenterClient;
//
//    @Override
//    public List<Long> listEvaluationOrderItemDetailIds(Long userId) {
//
//        List<Long> ids = new ArrayList<Long>();
//        UserEvaluationQuery query = new UserEvaluationQuery();
//        query.setUserId(userId);
//        List<QEvaluationcentUser> list = evaluationcenterClient.listQEvaluationcentUser(query);
//        for (QEvaluationcentUser evaluationcentUser : list) {
//            ids.add(evaluationcentUser.getOrderItemDetailId());
//        }
//        return ids;
//    }
//
//    @Override
//    public List<EvaluationcenterUser> listEvaluationUser(Long userId) {
//
//        return null;
//    }
//}
