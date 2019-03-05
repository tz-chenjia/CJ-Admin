package com.cj.admin.dao;

import com.cj.admin.domain.OrganizationPermission;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface OrganizationPermissionMapper {
    @Delete({
        "delete from cj_organization_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cj_organization_permission (organizationid, permissionid)",
        "values (#{organizationid,jdbcType=INTEGER}, #{permissionid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(OrganizationPermission record);

    @Select({
        "select",
        "id, organizationid, permissionid",
        "from cj_organization_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="organizationid", property="organizationid", jdbcType=JdbcType.INTEGER),
        @Result(column="permissionid", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    OrganizationPermission selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, organizationid, permissionid",
        "from cj_organization_permission"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="organizationid", property="organizationid", jdbcType=JdbcType.INTEGER),
        @Result(column="permissionid", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    List<OrganizationPermission> selectAll();
    
    @Select({
        "select",
        "id, organizationid, permissionid",
        "from cj_organization_permission where organizationid = #{orgID,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="organizationid", property="organizationid", jdbcType=JdbcType.INTEGER),
        @Result(column="permissionid", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    List<OrganizationPermission> selectAllByOrgID(int orgID);

    @Update({
        "update cj_organization_permission",
        "set organizationid = #{organizationid,jdbcType=INTEGER},",
          "permissionid = #{permissionid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrganizationPermission record);
}