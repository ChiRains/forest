package com.qcloud.component.my.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.InvoiceModeDao;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.model.query.InvoiceModeQuery;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;

@Repository
public class InvoiceModeDaoCacheImpl implements InvoiceModeDao {

    @Autowired
    private InvoiceModeDao invoiceModeDaoMysqlImpl;

    // @Autowired
    // private InvoiceModeDao invoiceModeDaoRedisImpl;
    @Override
    public boolean add(InvoiceMode invoiceMode) {

        return invoiceModeDaoMysqlImpl.add(invoiceMode);
    }

    @Override
    public InvoiceMode get(Long id) {

        return invoiceModeDaoMysqlImpl.get(id);
        // return CacheLoader.get(invoiceModeDaoRedisImpl, invoiceModeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return invoiceModeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(InvoiceMode invoiceMode) {

        return invoiceModeDaoMysqlImpl.update(invoiceMode);
    }

    @Override
    public List<InvoiceMode> list(List<Long> idList) {

        return CacheLoader.list(invoiceModeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, InvoiceMode> map(Set<Long> idSet) {

        return CacheLoader.map(invoiceModeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<InvoiceMode> page(int start, int count) {

        return invoiceModeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<InvoiceMode> page(InvoiceModeQuery query, int start, int count) {

        return invoiceModeDaoMysqlImpl.page(query, start, count);
    }

    public List<InvoiceMode> listAll() {

        return invoiceModeDaoMysqlImpl.listAll();
    }

    @Override
    public InvoiceMode getByUser(Long userId) {

        return invoiceModeDaoMysqlImpl.getByUser(userId);
    }

    @Override
    public List<InvoiceMode> listByUser(Long userId) {

        return invoiceModeDaoMysqlImpl.listByUser(userId);
    }
}
