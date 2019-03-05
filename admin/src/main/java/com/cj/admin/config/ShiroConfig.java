package com.cj.admin.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.cj.admin.domain.Organization;
import com.cj.admin.domain.Permission;
import com.cj.admin.domain.Role;
import com.cj.admin.domain.User;
import com.cj.admin.service.IOrganizationService;
import com.cj.admin.service.IRoleService;
import com.cj.admin.service.IUserService; 

@Configuration
public class ShiroConfig {
	
	/**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");

        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/toLogin", "anon");

        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
        // ① authc:所有url都必须认证通过才可以访问; ② anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myRealm());
        return securityManager;
    }
    
    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    @DependsOn({ "lifecycleBeanPostProcessor" })
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}

class MyRealm extends AuthorizingRealm{
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IRoleService roleService;
	
	@Qualifier("IOrganizationService")
	@Autowired
	IOrganizationService orgService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String) SecurityUtils.getSubject().getPrincipal();
		User user = userService.selectByCodeOrName(userName);
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        List<Role> roles = userService.getRoles(user.getId());
        for(Role role : roles) {
        	info.addRole(role.getName());
        	List<Permission> rolePermissions = roleService.getPermissions(role.getId());
        	for(Permission permission : rolePermissions) {
        		info.addStringPermission(permission.getName());
        	}
        }
        List<Organization> organizations = userService.getOrganizations(user.getId());
        for(Organization org : organizations) {
        	info.addRole(org.getName());
        	List<Permission> rolePermissions = orgService.getPermissions(org.getId());
        	for(Permission permission : rolePermissions) {
        		info.addStringPermission(permission.getName());
        	}
        }
        System.out.println("SimpleAuthorizationInfo:" + info.toString());
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("token.getPrincipal:" + token.getPrincipal());
        System.out.println("token.getCredentials:" + token.getCredentials());
        String userName = token.getPrincipal().toString();
        User user = userService.selectByCodeOrName(userName);
        // Object principal, Object credentials, String realmName
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
        return authcInfo;
	}
	
}
