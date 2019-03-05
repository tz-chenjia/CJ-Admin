package com.cj.admin.dao;

import com.cj.admin.domain.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserRoleMapper {
    @Delete({
        "delete from cj_user_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cj_user_role (userid, roleid)",
        "values (#{userid,jdbcType=INTEGER}, #{roleid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserRole record);

    @Select({
        "select",
        "id, userid, roleid",
        "from cj_user_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="roleid", property="roleid", jdbcType=JdbcType.INTEGER)
    })
    UserRole selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, userid, roleid",
        "from cj_user_role"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="roleid", property="roleid", jdbcType=JdbcType.INTEGER)
    })
    List<UserRole> selectAll();
    
    @Select({
        "select",
        "id, userid, roleid",
        "from cj_user_role where userid = #{userID, jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="roleid", property="roleid", jdbcType=JdbcType.INTEGER)
    })
    List<UserRole> selectAllByUserID(int userID);

    @Update({
        "update cj_user_role",
        "set userid = #{userid,jdbcType=INTEGER},",
          "roleid = #{roleid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserRole record);
}