package com.qcloud.component.personalcenter.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang.NotImplementedException;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.component.personalcenter.dao.UserThirdDao;
import com.qcloud.component.personalcenter.model.UserThird;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.query.UserThirdQuery;

@Repository
public class UserThirdDaoMysqlImpl implements UserThirdDao {

    @Resource(name = "sqlOperator-personalcenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(UserThird userThird) {

        return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.insert", userThird) == 1;
    }

    @Override
    public UserThird get(Long id) {

        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {

        return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.delete", id) > 0;
    }

    @Override
    public boolean update(UserThird userThird) {

        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.update", userThird) > 0;
    }

    @Override
    public List<UserThird> list(List<Long> idList) {

        throw new NotImplementedException();
    }

    @Override
    public Map<Long, UserThird> map(Set<Long> idSet) {

        throw new NotImplementedException();
    }

    @Override
    public Page<UserThird> page(int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UserThird> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.count4page", param);
        Page<UserThird> page = new Page<UserThird>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<UserThird> page(UserThirdQuery query, int start, int count) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<UserThird> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.list4query", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.count4query", param);
        Page<UserThird> page = new Page<UserThird>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<UserThird> listAll() {

        List<UserThird> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.listAll");
        return list;
    }

    @Override
    public UserThird getByThird(String thirdId, AccountType type) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("thirdId", thirdId);
        param.put("accountType", type.getKey());
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.getByThird", param);
    }

    @Override
    public UserThird getByUser(Long userId) {
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserThirdMapper.getByUser", userId);
    }
}
