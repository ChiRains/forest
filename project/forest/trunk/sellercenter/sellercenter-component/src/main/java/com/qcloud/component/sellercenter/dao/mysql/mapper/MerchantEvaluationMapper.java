package com.qcloud.component.sellercenter.dao.mysql.mapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.sellercenter.model.MerchantEvaluation;
public interface MerchantEvaluationMapper {
    @Insert("insert into `${table_name}`(`id`,`evaluationId`,`merchantId`,`merchandiseId`,`evaluationTime`)" + " values(#{id},#{evaluationId},#{merchantId},#{merchandiseId},#{evaluationTime})")
    public void insert(MerchantEvaluation merchantEvaluation);

    @Select("select * from `${table_name}` where `id`=#{id}")
    public MerchantEvaluation get(Long id);

    @Update("update `${table_name}` set `evaluationId`=#{evaluationId},`merchantId`=#{merchantId},`merchandiseId`=#{merchandiseId},`evaluationTime`=#{evaluationTime} where `id`=#{id}")
    public void update(MerchantEvaluation merchantEvaluation);

    @Delete("delete from `${table_name}` where `id`=#{id}")
    public void delete(Long id);

    @Select("select * from `${table_name}` limit #{start},#{count}")
    public List<MerchantEvaluation> list4page(Map<String, Object> param);

    @Select("select count(*) from `${table_name}`")
    public int count4page(Map<String, Object> param);

    @Select("select * from `${table_name}`")
    public List<MerchantEvaluation> listAll();
}