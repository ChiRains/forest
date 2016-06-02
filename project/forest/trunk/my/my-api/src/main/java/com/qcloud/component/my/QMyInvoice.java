package com.qcloud.component.my;

public interface QMyInvoice {

    String getHead();

    String getContent();

    InvoiceType getType();

    NeedInvoiceType getMode();
}
