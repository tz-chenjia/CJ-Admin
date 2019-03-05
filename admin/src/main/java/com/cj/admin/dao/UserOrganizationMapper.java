package com.cj.admin.dao;

import com.cj.admin.domain.UserOrganization;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserOrganizationMapper {
    @Delete({
        "delete from cj_user_organization",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cj_user_organization (userid, organizationid)",
        "values (#{userid,jdbcType=INTEGER}, #{organizationid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserOrganization record);

    @Select({
        "select",
        "id, userid, organizationid",
        "from cj_user_organization",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="organizationid", property="organizationid", jdbcType=JdbcType.INTEGER)
    })
    UserOrganization selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, userid, organizationid",
        "from cj_user_organization"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="organizationid", property="organizationid", jdbcType=JdbcType.INTEGER)
    })
    List<UserOrganization> selectAll();
    
    @Select({
        "select",
        "id, userid, organizationid",
        "from cj_user_organization where userid = #{userID,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="organizationid", property="organizationid", jdbcType=JdbcType.INTEGER)
    })
    List<UserOrganization> selectAllByUserID(int userID);

    @Update({
        "update cj_user_organization",
        "set userid = #{userid,jdbcType=INTEGER},",
          "organizationid = #{organizationid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserOrganization record);
}