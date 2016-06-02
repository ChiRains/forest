package com.qcloud.component.personalcenter.dao.mysql;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;
import com.qcloud.component.personalcenter.dao.UserDao;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.query.UserQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.sql.mybatis.SqlOperator;
import com.qcloud.pirates.util.StringUtil;
@Repository
public class UserDaoMysqlImpl implements UserDao {
    @Resource(name = "sqlOperator-personalcenter")
    private SqlOperator sqlOperator;

    @Override
    public boolean add(User user) {
        return sqlOperator.insert("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.insert", user) == 1;
    }

    @Override
    public User get(Long id) {
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.get", id);
    }

    @Override
    public boolean delete(Long id) {
        return sqlOperator.delete("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.delete", id) > 0;
    }

    @Override
    public boolean update(User user) {
        return sqlOperator.update("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.update", user) > 0;
    }

    @Override
    public List<User> list(List<Long> idList) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Long, User> map(Set<Long> idSet) {
        throw new NotImplementedException();
    }

    @Override
    public Page<User> page(int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        List<User> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.list4page", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.count4page", param);
        Page<User> page = new Page<User>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public Page<User> page(UserQuery query, int start, int count) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("start", start);
        param.put("count", count);
        param.put("id", query.getId());
        param.put("name", StringUtil.emptyToNull(query.getName()));
        List<User> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.list4query2", param);
        int total = sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.count4query2", param);
        Page<User> page = new Page<User>();
        page.setCount(total);
        page.setData(list);
        return page;
    }

    @Override
    public List<User> listAll() {
        List<User> list = sqlOperator.selectList("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.listAll");
        return list;
    }
 
    @Override
    public User getByMobile(String mobile) {
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.getByMobile", mobile);
    }

    @Override
    public User getByEmail(String email) {
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.getByEmail", email);
    }

    @Override
    public User getByMembershipCard(String membershipCard) {
        return sqlOperator.selectOne("com.qcloud.component.personalcenter.dao.mysql.mapper.UserMapper.getByMembershipCard", membershipCard);
    }
}
