package com.lind.bookshop.Interceptor;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 拦截器，需要在启动类添加注解：@ServletComponentScan.
 */
@WebFilter(urlPatterns = {"/book/*", "/category/*", "/order/list"}, filterName = "adminFilter")
public class AuthFilter extends GenericFilterBean {


  @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    Boolean user = (Boolean) servletRequest.getAttribute("__spring_security_scpf_applied");

    if (!user) {
      servletResponse.setContentType("text/html;charset=utf-8");
      servletResponse.getWriter().write("您没有访问此页面的权限！<a href='/login'>去登录</a>");
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }

  @Override
  public void destroy() {

  }
}
