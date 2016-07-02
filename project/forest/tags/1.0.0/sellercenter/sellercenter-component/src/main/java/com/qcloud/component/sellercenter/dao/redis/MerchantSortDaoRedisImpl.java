package com.qcloud.component.sellercenter.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantSortDao;
import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.model.query.MerchantSortQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchantSortDaoRedisImpl implements MerchantSortDao {

    @Override
    public Page<MerchantSort> page(int arg0, int arg1) {

        throw new NotImplementedException();
    }

    @Override
    public boolean add(MerchantSort merchantSort) {

        throw new NotImplementedException();
    }

    @Override
    public MerchantSort get(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(Long id) {

        throw new NotImplementedException();
    }

    @Override
    public boolean update(MerchantSort merchantSort) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantSort> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchantSort> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchantSort> page(MerchantSortQuery query, int start, int count) {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantSort> listAll() {

        throw new NotImplementedException();
    }

    @Override
    public List<MerchantSort> list(int start, int count) {

        throw new NotImplementedException();
    }
    
}
