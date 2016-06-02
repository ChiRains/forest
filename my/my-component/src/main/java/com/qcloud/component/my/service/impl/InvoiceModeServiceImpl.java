package com.qcloud.component.my.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.autoid.AutoIdGenerator;
import com.qcloud.component.my.dao.InvoiceModeDao;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.model.query.InvoiceModeQuery;
import com.qcloud.component.my.service.InvoiceModeService;
import com.qcloud.pirates.data.Page;

@Service
public class InvoiceModeServiceImpl implements InvoiceModeService {

    @Autowired
    private InvoiceModeDao      invoiceModeDao;

    @Autowired
    private AutoIdGenerator     autoIdGenerator;

    private static final String ID_KEY = "personalcenter_invoice_mode";

    @Override
    public boolean add(InvoiceMode invoiceMode) {

        long id = autoIdGenerator.get(ID_KEY);
        invoiceMode.setId(id);
        return invoiceModeDao.add(invoiceMode);
    }

    @Override
    public InvoiceMode get(Long id) {

        return invoiceModeDao.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return invoiceModeDao.delete(id);
    }

    @Override
    public boolean update(InvoiceMode invoiceMode) {

        return invoiceModeDao.update(invoiceMode);
    }

    @Override
    public Page<InvoiceMode> page(InvoiceModeQuery query, int start, int count) {

        return invoiceModeDao.page(query, start, count);
    }

    public List<InvoiceMode> listAll() {

        return invoiceModeDao.listAll();
    }

    @Override
    public InvoiceMode getByUser(Long userId) {

        return invoiceModeDao.getByUser(userId);
    }
}
