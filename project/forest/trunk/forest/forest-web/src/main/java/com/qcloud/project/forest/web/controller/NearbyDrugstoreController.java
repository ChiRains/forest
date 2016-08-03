package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.my.model.MyCollection;
import com.qcloud.component.my.model.key.TypeEnum.CollectionType;
import com.qcloud.component.my.service.MyCollectionService;
import com.qcloud.component.my.web.vo.MyStoreCollectionVO;
import com.qcloud.component.organization.OrganizationClient;
import com.qcloud.component.organization.QDepartment;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.service.ActivityService;

@Controller
@RequestMapping(value = NearbyDrugstoreController.DIR)
public class NearbyDrugstoreController {

    public static final String  DIR = "/nearbyDrugstore";

    @Autowired
    private OrganizationClient  organizationClient;

    @Autowired
    private MyCollectionService myCollectionService;

    @Autowired
    private ActivityService     activityService;

    public static double Distance(double long1, double lat1, double long2, double lat2) {

        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView listNearby(Double latitude, Double longitude) {

        List<QDepartment> departments = organizationClient.listNearby(latitude, longitude);
        List<MyStoreCollectionVO> myStoreCollectionVOs = new ArrayList<MyStoreCollectionVO>(4);
        MyStoreCollectionVO myStoreCollectionVO = null;
        for (QDepartment qDepartment : departments) {
            myStoreCollectionVO = new MyStoreCollectionVO();
            myStoreCollectionVO.setId(qDepartment.getId());
            myStoreCollectionVO.setName(qDepartment.getName());
            myStoreCollectionVO.setAddress(qDepartment.getAddress());
            myStoreCollectionVO.setPhone(qDepartment.getPhone());
            myStoreCollectionVO.setShopHour(qDepartment.getShopHour());
            myStoreCollectionVO.setDistance(Distance(longitude, latitude, qDepartment.getLongitude(), qDepartment.getLatitude()));
            myStoreCollectionVO.setLongitude(qDepartment.getLongitude());
            myStoreCollectionVO.setLatitude(qDepartment.getLatitude());
            myStoreCollectionVOs.add(myStoreCollectionVO);
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("list", myStoreCollectionVOs);
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView getStore(HttpServletRequest request, Long id, Double latitude, Double longitude) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_IS_LOGIN_PARAMETER_KEY);
        QDepartment qDepartment = organizationClient.getDepartment(id);
        MyStoreCollectionVO myStoreCollectionVO = new MyStoreCollectionVO();
        myStoreCollectionVO.setId(qDepartment.getId());
        myStoreCollectionVO.setName(qDepartment.getName());
        myStoreCollectionVO.setAddress(qDepartment.getAddress());
        myStoreCollectionVO.setPhone(qDepartment.getPhone());
        myStoreCollectionVO.setShopHour(qDepartment.getShopHour());
        myStoreCollectionVO.setDistance(Distance(longitude, latitude, qDepartment.getLongitude(), qDepartment.getLatitude()));
        myStoreCollectionVO.setLongitude(qDepartment.getLongitude());
        myStoreCollectionVO.setLatitude(qDepartment.getLatitude());
        myStoreCollectionVO.setImages(organizationClient.listDepartmentImages(id));
        List<Activity> activities = activityService.listBydepartmentId(id);
        List<String> strings = new ArrayList<String>();
        for (Activity activity : activities) {
            strings.add(activity.getName());
        }
        myStoreCollectionVO.setActivities(strings);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        if (user != null) {
            MyCollection myCollection = myCollectionService.getByObject(id, user.getId(), CollectionType.STORE);
            if (myCollection != null) {
                frontAjaxView.addObject("isCollect", 1);
            } else {
                frontAjaxView.addObject("isCollect", 0);
            }
        } else {
            frontAjaxView.addObject("isCollect", 0);
        }
        frontAjaxView.addObject("result", myStoreCollectionVO);
        return frontAjaxView;
    }
}
