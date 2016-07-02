package com.qcloud.component.personalcenter.dao.cache;

import java.util.Map;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qcloud.pirates.data.CacheLoader;
import com.qcloud.pirates.data.Page;
import com.qcloud.component.personalcenter.dao.UserThirdDao;
import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.query.UserThirdQuery;

@Repository
public class UserThirdDaoCacheImpl implements UserThirdDao {
	
	@Autowired
	private UserThirdDao userThirdDaoMysqlImpl;
	
//	@Autowired
//	private UserThirdDao userThirdDaoRedisImpl;

	@Override
	public boolean add(UserThird userThird) {
		return userThirdDaoMysqlImpl.add(userThird);
	}

	@Override
	public UserThird get(Long id) {
	    return userThirdDaoMysqlImpl.get(id);
//		return CacheLoader.get(userThirdDaoRedisImpl, userThirdDaoMysqlImpl, id);
	}

	@Override
	public boolean delete(Long id){
		return userThirdDaoMysqlImpl.delete(id);
	}
	
	@Override
	public boolean update(UserThird userThird){
		return userThirdDaoMysqlImpl.update(userThird);
	}
	
	@Override
	public List<UserThird> list(List<Long> idList) {
		return CacheLoader.list(userThirdDaoMysqlImpl, idList);
	}

	@Override
	public Map<Long, UserThird> map(Set<Long> idSet){
		return CacheLoader.map(userThirdDaoMysqlImpl, idSet);
	}

	@Override
	public Page<UserThird> page(int start, int count){
		return userThirdDaoMysqlImpl.page(start, count);
	}
	
	@Override
	public Page<UserThird> page(UserThirdQuery query, int start, int count){
		return userThirdDaoMysqlImpl.page(query, start, count);
	}
	
	public List<UserThird> listAll(){
		return userThirdDaoMysqlImpl.listAll();
	}

    @Override
    public UserThird getByThird(String thirdId, AccountType type) {
         return userThirdDaoMysqlImpl.getByThird(thirdId, type);
    }

    @Override
    public UserThird getByUser(Long userId) {       
        return userThirdDaoMysqlImpl.getByUser(userId);
    }
}

