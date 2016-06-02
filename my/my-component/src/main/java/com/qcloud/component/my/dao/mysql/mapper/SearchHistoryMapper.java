package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.SearchHistory;
import com.qcloud.component.my.model.query.SearchHistoryQuery;

public interface SearchHistoryMapper {

	@Insert("insert into `my_search_history`(`id`,`keywords`,`time`,`userId`,`type`)"
			+ " values(#{id},#{keywords},#{time},#{userId},#{type})")
	public void insert(SearchHistory searchHistory);

	@Select("select * from `my_search_history` where `id`=#{id}")
	public SearchHistory get(Long id);

	@Update("update `my_search_history` set `keywords`=#{keywords},`time`=#{time},`userId`=#{userId},`type`=#{type} where `id`=#{id}")
	public void update(SearchHistory searchHistory);

	@Delete("delete from `my_search_history` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_search_history` limit #{start},#{count}")
	public List<SearchHistory> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_search_history`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_search_history`")
	public List<SearchHistory> listAll();
}