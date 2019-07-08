package com.lind.bookshop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LindAuthenticationFailHandler
    implements AuthenticationFailureHandler {
  @Autowired
  ObjectMapper objectMapper;

  /**
   * Called when an authentication attempt fails.
   *
   * @param request   the request during which the authentication attempt occurred.
   * @param response  the response.
   * @param exception the exception which was thrown to reject the authentication
   */
  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    String jsonMessage = objectMapper.writeValueAsString(
        new HashMap<String, String>() {{
          put("message", exception.getMessage());
        }});
    response.getWriter().append(jsonMessage);
    logger.info("fail,writeResponse,responseBody={}", jsonMessage);
  }
}
