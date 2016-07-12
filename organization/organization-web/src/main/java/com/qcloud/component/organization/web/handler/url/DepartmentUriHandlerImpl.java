package com.qcloud.component.organization.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class DepartmentUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/department/list.do");
        list.add("/admin/department/toAdd.do");
        list.add("/admin/department/toEdit.do");
        list.add("/admin/department/add.do");
        list.add("/admin/department/edit.do");
        list.add("/department/list.do");
        list.add("/admin/department/delete.do");
        list.add("/admin/department/platformTypeList.do");
        //
        list.add("/admin/department/list4Super.do");
        list.add("/admin/department/toAdd4Super.do");
        list.add("/admin/department/toEdit4Super.do");
        list.add("/admin/department/add4Super.do");
        list.add("/admin/department/edit4Super.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/department/list.do");
        list.add("/admin/department/toAdd.do");
        list.add("/admin/department/toEdit.do");
        list.add("/admin/department/add.do");
        list.add("/admin/department/edit.do");
        list.add("/admin/department/delete.do");
        //
        list.add("/admin/department/list4Super.do");
        list.add("/admin/department/toAdd4Super.do");
        list.add("/admin/department/toEdit4Super.do");
        list.add("/admin/department/add4Super.do");
        list.add("/admin/department/edit4Super.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        List<String> list = stringToList("/admin/department/list.do");
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("/admin/department/toAdd.do", list);
        map.put("/admin/department/add.do", list);
        map.put("/admin/department/toEdit.do", list);
        map.put("/admin/department/edit.do", list);
        //
        List<String> list4Super = stringToList("/admin/department/list4Super.do");
        map.put("/admin/department/toAdd4Super.do", list4Super);
        map.put("/admin/department/toEdit4Super.do", list4Super);
        map.put("/admin/department/add4Super.do", list4Super);
        map.put("/admin/department/edit4Super.do", list4Super);
        //共同
        List<String> commonList = stringToList("/admin/department/list.do", "/admin/department/list4Super.do");
        map.put("/admin/clerk/list.do", commonList);
        map.put("/admin/departmentClerk/selectMember.do", commonList);
        map.put("/admin/departmentClerk/selectManager.do", commonList);
        map.put("/admin/departmentClerk/editManager.do", commonList);
        map.put("/admin/departmentClerk/edit.do", commonList);
        map.put("/admin/department/delete.do", commonList);
        return map;
    }

    @Override
    public List<String> userUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/department/list.do");
        return list;
    }

    @Override
    public List<String> appUris() {

        List<String> list = new ArrayList<String>();
        list.add("/app/department/list.do");
        list.add("/app/department/listStoreByAddress.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/department/add.do");
        list.add("/department/listStoreByAddress.do");
        //
        list.add("/app/department/listStoreByAddress.do");
        return list;
    }
}
