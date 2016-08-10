package com.qcloud.component.pay.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.redis.Redis;
import com.qcloud.component.pay.dao.PayRecordDao;
import com.qcloud.component.pay.model.PayRecord;
import com.qcloud.component.pay.model.query.PayRecordQuery;

@Repository
public class PayRecordDaoRedisImpl implements PayRecordDao {

    // @Resource(name = "redis-pay")
    // private Redis redis;
    @Override
    public boolean add(PayRecord payRecord) {

        throw new NotImplementedException();
    }

    @Override
    public PayRecord get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(PayRecord payRecord) {

        throw new NotImplementedException();
    }

    @Override
    public List<PayRecord> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, PayRecord> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<PayRecord> page(int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public Page<PayRecord> page(PayRecordQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<PayRecord> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public PayRecord getByObjectId(long objectId) {

        throw new NotImplementedException();
    }
}
