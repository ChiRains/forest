package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.project.forest.model.ExpressQueryHistory;

public interface ExpressQueryHistoryMapper {

    @Insert("insert into `forest_express_query_history`(`id`,`expressName`,`expressNum`,`userId`,`expressCode`,`time`)" + " values(#{id},#{expressName},#{expressNum},#{userId},#{expressCode},#{time})")
    public void insert(ExpressQueryHistory expressQueryHistory);

    @Select("select * from `forest_express_query_history` where `id`=#{id}")
    public ExpressQueryHistory get(Long id);

    @Update("update `forest_express_query_history` set `expressName`=#{expressName},`expressNum`=#{expressNum},`userId`=#{userId},`expressCode`=#{expressCode},`time`=#{time} where `id`=#{id}")
    public void update(ExpressQueryHistory expressQueryHistory);

    @Delete("delete from `forest_express_query_history` where `id`=#{id}")
    public void delete(Long id);

    @Select("select * from `forest_express_query_history` limit #{start},#{count}")
    public List<ExpressQueryHistory> list4page(Map<String, Object> param);

    @Select("select count(*) from `forest_express_query_history`")
    public int count4page(Map<String, Object> param);

    @Select("select * from `forest_express_query_history`")
    public List<ExpressQueryHistory> listAll();
}