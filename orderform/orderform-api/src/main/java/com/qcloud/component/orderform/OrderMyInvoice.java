package com.qcloud.component.orderform;

import com.qcloud.component.my.InvoiceType;
import com.qcloud.component.my.NeedInvoiceType;
import com.qcloud.component.my.QMyInvoice;

public class OrderMyInvoice implements QMyInvoice {

    // 模式 1开发票 1不开发票
    private int                    mode;

    // 类型 1普通 1增值税
    private int                    type;

    // 抬头
    private String                 head;

    // 内容
    private String                 content;

    public static final QMyInvoice NOT_NEED = OrderMyInvoice.get(NeedInvoiceType.NO.getKey(), 0, "", "");

    public static QMyInvoice get(final int mode, final int type, final String head, final String content) {

        OrderMyInvoice orderMyInvoice = new OrderMyInvoice();
        orderMyInvoice.mode = mode;
        orderMyInvoice.type = type;
        orderMyInvoice.head = head;
        orderMyInvoice.content = content;
        return orderMyInvoice;
    }

    @Override
    public String getHead() {

        return head;
    }

    @Override
    public String getContent() {

        return content;
    }

    @Override
    public InvoiceType getType() {

        return InvoiceType.get(type);
    }

    @Override
    public NeedInvoiceType getMode() {

        return NeedInvoiceType.get(mode);
    }
}
