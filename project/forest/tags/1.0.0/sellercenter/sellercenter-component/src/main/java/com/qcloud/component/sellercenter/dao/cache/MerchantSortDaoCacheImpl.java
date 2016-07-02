package com.qcloud.component.sellercenter.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.component.sellercenter.dao.MerchantSortDao;
import com.qcloud.component.sellercenter.model.MerchantSort;
import com.qcloud.component.sellercenter.model.query.MerchantSortQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class MerchantSortDaoCacheImpl implements MerchantSortDao {
    
    @Autowired
    private MerchantSortDao merchantSortDaoMysqlImpl;

    @Override
    public boolean add(MerchantSort merchantSort) {
        
        return merchantSortDaoMysqlImpl.add(merchantSort);
    }

    @Override
    public MerchantSort get(Long id) {

        return merchantSortDaoMysqlImpl.get(id);
    }

    @Override
    public boolean delete(Long id) {

        return merchantSortDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(MerchantSort merchantSort) {

        return merchantSortDaoMysqlImpl.update(merchantSort);
    }

    @Override
    public List<MerchantSort> list(List<Long> idList) {

        return merchantSortDaoMysqlImpl.list(idList);
    }

    @Override
    public Map<Long, MerchantSort> map(Set<Long> idSet) {

        return merchantSortDaoMysqlImpl.map(idSet);
    }
    
    @Override
    public Page<MerchantSort> page(int start, int count) {

        return merchantSortDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<MerchantSort> page(MerchantSortQuery query, int start, int count) {

        return merchantSortDaoMysqlImpl.page(query, start, count);
    }

    @Override
    public List<MerchantSort> listAll() {

        return merchantSortDaoMysqlImpl.listAll();
    }

    @Override
    public List<MerchantSort> list(int start, int count) {

        return merchantSortDaoMysqlImpl.list(start, count);
    }

}
