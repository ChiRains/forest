package com.qcloud.component.commoditycenter.web.handler.url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class AttributeDefinitionUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/attributeDefinition/list.do");
        list.add("/admin/attributeDefinition/toAdd.do");
        list.add("/admin/attributeDefinition/toEdit.do");
        list.add("/admin/attributeDefinition/add.do");
        list.add("/admin/attributeDefinition/edit.do");
        list.add("/admin/attributeDefinition/specList.do");
        list.add("/admin/attributeDefinition/toAddSpec.do");
        list.add("/admin/attributeDefinition/toEditSpec.do");
        list.add("/admin/attributeDefinition/addSpec.do");
        list.add("/admin/attributeDefinition/editSpec.do");
        list.add("/admin/attributeDefinition/selectAttribute.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/attributeDefinition/list.do");
        list.add("/admin/attributeDefinition/toAdd.do");
        list.add("/admin/attributeDefinition/toEdit.do");
        list.add("/admin/attributeDefinition/add.do");
        list.add("/admin/attributeDefinition/edit.do");
        list.add("/admin/attributeDefinition/specList.do");
        list.add("/admin/attributeDefinition/toAddSpec.do");
        list.add("/admin/attributeDefinition/toEditSpec.do");
        list.add("/admin/attributeDefinition/addSpec.do");
        list.add("/admin/attributeDefinition/editSpec.do");
        list.add("/admin/attributeDefinition/selectAttribute.do");
        return list;
    }

    @Override
    public Map<String, List<String>> permissionRelaMap() {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        return map;
    }
}
