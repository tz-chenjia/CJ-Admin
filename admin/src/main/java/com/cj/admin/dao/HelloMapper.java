package com.cj.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cj.admin.domain.HelloEntity;

@Mapper
public interface HelloMapper {

	@Select("select * from hello where id = #{id}")
	HelloEntity selectById(@Param("id") int id);
	
}
