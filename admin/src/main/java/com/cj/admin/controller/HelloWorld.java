package com.cj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cj.admin.dao.HelloMapper;
import com.cj.admin.domain.HelloEntity;

@RestController
public class HelloWorld {
	
	@Autowired
	HelloMapper mapper;
	
	@RequestMapping("/hello")
	public HelloEntity hello() {
		HelloEntity hm = mapper.selectById(1);
		return hm;
	}

}
