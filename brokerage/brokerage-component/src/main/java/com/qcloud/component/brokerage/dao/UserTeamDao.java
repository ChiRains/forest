package com.qcloud.component.brokerage.dao;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.model.query.UserTeamQuery;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.data.api.ISimpleDao;
public interface UserTeamDao extends ISimpleDao<UserTeam, Long> {
    
    public boolean add(UserTeam userTeam);

    public UserTeam get(Long id);

    public boolean delete(Long id);

    public boolean update(UserTeam userTeam);

    public List<UserTeam> list(List<Long> idList);

    public Map<Long, UserTeam> map(Set<Long> idSet);

    public Page<UserTeam> page(UserTeamQuery query, int start, int size);

    public List<UserTeam> listAll();

    UserTeam getByUserId(Long userId);

    List<UserTeam> listChildren(long leader);
    
    int countChildren(long leader);  
    
    Page<UserTeam> pageLeader(UserTeamQuery query, int start, int count);
}
