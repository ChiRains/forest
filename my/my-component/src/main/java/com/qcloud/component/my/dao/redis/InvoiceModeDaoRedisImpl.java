package com.qcloud.component.my.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.my.dao.InvoiceModeDao;
import com.qcloud.component.my.model.InvoiceMode;
import com.qcloud.component.my.model.query.InvoiceModeQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class InvoiceModeDaoRedisImpl implements InvoiceModeDao {

    // @Resource(name = "redis-my")
    // private Redis redis;
    @Override
    public boolean add(InvoiceMode invoiceMode) {

        throw new NotImplementedException();
    }

    @Override
    public InvoiceMode get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(InvoiceMode invoiceMode) {

        throw new NotImplementedException();
    }

    @Override
    public List<InvoiceMode> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, InvoiceMode> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<InvoiceMode> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<InvoiceMode> page(InvoiceModeQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<InvoiceMode> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public InvoiceMode getByUser(Long userId) {

        throw new NotImplementedException();
    }
}
