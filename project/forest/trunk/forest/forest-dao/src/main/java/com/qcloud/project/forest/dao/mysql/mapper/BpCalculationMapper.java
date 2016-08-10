package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.project.forest.model.BpCalculation;

public interface BpCalculationMapper {

    @Insert("insert into `forest_bp_calculation`(`id`,`name`,`description`,`dbpMax`,`dbpMin`,`sbpMax`,`sbpMin`)" + " values(#{id},#{name},#{description},#{dbpMax},#{dbpMin},#{sbpMax},#{sbpMin})")
    public void insert(BpCalculation bpCalculation);

    @Select("select * from `forest_bp_calculation` where `id`=#{id}")
    public BpCalculation get(Long id);

    @Update("update `forest_bp_calculation` set `name`=#{name},`description`=#{description},`dbpMax`=#{dbpMax},`dbpMin`=#{dbpMin},`sbpMax`=#{sbpMax},`sdpMin`=#{sbpMin} where `id`=#{id}")
    public void update(BpCalculation bpCalculation);

    @Delete("delete from `forest_bp_calculation` where `id`=#{id}")
    public void delete(Long id);

    @Select("select * from `forest_bp_calculation` limit #{start},#{count}")
    public List<BpCalculation> list4page(Map<String, Object> param);

    @Select("select count(*) from `forest_bp_calculation`")
    public int count4page(Map<String, Object> param);

    @Select("select * from `forest_bp_calculation`")
    public List<BpCalculation> listAll();
}