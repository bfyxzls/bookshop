package com.lind.bookshop.client.fallback;

import com.lind.bookshop.client.UserClient;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserClientFallback implements UserClient {
  @Override
  public Map getUser(Long id) {
    logger.info("getUser.error");
    return null;
  }

  @Override
  public Map getUsers() {
    logger.info("getUsers.error");
    return null;
  }
}
