package com.qcloud.component.my.service;

import java.util.List;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.model.query.InvoiceModeQuery;
import com.qcloud.pirates.data.Page;

public interface InvoiceModeService {

    public boolean add(InvoiceMode invoiceMode);

    public InvoiceMode get(Long id);

    InvoiceMode getByUser(Long userId);

    public boolean delete(Long id);

    public boolean update(InvoiceMode invoiceMode);

    public Page<InvoiceMode> page(InvoiceModeQuery query, int start, int count);

    public List<InvoiceMode> listAll();
}
