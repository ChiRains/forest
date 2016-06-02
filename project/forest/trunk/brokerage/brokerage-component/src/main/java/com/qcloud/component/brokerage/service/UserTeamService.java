package com.qcloud.component.brokerage.service;
import java.util.List;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.model.query.UserTeamQuery;
import com.qcloud.pirates.data.Page;
public interface UserTeamService {
    
    public boolean add(UserTeam userTeam);

    public UserTeam get(Long id);

    public boolean delete(Long id);

    public boolean update(UserTeam userTeam);

    public Page<UserTeam> page(UserTeamQuery query, int start, int count);

    public Page<UserTeam> pageLeader(UserTeamQuery query, int start, int count);
    
    public List<UserTeam> listAll();

    public UserTeam getByUserId(long userId);

    public boolean isInLeader(long userId,long leader);
    
    List<UserTeam> listChildren(long leader);
    
    int countChildren(long leader);  
}
