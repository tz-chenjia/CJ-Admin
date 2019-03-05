package com.cj.admin.dao;

import com.cj.admin.domain.RoleOrganization;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface RoleOrganizationMapper {
    @Delete({
        "delete from cj_role_organization",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cj_role_organization (roleid, organizationid)",
        "values (#{roleid,jdbcType=INTEGER}, #{organizationid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(RoleOrganization record);

    @Select({
        "select",
        "id, roleid, organizationid",
        "from cj_role_organization",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType=JdbcType.INTEGER),
        @Result(column="organizationid", property="organizationid", jdbcType=JdbcType.INTEGER)
    })
    RoleOrganization selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, roleid, organizationid",
        "from cj_role_organization"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType=JdbcType.INTEGER),
        @Result(column="organizationid", property="organizationid", jdbcType=JdbcType.INTEGER)
    })
    List<RoleOrganization> selectAll();

    @Update({
        "update cj_role_organization",
        "set roleid = #{roleid,jdbcType=INTEGER},",
          "organizationid = #{organizationid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoleOrganization record);
}