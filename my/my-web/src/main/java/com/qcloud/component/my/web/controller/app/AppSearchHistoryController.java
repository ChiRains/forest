// package com.qcloud.component.my.web.controller.app;
//
// import javax.servlet.http.HttpServletRequest;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import com.qcloud.component.my.web.controller.SearchHistoryController;
// import com.qcloud.pirates.mvc.FrontAjaxView;
//
// @Controller
// @RequestMapping(value = AppSearchHistoryController.DIR)
// public class AppSearchHistoryController {
//
// public static final String DIR = "/app/mySearchHistory";
//
// @Autowired
// private SearchHistoryController searchHistoryController;
//
// @RequestMapping
// public FrontAjaxView list(HttpServletRequest request, Integer type, Integer number) {
//
// return searchHistoryController.list(request, type, number);
// }
//
// @RequestMapping
// public FrontAjaxView clear(HttpServletRequest request, Integer type) {
//
// return searchHistoryController.clear(request, type);
// }
// }
