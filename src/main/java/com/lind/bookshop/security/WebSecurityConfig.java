package com.lind.bookshop.security;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置.
 *
 * @EnableWebMvcSecurity 注解开启Spring Security的功能.
 * @EnableGlobalMethodSecurity 注解表示开启@PreAuthorize,@PostAuthorize,@Secured.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  LindAuthenticationSuccessHandler lindAuthenticationSuccessHandler;
  @Autowired
  LindAuthenticationFailHandler lindAuthenticationFailHandler;
  @Autowired
  LindAuthenticationProvider lindAuthenticationProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/", "/news", "/recommends", "/img/**", "/register", "/mgr/**", "/actuator/**", "/api/v1/**").permitAll()
        .antMatchers("/book/create").hasRole("USER")//按路由授权
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/index")//默认登录成功后跳转的页面s
        .successHandler(lindAuthenticationSuccessHandler)
        .failureHandler(lindAuthenticationFailHandler)
        .permitAll()
        .and()
        .addFilterAt(lindAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .and()
        .logout()
        .permitAll();
  }

  /**
   * 自定义的Filter.
   *
   * @return
   */
  @Bean
  LindUserNameAuthenticationFilter lindAuthenticationFilter() {
    LindUserNameAuthenticationFilter lindUserNameAuthenticationFilter =
        new LindUserNameAuthenticationFilter();
    ProviderManager providerManager =
        new ProviderManager(Collections.singletonList(lindAuthenticationProvider));
    lindUserNameAuthenticationFilter.setAuthenticationManager(providerManager);
    lindUserNameAuthenticationFilter.setAuthenticationSuccessHandler(
        lindAuthenticationSuccessHandler);
    lindUserNameAuthenticationFilter.setAuthenticationFailureHandler(
        lindAuthenticationFailHandler);
    return lindUserNameAuthenticationFilter;
  }

}
