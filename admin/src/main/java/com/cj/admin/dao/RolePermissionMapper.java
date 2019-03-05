package com.cj.admin.dao;

import com.cj.admin.domain.RolePermission;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface RolePermissionMapper {
    @Delete({
        "delete from cj_role_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cj_role_permission (roleid, permissionid)",
        "values (#{roleid,jdbcType=INTEGER}, #{permissionid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(RolePermission record);

    @Select({
        "select",
        "id, roleid, permissionid",
        "from cj_role_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType=JdbcType.INTEGER),
        @Result(column="permissionid", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    RolePermission selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, roleid, permissionid",
        "from cj_role_permission"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType=JdbcType.INTEGER),
        @Result(column="permissionid", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    List<RolePermission> selectAll();
    
    @Select({
        "select",
        "id, roleid, permissionid",
        "from cj_role_permission where roleid = #{roleID,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleid", property="roleid", jdbcType=JdbcType.INTEGER),
        @Result(column="permissionid", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    List<RolePermission> selectAllByRoleID(int roleID);

    @Update({
        "update cj_role_permission",
        "set roleid = #{roleid,jdbcType=INTEGER},",
          "permissionid = #{permissionid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RolePermission record);
}