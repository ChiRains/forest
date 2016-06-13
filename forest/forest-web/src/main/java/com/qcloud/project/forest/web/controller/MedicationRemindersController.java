package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.project.forest.model.MedicationReminders;
import com.qcloud.project.forest.model.MedicationRemindersTheme;
import com.qcloud.project.forest.model.MedicationRemindersTime;
import com.qcloud.project.forest.model.key.TypeEnum.EnableType;
import com.qcloud.project.forest.model.query.MedicationRemindersThemeQuery;
import com.qcloud.project.forest.service.MedicationRemindersService;
import com.qcloud.project.forest.service.MedicationRemindersThemeService;
import com.qcloud.project.forest.service.MedicationRemindersTimeService;
import com.qcloud.project.forest.web.form.MedicationRemindersForm;
import com.qcloud.project.forest.web.handler.MedicationRemindersHandler;
import com.qcloud.project.forest.web.handler.MedicationRemindersThemeHandler;
import com.qcloud.project.forest.web.vo.MedicationRemindersThemeVO;
import com.qcloud.project.forest.web.vo.MedicationRemindersVO;

@Controller
@RequestMapping(value = MedicationRemindersController.DIR)
public class MedicationRemindersController {

    public static final String              DIR = "/medicationReminders";

    @Autowired
    private MedicationRemindersService      medicationRemindersService;

    @Autowired
    private MedicationRemindersHandler      medicationRemindersHandler;

    @Autowired
    private MedicationRemindersThemeService medicationRemindersThemeService;

    @Autowired
    private MedicationRemindersThemeHandler medicationRemindersThemeHandler;

    @Autowired
    private MedicationRemindersTimeService  medicationRemindersTimeService;

    @Autowired
    private FileSDKClient                   fileSDKClient;

    /**
     * 用药提醒列表（外层）
     * @param request
     * @param medicationRemindersThemeQuery
     * @param pPage
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontPagingView outerList(HttpServletRequest request, MedicationRemindersThemeQuery medicationRemindersThemeQuery, PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        medicationRemindersThemeQuery.setUserId(user.getId());
        Page<MedicationRemindersTheme> page = medicationRemindersThemeService.page(medicationRemindersThemeQuery, pPage.getPageStart(), pPage.getPageSize());
        List<MedicationRemindersThemeVO> medicationRemindersThemeVOs = medicationRemindersThemeHandler.toVOList(page.getData());
        FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        frontPagingView.setList(medicationRemindersThemeVOs);
        return frontPagingView;
    }

    /**
     * 添加用药提醒
     * @param request
     * @param medicationRemindersForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView addMedicationReminders(HttpServletRequest request, MedicationRemindersForm medicationRemindersForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        // 添加到外部
        MedicationRemindersTheme medicationRemindersTheme = new MedicationRemindersTheme();
        medicationRemindersTheme.setName(medicationRemindersForm.getName());
        medicationRemindersTheme.setImage(fileSDKClient.uidToUrl(medicationRemindersForm.getImage()));
        medicationRemindersTheme.setUserId(user.getId());
        medicationRemindersTheme.setRecordTime(new Date());
        medicationRemindersTheme.setEnable(EnableType.ENABLE.getKey());
        medicationRemindersThemeService.add(medicationRemindersTheme);
        // 添加到药品提醒
        MedicationReminders medicationReminders = null;
        for (MedicationRemindersVO medicationRemindersVO : medicationRemindersForm.getMedicationRemindersVOs()) {
            medicationReminders = new MedicationReminders();
            medicationReminders.setThemeId(medicationRemindersTheme.getId());
            medicationReminders.setDesc(medicationRemindersVO.getDesc());
            medicationReminders.setMedicineDosage(medicationRemindersVO.getMedicineDosage());
            medicationReminders.setMedicineName(medicationRemindersVO.getMedicineName());
            medicationReminders.setMedicineId(medicationRemindersVO.getMedicineId());
            medicationReminders.setMedicineUnit(medicationRemindersVO.getMedicineUnit());
            medicationReminders.setUseTimes(medicationRemindersVO.getUseTimes());
            medicationReminders.setPeriodTimes(medicationRemindersVO.getPeriodTimes());
            medicationReminders.setRecordTime(new Date());
            medicationReminders.setUserId(user.getId());
            medicationRemindersService.add(medicationReminders);
            // 添加时间到时间表
            String[] arryDay = medicationRemindersVO.getPeriodTimes().split(",");
            String[] arryTime = medicationRemindersVO.getUseTimes().split(",");
            insertInMedicationRemindersTime(arryTime, arryDay, medicationReminders.getId());
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("添加成功");
        return frontAjaxView;
    }

    /**
     * 跳转到编辑用药提醒的页面
     * @param request
     * @param themeId
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView toEditMedicationReminders(HttpServletRequest request, Long themeId) {

        MedicationRemindersTheme medicationRemindersTheme = medicationRemindersThemeService.get(themeId);
        MedicationRemindersThemeVO medicationRemindersThemeVO = medicationRemindersThemeHandler.toVO(medicationRemindersTheme);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", medicationRemindersThemeVO);
        return frontAjaxView;
    }

    /**
     * 修改用药提醒
     * @param request
     * @param medicationRemindersForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView editMedicationReminders(HttpServletRequest request, MedicationRemindersForm medicationRemindersForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        // 修改到外部药品提醒
        MedicationRemindersTheme medicationRemindersTheme = medicationRemindersThemeService.get(medicationRemindersForm.getId());
        AssertUtil.notNull(medicationRemindersTheme, "找不到相关信息");
        AssertUtil.assertTrue(medicationRemindersTheme.getUserId() == user.getId() ? true : false, "不能修改其他用户信息");
        if (medicationRemindersTheme != null) {
            medicationRemindersTheme.setName(medicationRemindersForm.getName());
            if (medicationRemindersForm.getImage() != null) {
                String url = fileSDKClient.uidToUrl(medicationRemindersForm.getImage());
                if (!medicationRemindersTheme.getImage().equals(url)) {
                    medicationRemindersTheme.setImage(url);
                } else {
                    medicationRemindersTheme.setImage(medicationRemindersForm.getUid());
                }
            }
            medicationRemindersTheme.setUserId(user.getId());
            medicationRemindersTheme.setRecordTime(medicationRemindersTheme.getRecordTime());
            medicationRemindersTheme.setEnable(medicationRemindersTheme.getEnable());
            medicationRemindersThemeService.update(medicationRemindersTheme);
        }
        // 修改到药品提醒
        MedicationReminders medicationReminders = null;
        for (MedicationRemindersVO medicationRemindersVO : medicationRemindersForm.getMedicationRemindersVOs()) {
            medicationReminders = medicationRemindersService.get(medicationRemindersVO.getId());
            AssertUtil.notNull(medicationReminders, "找不到相关信息");
            AssertUtil.assertTrue(medicationReminders.getUserId() == user.getId() ? true : false, "不能修改其他用户信息");
            if (medicationReminders != null) {
                medicationReminders.setThemeId(medicationRemindersTheme.getId());
                medicationReminders.setDesc(medicationRemindersVO.getDesc());
                medicationReminders.setMedicineDosage(medicationRemindersVO.getMedicineDosage());
                medicationReminders.setMedicineName(medicationRemindersVO.getMedicineName());
                medicationReminders.setMedicineId(medicationRemindersVO.getMedicineId());
                medicationReminders.setMedicineUnit(medicationRemindersVO.getMedicineUnit());
                medicationReminders.setUseTimes(medicationRemindersVO.getUseTimes());
                medicationReminders.setPeriodTimes(medicationRemindersVO.getPeriodTimes());
                medicationReminders.setRecordTime(medicationReminders.getRecordTime());
                medicationRemindersService.update(medicationReminders);
                // 添加到时间表
                medicationRemindersTimeService.deleteByReminderId(medicationReminders.getId());
                String[] arryDay = medicationRemindersVO.getPeriodTimes().split(",");
                String[] arryTime = medicationRemindersVO.getUseTimes().split(",");
                insertInMedicationRemindersTime(arryTime, arryDay, medicationReminders.getId());
            }
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("修改成功");
        return frontAjaxView;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView deleteMedicationRemindersTheme(HttpServletRequest request, Long themeId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MedicationRemindersTheme medicationRemindersTheme = medicationRemindersThemeService.get(themeId);
        AssertUtil.assertTrue(user.getId() == medicationRemindersTheme.getUserId() ? true : false, "不能删除其他用户的药物提醒");
        List<MedicationReminders> medicationReminders = medicationRemindersService.listByThemeId(themeId);
        List<Long> list = new ArrayList<Long>();
        for (MedicationReminders medicationReminders2 : medicationReminders) {
            list.add(medicationReminders2.getId());
        }
        for (Long long1 : list) {
            medicationRemindersService.delete(long1);
        }
        medicationRemindersThemeService.delete(themeId);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("删除成功");
        return frontAjaxView;
    }

    /**
     * 删除用药提醒（内层）
     * @param request
     * @param medicationRemindersId
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView deleteMedicationReminders(HttpServletRequest request, Long medicationRemindersId) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MedicationReminders medicationReminders = medicationRemindersService.get(medicationRemindersId);
        AssertUtil.assertTrue(user.getId() == medicationReminders.getUserId() ? true : false, "不能删除其他用户的药物提醒");
        medicationRemindersService.delete(medicationRemindersId);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("删除成功");
        return frontAjaxView;
    }

    /**
     * 跳转到编辑用药提醒的页面
     * @param request
     * @param themeId
     * @param enable
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView enableMedicationRemindersTheme(HttpServletRequest request, Long themeId, int enable) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        MedicationRemindersTheme medicationRemindersTheme = medicationRemindersThemeService.get(themeId);
        AssertUtil.assertTrue(user.getId() == medicationRemindersTheme.getUserId() ? true : false, "不能对其他用户的药物提醒进行该操作");
        medicationRemindersTheme.setEnable(enable);
        medicationRemindersThemeService.update(medicationRemindersTheme);
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.setMessage("操作成功");
        return frontAjaxView;
    }

    private void insertInMedicationRemindersTime(String[] times, String[] days, Long medicationRemindersId) {

        MedicationRemindersTime medicationRemindersTime = null;
        for (String dayStr : days) {
            for (String timeStr : times) {
                medicationRemindersTime = new MedicationRemindersTime();
                medicationRemindersTime.setReminderId(medicationRemindersId);
                medicationRemindersTime.setExcuteTime(timeStr + " 星期" + dayStr);
                medicationRemindersTimeService.add(medicationRemindersTime);
            }
        }
    }
}
