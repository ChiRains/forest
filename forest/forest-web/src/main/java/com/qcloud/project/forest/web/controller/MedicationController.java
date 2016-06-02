package com.qcloud.project.forest.web.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
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
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.exception.ForestException;
import com.qcloud.project.forest.model.Medication;
import com.qcloud.project.forest.model.key.TypeEnum.EnableType;
import com.qcloud.project.forest.model.query.MedicationQuery;
import com.qcloud.project.forest.service.MedicationService;
import com.qcloud.project.forest.web.form.MedicationForm;
import com.qcloud.project.forest.web.handler.MedicationHandler;
import com.qcloud.project.forest.web.vo.MedicationVO;

@Controller
@RequestMapping(value = MedicationController.DIR)
public class MedicationController {

    public static final String    DIR = "/medication";

    @Autowired
    private MedicationService     medicationService;

    @Autowired
    private MedicationHandler     medicationHandler;

    @Autowired
    private FileSDKClient         fileSDKClient;

    /**
     * 添加用药提醒
     * @param request
     * @param medicationForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView add(HttpServletRequest request, MedicationForm medicationForm) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
//        long userId=1010008000002202L;
        if (medicationForm.getTakeTimes().size() == 0) {
            throw new ForestException("请输入提醒时间!");
        }
        Medication medication = new Medication();
        Date recordTime = new Date();
       
        medication.setTheme(medicationForm.getTheme());
        medication.setImage(medicationForm.getImage());
        medication.setMedicine(medicationForm.getMedicine());
        medication.setObjectName(medicationForm.getObjectName());
        medication.setDosage(medicationForm.getDosage());
        medication.setUnit(medicationForm.getUnit());
        medication.setDesc(medicationForm.getDesc());
        //
        medication.setEnable(EnableType.ENABLE.getKey());
        medication.setUserId(user.getId());
//        medication.setUserId(userId);
        medication.setRecordTime(recordTime);
        //TODO 用药周期
        List<Integer> periodTypeList=medicationForm.getPeriodType();
        String periodTimes=new String();
        for (Integer period : periodTypeList) {
            periodTimes+=period+",";
        }
        medication.setPeriodTimes(periodTimes);
        //TODO 服药点数
        List<String>  takeTimes=medicationForm.getTakeTimes();
        String takes=new String();
        for (String str : takeTimes) {
            takes+=str+",";
        }
        medication.setUseTimes(takes);
        
        medicationService.add(medication);
       
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("保存成功!");
        return view;
    }

    /**
     * 用药提醒列表
     * @param request
     * @param query
     * @param pageSize
     * @param pageNum
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontPagingView list(HttpServletRequest request, MedicationQuery query,PPage pPage) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
//        long userId=1010008000002202L;
        query.setUserId(user.getId());
//        query.setUserId(userId);
        Page<Medication> page = medicationService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<MedicationVO> voList = medicationHandler.toVOList(page.getData());
        FrontPagingView view = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        view.setMessage("用药列表成功!");
        view.addObject("data", voList);
        return view;
    }

    /**
     * 是否启用
     * @param request
     * @param id
     * @param enable (1开启，2关闭)
     * @return
     */
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView enable(HttpServletRequest request, Long id, Integer enable) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(id, "id不能为空");
//        long userId=1010008000002202L;
        Medication medication = medicationService.get(id);
        if (medication.getUserId() != user.getId()) {
            throw new ForestException("用户不正确!");
        }
        medication.setEnable(enable);
        medicationService.update(medication);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("操作成功!");
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView delete(HttpServletRequest request, Long id) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(id, "id不能为空");
        Medication medication = medicationService.get(id);
        if (medication.getUserId() != user.getId()) {
            throw new ForestException("用户不正确!");
        }
        medicationService.delete(id);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("操作成功!");
        return view;
    }

    /**
     * 跳转编辑页面
     * @param request
     * @param id
     * @return
     */
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView get(HttpServletRequest request, Long id) {

//        long userId=1010008000002202L;
        AssertUtil.assertNotNull(id, "id不能为空!");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Medication medication = medicationService.get(id);
        MedicationVO medicationVO = medicationHandler.toVO(medication);
        if (medication.getUserId() != user.getId()) {
            throw new ForestException("用户不正确!");
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("medication", medicationVO);
        return view;
    }

    /**
     * 编辑用药提醒
     * @param request
     * @param medicationForm
     * @return
     */
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView edit(HttpServletRequest request, MedicationForm medicationForm) {

//        long userId=1010008000002202L;
        AssertUtil.assertNotNull(medicationForm.getId(), "id不能为空!");
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        Medication m = medicationService.get(medicationForm.getId());
        AssertUtil.assertNotNull(m, "用药提醒不存在.");
        if (m.getUserId() != user.getId()) {
            throw new ForestException("用户不正确!");
        }
        
        Date recordTime = new Date();
        m.setTheme(medicationForm.getTheme());
        if(StringUtils.isNotEmpty(medicationForm.getImage())){
            m.setImage(fileSDKClient.uidToUrl(medicationForm.getImage()));
        }
        m.setMedicine(medicationForm.getMedicine());
        m.setObjectName(medicationForm.getObjectName());
        m.setDosage(medicationForm.getDosage());
        m.setUnit(medicationForm.getUnit());
        m.setDesc(medicationForm.getDesc());
        m.setRecordTime(recordTime);
        
      //TODO 用药周期
        List<Integer> periodTypeList=medicationForm.getPeriodType();
        String periodTimes=new String();
        for (Integer period : periodTypeList) {
            periodTimes+=period+",";
        }
        m.setPeriodTimes(periodTimes);
        //TODO 服药点数
        List<String>  takeTimes=medicationForm.getTakeTimes();
        String takes=new String();
        for (String str : takeTimes) {
            takes+=str+",";
        }
        m.setUseTimes(takes);
        medicationService.update(m);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("操作成功!");
        return view;
    }
}
