package com.qcloud.component.my.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.model.query.InvoiceModeQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;

public interface InvoiceModeDao extends ISimpleDao<InvoiceMode, Long> {

    public boolean add(InvoiceMode invoiceMode);

    public InvoiceMode get(Long id);

    public boolean delete(Long id);

    public boolean update(InvoiceMode invoiceMode);

    public List<InvoiceMode> list(List<Long> idList);

    public Map<Long, InvoiceMode> map(Set<Long> idSet);

    public Page<InvoiceMode> page(InvoiceModeQuery query, int start, int size);

    public List<InvoiceMode> listAll();

    InvoiceMode getByUser(Long userId);

    public List<InvoiceMode> listByUser(Long userId);
}
