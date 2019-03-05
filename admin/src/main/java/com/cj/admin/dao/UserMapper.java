package com.cj.admin.dao;

import com.cj.admin.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @Delete({
        "delete from cj_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cj_user (name, code, ",
        "status, createtime, ",
        "updatetime, optid, ",
        "password)",
        "values (#{name,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{updatetime,jdbcType=TIMESTAMP}, #{optid,jdbcType=INTEGER}, ",
        "#{password,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(User record);

    @Select({
        "select",
        "id, name, code, status, createtime, updatetime, optid, password",
        "from cj_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="optid", property="optid", jdbcType=JdbcType.INTEGER),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Integer id);
    
    @Select({
        "select",
        "id, name, code, status, createtime, updatetime, optid, password",
        "from cj_user",
        "where code = #{codeOrName,jdbcType=VARCHAR} or name = #{codeOrName,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="optid", property="optid", jdbcType=JdbcType.INTEGER),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    User selectByCodeOrName(String codeOrName);

    @Select({
        "select",
        "id, name, code, status, createtime, updatetime, optid, password",
        "from cj_user"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="optid", property="optid", jdbcType=JdbcType.INTEGER),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update cj_user",
        "set name = #{name,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "updatetime = #{updatetime,jdbcType=TIMESTAMP},",
          "optid = #{optid,jdbcType=INTEGER},",
          "password = #{password,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}