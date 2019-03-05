package com.cj.admin.dao;

import com.cj.admin.domain.UserPermission;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserPermissionMapper {
    @Delete({
        "delete from cj_user_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cj_user_permission (userid, permissionid)",
        "values (#{userid,jdbcType=INTEGER}, #{permissionid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserPermission record);

    @Select({
        "select",
        "id, userid, permissionid",
        "from cj_user_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="permissionid", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    UserPermission selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, userid, permissionid",
        "from cj_user_permission"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="permissionid", property="permissionid", jdbcType=JdbcType.INTEGER)
    })
    List<UserPermission> selectAll();

    @Update({
        "update cj_user_permission",
        "set userid = #{userid,jdbcType=INTEGER},",
          "permissionid = #{permissionid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserPermission record);
}