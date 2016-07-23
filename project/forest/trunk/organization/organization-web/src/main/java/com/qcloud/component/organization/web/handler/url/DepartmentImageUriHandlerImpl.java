package com.qcloud.component.organization.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class DepartmentImageUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/departmentImage/list.do");
        list.add("/admin/departmentImage/toAdd.do");
        list.add("/admin/departmentImage/toEdit.do");
        list.add("/admin/departmentImage/add.do");
        list.add("/admin/departmentImage/edit.do");
        list.add("/admin/departmentImage/delete.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/departmentImage/list.do");
        return list;
    }
}
