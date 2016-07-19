package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.project.forest.model.IntegralOrder;

public interface IntegralOrderMapper {

    @Insert("insert into `forest_integral_order`(`id`,`orderNumber`,`time`,`userId`,`sum`,`cash`,`integral`,`unifiedMerchandiseId`,`name`,`image`,`specifications`,`state`,`paymentMode`,`consignee`,`address`,`email`,`mobile`,`remind`,`expressName`,`expressCode`,`expressNumber`)" 
    + " values(#{id},#{orderNumber},#{time},#{userId},#{sum},#{cash},#{integral},#{unifiedMerchandiseId},#{name},#{image},#{specifications},#{state},#{paymentMode},#{consignee},#{address},#{email},#{mobile},#{remind},#{expressName},#{expressCode},#{expressNumber})")
    public void insert(IntegralOrder integralOrder);

    @Select("select * from `forest_integral_order` where `id`=#{id}")
    public IntegralOrder get(Long id);

    @Update("update `forest_integral_order` set `orderNumber`=#{orderNumber},`time`=#{time},`userId`=#{userId},`sum`=#{sum},`cash`=#{cash},`integral`=#{integral},`unifiedMerchandiseId`=#{unifiedMerchandiseId},`name`=#{name},`image`=#{image},`specifications`=#{specifications},`state`=#{state},`paymentMode`=#{paymentMode},`consignee`=#{consignee},`address`=#{address},`email`=#{email},`mobile`=#{mobile},`remind`=#{remind},`expressName`=#{expressName},`expressCode`=#{expressCode},`expressNumber`=#{expressNumber} where `id`=#{id}")
    public void update(IntegralOrder integralOrder);

    @Delete("delete from `forest_integral_order` where `id`=#{id}")
    public void delete(Long id);

    @Select("select * from `forest_integral_order` limit #{start},#{count}")
    public List<IntegralOrder> list4page(Map<String, Object> param);

    @Select("select count(*) from `forest_integral_order`")
    public int count4page(Map<String, Object> param);

    @Select("select * from `forest_integral_order`")
    public List<IntegralOrder> listAll();
}