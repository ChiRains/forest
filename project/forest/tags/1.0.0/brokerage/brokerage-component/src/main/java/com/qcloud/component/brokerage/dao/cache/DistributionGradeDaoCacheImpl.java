package com.qcloud.component.brokerage.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.brokerage.dao.DistributionGradeDao;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.query.DistributionGradeQuery;

@Repository
public class DistributionGradeDaoCacheImpl implements DistributionGradeDao {

    @Autowired
    private DistributionGradeDao distributionGradeDaoMysqlImpl;

    // @Autowired
    // private DistributionGradeDao distributionGradeDaoRedisImpl;
    @Override
    public boolean add(DistributionGrade distributionGrade) {

        return distributionGradeDaoMysqlImpl.add(distributionGrade);
    }

    @Override
    public DistributionGrade get(Long id) {

        return distributionGradeDaoMysqlImpl.get(id);
        // return CacheLoader.get(distributionGradeDaoRedisImpl, distributionGradeDaoMysqlImpl, id);
    }

    @Override
    public boolean delete(Long id) {

        return distributionGradeDaoMysqlImpl.delete(id);
    }

    @Override
    public boolean update(DistributionGrade distributionGrade) {

        return distributionGradeDaoMysqlImpl.update(distributionGrade);
    }

    @Override
    public List<DistributionGrade> list(List<Long> idList) {

        return CacheLoader.list(distributionGradeDaoMysqlImpl, idList);
    }

    @Override
    public Map<Long, DistributionGrade> map(Set<Long> idSet) {

        return CacheLoader.map(distributionGradeDaoMysqlImpl, idSet);
    }

    @Override
    public Page<DistributionGrade> page(int start, int count) {

        return distributionGradeDaoMysqlImpl.page(start, count);
    }

    @Override
    public Page<DistributionGrade> page(DistributionGradeQuery query, int start, int count) {

        return distributionGradeDaoMysqlImpl.page(query, start, count);
    }

    public List<DistributionGrade> listAll() {

        return distributionGradeDaoMysqlImpl.listAll();
    }

    @Override
    public DistributionGrade getDefault() {
        
        return distributionGradeDaoMysqlImpl.getDefault();
    }
}
