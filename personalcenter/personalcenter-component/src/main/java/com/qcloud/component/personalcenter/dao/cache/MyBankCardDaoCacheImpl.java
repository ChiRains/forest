package com.qcloud.component.personalcenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.MyBankCardDao;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.query.MyBankCardQuery;

@Repository
public class MyBankCardDaoCacheImpl implements MyBankCardDao {
	
	@Autowired
	private MyBankCardDao myBankCardDaoMysqlImpl;
	
//	@Autowired
//	private MyBankCardDao myBankCardDaoRedisImpl;

	@Override
	public boolean add(MyBankCard myBankCard) {
		return myBankCardDaoMysqlImpl.add(myBankCard);
	}

	@Override
	public MyBankCard get(Long id) {
	    return myBankCardDaoMysqlImpl.get(id);
//		return CacheLoader.get(myBankCardDaoRedisImpl, myBankCardDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return myBankCardDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(MyBankCard myBankCard){
		return myBankCardDaoMysqlImpl.update(myBankCard);
	}
	
	@Override
	public List<MyBankCard> list(List<Long> idList) {
		return CacheLoader.list(myBankCardDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, MyBankCard> map(Set<Long> idSet){
		return CacheLoader.map(myBankCardDaoMysqlImpl, idSet);
	}

	@Override
	public Page<MyBankCard> page(int start, int count){
		return myBankCardDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<MyBankCard> page(MyBankCardQuery query, int start, int count){
		return myBankCardDaoMysqlImpl.page(query, start, count);
	}
	
	public List<MyBankCard> listAll(){
		return myBankCardDaoMysqlImpl.listAll();
	}

    @Override
    public List<MyBankCard> listByUser(long userId) {
        return myBankCardDaoMysqlImpl.listByUser(userId);
    }
}

