package com.qcloud.component.organization.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.organization.model.DepartmentImage;

public interface DepartmentImageMapper {

    @Insert("insert into `organization_department_image`(`id`,`departmentId`,`image`,`orderNum`)" + " values(#{id},#{departmentId},#{image},#{orderNum})")
    public void insert(DepartmentImage departmentImage);

    @Select("select * from `organization_department_image` where `id`=#{id}")
    public DepartmentImage get(Long id);

    @Update("update `organization_department_image` set `departmentId`=#{departmentId},`image`=#{image},`orderNum`=#{orderNum} where `id`=#{id}")
    public void update(DepartmentImage departmentImage);

    @Delete("delete from `organization_department_image` where `id`=#{id}")
    public void delete(Long id);

    @Select("select * from `organization_department_image` limit #{start},#{count}")
    public List<DepartmentImage> list4page(Map<String, Object> param);

    @Select("select count(*) from `organization_department_image`")
    public int count4page(Map<String, Object> param);

    @Select("select * from `organization_department_image`")
    public List<DepartmentImage> listAll();
}