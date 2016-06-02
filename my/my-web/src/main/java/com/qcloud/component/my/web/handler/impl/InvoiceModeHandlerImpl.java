package com.qcloud.component.my.web.handler.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.web.handler.InvoiceModeHandler;
import com.qcloud.component.my.web.vo.InvoiceModeVO;
import com.qcloud.pirates.core.json.Json;

@Component
public class InvoiceModeHandlerImpl implements InvoiceModeHandler {

    @Override
    public List<InvoiceModeVO> toVOList(List<InvoiceMode> list) {

        List<InvoiceModeVO> voList = new ArrayList<InvoiceModeVO>();
        for (InvoiceMode invoiceMode : list) {
            voList.add(toVO(invoiceMode));
        }
        return voList;
    }

    @Override
    public InvoiceModeVO toVO(InvoiceMode invoiceMode) {

        String json = Json.toJson(invoiceMode);
        return Json.toObject(json, InvoiceModeVO.class, true);
    }
}
