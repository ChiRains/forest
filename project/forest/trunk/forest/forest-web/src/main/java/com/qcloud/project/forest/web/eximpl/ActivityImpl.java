package com.qcloud.project.forest.web.eximpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.web.handler.ActivityForMy;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.service.ActivityService;

@Component
public class ActivityImpl implements ActivityForMy {

    @Autowired
    private ActivityService activityService;

    @Override
    public List<String> getName(Long departmentId) {

        List<Activity> activities = activityService.listBydepartmentId(departmentId);
        List<String> strings = new ArrayList<String>();
        for (Activity activity : activities) {
            strings.add(activity.getName());
        }
        return strings;
    }
}
