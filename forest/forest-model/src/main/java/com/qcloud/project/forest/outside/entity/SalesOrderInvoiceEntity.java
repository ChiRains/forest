package com.qcloud.project.forest.outside.entity;

/**
 * 发票明细
 */
public class SalesOrderInvoiceEntity {

    public String title;

    public double amount;

    public String Content;

    public String invoiceTypeCode;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {

        this.amount = amount;
    }

    public String getContent() {

        return Content;
    }

    public void setContent(String content) {

        Content = content;
    }

    public String getInvoiceTypeCode() {

        return invoiceTypeCode;
    }

    public void setInvoiceTypeCode(String invoiceTypeCode) {

        this.invoiceTypeCode = invoiceTypeCode;
    }
}
