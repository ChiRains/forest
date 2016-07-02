package com.qcloud.component.publicdata.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.publicdata.dao.ClassifyBsidMaxNumberDao;
import com.qcloud.component.publicdata.model.ClassifyBsidMaxNumber;
import com.qcloud.component.publicdata.model.query.ClassifyBsidMaxNumberQuery;
import com.qcloud.pirates.data.Page;

@Repository
public class ClassifyBsidMaxNumberDaoRedisImpl implements ClassifyBsidMaxNumberDao {

	//@Resource(name = "redis-publicdata")
	//private Redis redis;

	@Override
	public boolean add(ClassifyBsidMaxNumber classifyBsidMaxNumber) {			
		throw new NotImplementedException();
	}

	@Override
	public ClassifyBsidMaxNumber get(Long id) {		
		throw new NotImplementedException();
	}
	
	@Override
	public boolean delete(Long id){
		throw new NotImplementedException();
	}
	
	@Override
	public boolean update(ClassifyBsidMaxNumber classifyBsidMaxNumber){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ClassifyBsidMaxNumber> list(List<Long> idList) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, ClassifyBsidMaxNumber> map(Set<Long> idSet){
		throw new NotImplementedException();
	}
		
	@Override
	public Page<ClassifyBsidMaxNumber> page(int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public Page<ClassifyBsidMaxNumber> page(ClassifyBsidMaxNumberQuery query, int start, int count){
		throw new NotImplementedException();
	}
	
	@Override
	public List<ClassifyBsidMaxNumber> listAll(){	
		throw new NotImplementedException();
	}

    @Override
    public ClassifyBsidMaxNumber getByParentClassify(long parentClassifyId, long type) {
        throw new NotImplementedException();
    }

    @Override
    public void lockNextBsid(ClassifyBsidMaxNumber classifyBsidMaxNumber) {
        throw new NotImplementedException();
    }
}

