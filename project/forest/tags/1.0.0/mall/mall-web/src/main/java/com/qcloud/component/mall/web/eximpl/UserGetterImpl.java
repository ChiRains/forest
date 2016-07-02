//package com.qcloud.component.mall.web.eximpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.qcloud.component.commoditycenter.web.handler.RecordUserGetter;
//import com.qcloud.component.personalcenter.PersonalcenterClient;
//import com.qcloud.component.personalcenter.QGrade;
//import com.qcloud.component.personalcenter.QUser;
//import com.qcloud.component.sellercenter.web.handler.UserGetter;
//
//@Component
//public class UserGetterImpl implements UserGetter, RecordUserGetter {
//
//    @Autowired
//    PersonalcenterClient personalcenterClient;
//
//    @Override
//    public String getUserName(long userId) {
//
//        QUser user = personalcenterClient.getUser(userId);
//        if (user == null) {
//            return "";
//        } else {
//            return user.getName();
//        }
//    }
//
//    @Override
//    public RecordUser getUser(Long userId) {
//
//        final QUser user = personalcenterClient.getUser(userId);
//        if (user == null) {
//            return null;
//        } else {
//            final QGrade grade = user.getGrade();
//            return new RecordUser() {
//
//                @Override
//                public String getName() {
//
//                    return user.getName();
//                }
//
//                @Override
//                public String getGradeName() {
//
//                    return grade.getName();
//                }
//
//                @Override
//                public String getMobile() {
//
//                    return user.getMobile();
//                }
//            };
//        }
//    }
//
//    @Override
//    public String getNickname(long userId) {
//
//        QUser user = personalcenterClient.getUser(userId);
//        if (user == null) {
//            return "";
//        } else {
//            return user.getNickname();
//        }
//    }
//}
