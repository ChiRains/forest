package com.qcloud.component.commoditycenter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.service.MerchandiseItemService;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseItemHandler;

@Controller
@RequestMapping(value = MerchandiseItemController.DIR)
public class MerchandiseItemController {

    public static final String     DIR = "/merchandiseItem";

    @Autowired
    private MerchandiseItemService merchandiseItemService;

    @Autowired
    private MerchandiseItemHandler merchandiseItemHandler;
}
