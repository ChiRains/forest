package com.qcloud.project.forest.web.handler.url;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.web.filter.AbstractUriHandler;

public class MedicationRemindersUriHandlerImpl extends AbstractUriHandler {

    @Override
    public List<String> adminUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/medicationReminders/list.do");
        list.add("/admin/medicationReminders/toAdd.do");
        list.add("/admin/medicationReminders/toEdit.do");
        list.add("/admin/medicationReminders/add.do");
        list.add("/admin/medicationReminders/edit.do");
        return list;
    }

    @Override
    public List<String> permissionUris() {

        List<String> list = new ArrayList<String>();
        list.add("/admin/medicationReminders/list.do");
        return list;
    }

    @Override
    public List<String> whiteNameUris() {

        List<String> list = new ArrayList<String>();
        list.add("/medicationReminders/outerList.do");
        list.add("/medicationReminders/addMedicationReminders.do");
        list.add("/medicationReminders/toEditMedicationReminders.do");
        list.add("/medicationReminders/editMedicationReminders.do");
        list.add("/medicationReminders/deleteMedicationRemindersTheme.do");
        list.add("/medicationReminders/deleteMedicationReminders.do");
        list.add("/medicationReminders/enableMedicationRemindersTheme.do");
        return list;
    }
}
