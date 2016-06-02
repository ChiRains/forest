package com.qcloud.component.my.web.handler;

import java.util.List;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.web.vo.InvoiceModeVO;

public interface InvoiceModeHandler {

    List<InvoiceModeVO> toVOList(List<InvoiceMode> list);

    InvoiceModeVO toVO(InvoiceMode invoiceMode);
}
