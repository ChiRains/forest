package com.qcloud.component.seckill.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.seckill.dao.MerchandiseSeckillDao;
import com.qcloud.component.seckill.model.MerchandiseSeckill;
import com.qcloud.component.seckill.model.query.MerchandiseSeckillQuery;

@Repository
public class MerchandiseSeckillDaoMysqlImpl implements MerchandiseSeckillDao {

    @Resource(name = "sqlOperator-seckill")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(MerchandiseSeckill merchandiseSeckill) {

        return sqlOperator.insert("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.insert", merchandiseSeckill) == 1;
    }

    @Override
    public MerchandiseSeckill get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.delete", id) > 0;
    }

    @Override
    public boolean update(MerchandiseSeckill merchandiseSeckill) {

        return sqlOperator.update("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.update", merchandiseSeckill) > 0;
    }

    @Override
    public List<MerchandiseSeckill> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, MerchandiseSeckill> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<MerchandiseSeckill> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<MerchandiseSeckill> list = sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.count4page", param);
        Page<MerchandiseSeckill> page = new Page<MerchandiseSeckill>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<MerchandiseSeckill> page(MerchandiseSeckillQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("screeningsId", query.getScreeningsId());
        param.put("merchandiseName", query.getName());
        List<MerchandiseSeckill> list = sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.count4query", param);
        Page<MerchandiseSeckill> page = new Page<MerchandiseSeckill>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<MerchandiseSeckill> listAll() {

        List<MerchandiseSeckill> list = sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.listAll");
        return list;
    }

    @Override
    public List<MerchandiseSeckill> listByScreenings(long screeningsId) {
        List<MerchandiseSeckill> list = sqlOperator.selectList(
                "com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.listByScreenings", screeningsId);
        return list;
    }

	@Override
	public List<MerchandiseSeckill> listByScreeningsAndQUnifiedMerchandiseId(
			long screeningsId, long qUnifiedMerchandiseId) {
		 Map<String, Object> param = new HashMap<String, Object>();
	        param.put("screeningsId", screeningsId);
	        param.put("qUnifiedMerchandiseId", qUnifiedMerchandiseId);
		List<MerchandiseSeckill> list = sqlOperator.selectList("com.qcloud.component.seckill.dao.mysql.mapper.MerchandiseSeckillMapper.listByScreeningsAndQUnifiedMerchandiseId", param);
        return list;
	}
    
    
}
