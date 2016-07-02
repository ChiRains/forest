package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.WealthType;
import com.qcloud.component.personalcenter.model.MyCommissionWithdrawCash;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.key.TypeEnum;
import com.qcloud.component.personalcenter.model.query.MyCommissionWithdrawCashQuery;
import com.qcloud.component.personalcenter.service.MyCommissionWithdrawCashService;
import com.qcloud.component.personalcenter.service.MyWealthDetailService;
import com.qcloud.component.personalcenter.web.handler.MyCommissionWithdrawCashHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyCommissionWithdrawCashVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyCommissionWithdrawCashVOExport;
import com.qcloud.component.template.client.excel.ExcelClient;
import com.qcloud.component.template.client.instance.ClientFactory;
import com.qcloud.component.template.client.instance.OperatePVFactory;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMyCommissionWithdrawCashController.DIR)
public class AdminMyCommissionWithdrawCashController {

    public static final String              DIR             = "admin/myCommissionWithdrawCash";

    public static final String              PASSCHECK       = "您的申请已经通过，请等待客服进行转账";

    public static final String              NOTPASSCHECK    = "对不起，您的申请不通过！";

    public static final String              TRANSFERSUCCESS = "转账成功！";

    public static final String              TRANSFERFAILED  = "对不起，转账失败！";

    @Autowired
    private MyCommissionWithdrawCashService myCommissionWithdrawCashService;

    @Autowired
    private MyCommissionWithdrawCashHandler myCommissionWithdrawCashHandler;

    @Autowired
    private MyWealthDetailService           myWealthDetailService;

    @Autowired
    private PersonalcenterClient            personalcenterClient;

    @RequestMapping
    @NoReferer
    // 提现审核列表
    public ModelAndView list(PPage pPage, MyCommissionWithdrawCashQuery query) {

        query.setMode(1);
        Page<MyCommissionWithdrawCash> page = myCommissionWithdrawCashService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMyCommissionWithdrawCashVO> list = myCommissionWithdrawCashHandler.toVOList4Admin(page.getData());
        String url = DIR + "/list";
        String url1 = "myCommissionWithdrawCash/export";
        String param1 = "";
        if (query.getDate() != null) {
            param1 = DateUtil.date2String(query.getDate(), "yyyy-MM-dd");
            url = url + "?date=" + param1;
            url1 = url1 + ".do?date=" + param1;
            url = url + "&state=" + query.getState();
            url1 = url1 + "&state=" + query.getState();
        } else if (query.getDate() == null) {
            url = url + "?state=" + query.getState();
            url1 = url1 + ".do?state=" + query.getState();
        }
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyCommissionWithdrawCash-list", url, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        pagingView.addObject("exportUrl", url1);
        return pagingView;
    }

    @SuppressWarnings("deprecation")
    @RequestMapping
    // 导出提现列表数据(excel)
    public void export(HttpServletRequest reqsuest, HttpServletResponse response, MyCommissionWithdrawCashQuery query) throws Exception {

        try {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=MyCommissionWithdrawCashData.xls");
            Page<MyCommissionWithdrawCash> page = myCommissionWithdrawCashService.page(query, 0, Integer.MAX_VALUE);
            List<MyCommissionWithdrawCash> list = page.getData();
            List<AdminMyCommissionWithdrawCashVOExport> voList = myCommissionWithdrawCashHandler.toVOList4Export(list);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("result", voList);
            String fileDir = reqsuest.getRealPath(TypeEnum.EXCEL_TEMPLATE_DIR);
            ExcelClient excelClient = ClientFactory.getExcelClientInstance();
            excelClient.parse(OperatePVFactory.getTemplateFilePV(fileDir + "/myCommissionWithdrawCash_data.xls"), OperatePVFactory.getObjectValuePV(map), OperatePVFactory.getROOSPV(response.getOutputStream()));
        } catch (Exception e) {
            throw new Exception("导出excel出错:" + e.getMessage(), e);
        }
    }

    // 转账管理列表
    @RequestMapping
    public ModelAndView transferlist(PPage pPage, MyCommissionWithdrawCashQuery query) {

        query.setMode(2);
        Page<MyCommissionWithdrawCash> page = myCommissionWithdrawCashService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMyCommissionWithdrawCashVO> list = myCommissionWithdrawCashHandler.toVOList4Admin(page.getData());
        String url = DIR + "/transferlist";
        String param1 = "";
        if (query.getDate() != null) {
            param1 = DateUtil.date2String(query.getDate(), "yyyy-MM-dd");
            url = url + "&date=" + param1;
        }
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyCommissionWithdrawCash-transferlist", url, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-MyCommissionWithdrawCash-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MyCommissionWithdrawCash myCommissionWithdrawCash) {

        myCommissionWithdrawCashService.add(myCommissionWithdrawCash);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MyCommissionWithdrawCash myCommissionWithdrawCash = myCommissionWithdrawCashService.get(id);
        AdminMyCommissionWithdrawCashVO adminMyCommissionWithdrawCashVO = myCommissionWithdrawCashHandler.toVO4Admin(myCommissionWithdrawCash);
        ModelAndView model = new ModelAndView("/admin/personalcenter-MyCommissionWithdrawCash-edit");
        model.addObject("myCommissionWithdrawCash", adminMyCommissionWithdrawCashVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MyCommissionWithdrawCash myCommissionWithdrawCash) {

        myCommissionWithdrawCashService.update(myCommissionWithdrawCash);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        myCommissionWithdrawCashService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView check(Long id, int ischeck) {

        MyCommissionWithdrawCash myCommissionWithdrawCash = myCommissionWithdrawCashService.get(id);
        myCommissionWithdrawCash.setState(ischeck);
        MyWealthDetail myWealthDetail = myWealthDetailService.getByIdandUserId(myCommissionWithdrawCash.getWealthDetailId(), myCommissionWithdrawCash.getUserId());
        switch (ischeck) {
        case 2:
            myWealthDetail.setDesc(PASSCHECK);
            break;
        case 4:
            myWealthDetail.setDesc(NOTPASSCHECK);
            if (myWealthDetail.getPoint() < 0) {
                myWealthDetail.setPoint(-myWealthDetail.getPoint());
            }
            personalcenterClient.calculateMyWealth(myWealthDetail.getUserId(), WealthType.COMMISSION, myWealthDetail.getPoint(), false, "申请失败，已返还" + myWealthDetail.getPoint());
            break;
        case 3:
            myWealthDetail.setDesc(TRANSFERSUCCESS);
            break;
        case 5:
            myWealthDetail.setDesc(TRANSFERFAILED);
            break;
        }
        myWealthDetailService.update(myWealthDetail);
        myCommissionWithdrawCashService.update(myCommissionWithdrawCash);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
