package com.cj.admin.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PortalController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@ResponseBody
	@RequestMapping(value = "/toLogin", method = RequestMethod.POST)
	public String toLogin(@RequestParam("userName")String userName, @RequestParam("password")String password) {
		System.out.println("userName："+userName);
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
	        // 把用户名和密码封装为 UsernamePasswordToken 对象
	        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
	        // rememberme
	        //token.setRememberMe(true);
	        try {
	            System.out.println("UsernamePasswordToken:");
	            System.out.println("hashCode:" + token.hashCode());
	            System.out.println("Principal:" + token.getPrincipal());
	            System.out.println("Credentials:" + String.valueOf((char[]) token.getCredentials()));
	            System.out.println("host:" + token.getHost());
	            System.out.println("Username:" + token.getUsername());
	            System.out.println("Password:" + String.valueOf(token.getPassword()));
	            // 执行登录.
	            currentUser.login(token);
	        }
	        // ... catch more exceptions here (maybe custom ones specific to your application?
	        // 所有认证时异常的父类.
	        catch (AuthenticationException ae) {
	            //unexpected condition?  error?
	            System.out.println("login failed :" + ae.getMessage());
	        }
	    }
	    return "redirect:/index";
	}

	@RequestMapping("/index")
	public String indexPage() {
		return "index";
	}

}
