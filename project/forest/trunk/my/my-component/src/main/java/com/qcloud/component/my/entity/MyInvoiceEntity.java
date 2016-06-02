package com.qcloud.component.my.entity;

import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.my.QMyInvoice;
import com.qcloud.component.my.model.InvoiceMode;

public class MyInvoiceEntity implements QMyInvoice {

    private InvoiceMode invoiceMode;

    public MyInvoiceEntity(InvoiceMode invoiceMode) {

        super();
        this.invoiceMode = invoiceMode;
    }

    @Override
    public String getHead() {

        return invoiceMode.getHead();
    }

    @Override
    public String getContent() {

        return invoiceMode.getContent();
    }

    @Override
    public InvoiceType getType() {

        return InvoiceType.get(invoiceMode.getType());
    }

    @Override
    public NeedInvoiceType getMode() {

        return NeedInvoiceType.get(invoiceMode.getMode());
    }
}
