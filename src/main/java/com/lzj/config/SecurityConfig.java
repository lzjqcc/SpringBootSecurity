/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lzj.config;

import com.lzj.util.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joe Grandja
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//注意这里指定的都是url
		http
				.authorizeRequests()
					.antMatchers("/css/**", "/index").permitAll()
					.antMatchers("/user/**").hasRole("USER")
					.antMatchers("/admin/**").hasRole("ADMIN")
					.and()
				.formLogin().loginPage("/login").failureUrl("/login-error").and()
				.exceptionHandling().accessDeniedPage("/403");//没有访问权限显示的网页。AccessDeniedHandler用于处理

	}
	// @formatter:on

	// @formatter:off
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("li").password("123").roles("USER")
			.and().withUser("admin").password("admin").roles("ADMIN");

	}
	// @formatter:on
	//ThymeleafViewResolver

	@Override
	protected UserDetailsService userDetailsService() {
		MyUserDetailsService userDetailsService=new MyUserDetailsService();
		return userDetailsService;
	}
	@Bean
	public MyAccessDesisionManager getAccessDesisionManager(){
		MyAccessDesisionManager accessDesisionManager=new MyAccessDesisionManager();
		return accessDesisionManager;
	}
	@Bean
	public MyMetadataSource getMetedataSources(){
		MyMetadataSource metadataSource=new MyMetadataSource();
		return metadataSource;
	}
	@Bean
	@Autowired
	public MyAuthenticationFilter getAuthenticationFilter(AuthenticationManager manager){
		MyAuthenticationFilter authenticationFilter=new MyAuthenticationFilter();
		authenticationFilter.setAuthenticationManager(manager);
		return authenticationFilter;
	}
	@Bean
	@Autowired
	public ProviderManager getProviderManager(MyDaoAuthenticationProvider provider){
		List<AuthenticationProvider>providers=new ArrayList<AuthenticationProvider>();
		providers.add(provider);
		ProviderManager providerManager=new ProviderManager(providers);
		return providerManager;
	}
	@Bean
	public MyDaoAuthenticationProvider getAuthenticationProvider(){
		MyDaoAuthenticationProvider authenticationProvider=new MyDaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(new MyUserDetailsService());
		return authenticationProvider;

	}
	@Bean
	@Autowired
	public MySecurityInterceptor getSecurityInterceptor(FilterInvocationSecurityMetadataSource metadataSource,MyAccessDesisionManager accessDesisionManager,
														ProviderManager manager){
		MySecurityInterceptor securityInterceptor=new MySecurityInterceptor();
		securityInterceptor.setSecurityMetadataSource(metadataSource);
		securityInterceptor.setAccessDecisionManager(accessDesisionManager);
		securityInterceptor.setAuthenticationManager(manager);
		return securityInterceptor;
	}

}
