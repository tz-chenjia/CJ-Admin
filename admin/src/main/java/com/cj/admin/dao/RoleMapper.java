package com.cj.admin.dao;

import com.cj.admin.domain.Role;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface RoleMapper {
    @Delete({
        "delete from cj_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cj_role (code, name, ",
        "status, parentid, ",
        "createtime, updatetime, ",
        "optid)",
        "values (#{code,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{parentid,jdbcType=INTEGER}, ",
        "#{createtime,jdbcType=TIMESTAMP}, #{updatetime,jdbcType=TIMESTAMP}, ",
        "#{optid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Role record);

    @Select({
        "select",
        "id, code, name, status, parentid, createtime, updatetime, optid",
        "from cj_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="parentid", property="parentid", jdbcType=JdbcType.INTEGER),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="optid", property="optid", jdbcType=JdbcType.INTEGER)
    })
    Role selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, code, name, status, parentid, createtime, updatetime, optid",
        "from cj_role"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="parentid", property="parentid", jdbcType=JdbcType.INTEGER),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="optid", property="optid", jdbcType=JdbcType.INTEGER)
    })
    List<Role> selectAll();

    @Update({
        "update cj_role",
        "set code = #{code,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "parentid = #{parentid,jdbcType=INTEGER},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "updatetime = #{updatetime,jdbcType=TIMESTAMP},",
          "optid = #{optid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);
}