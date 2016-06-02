package com.qcloud.component.orderform.dao.cache;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.orderform.dao.RemindRecordDao;
import com.qcloud.component.orderform.model.RemindRecord;
import com.qcloud.component.orderform.model.query.RemindRecordQuery;

@Repository
public class RemindRecordDaoCacheImpl implements RemindRecordDao {
	
	@Autowired
	private RemindRecordDao remindRecordDaoMysqlImpl;
	
//	@Autowired
//	private RemindRecordDao remindRecordDaoRedisImpl;

	@Override
	public boolean add(RemindRecord remindRecord) {
		return remindRecordDaoMysqlImpl.add(remindRecord);
	}

	@Override
	public RemindRecord get(Long id) {
	    return remindRecordDaoMysqlImpl.get(id);
//		return CacheLoader.get(remindRecordDaoRedisImpl, remindRecordDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return remindRecordDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(RemindRecord remindRecord){
		return remindRecordDaoMysqlImpl.update(remindRecord);
	}
	
	@Override
	public List<RemindRecord> list(List<Long> idList) {
		return CacheLoader.list(remindRecordDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, RemindRecord> map(Set<Long> idSet){
		return CacheLoader.map(remindRecordDaoMysqlImpl, idSet);
	}

	@Override
	public Page<RemindRecord> page(int start, int count){
		return remindRecordDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<RemindRecord> page(RemindRecordQuery query, int start, int count){
		return remindRecordDaoMysqlImpl.page(query, start, count);
	}
	
	public List<RemindRecord> listAll(){
		return remindRecordDaoMysqlImpl.listAll();
	}

    @Override
    public boolean canRemind(long subOrderId, Date orderDate, int splitMinutes) {
        return remindRecordDaoMysqlImpl.canRemind(subOrderId, orderDate, splitMinutes);
    }
}

