package com.lind.bookshop.client.fallback;

import com.google.common.collect.ImmutableMap;
import com.lind.bookshop.client.UserClient;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 降级回退类.
 */
@Component
@Slf4j
public class UserClientFallback implements UserClient {
  @Override
  public Map getUser(Long id) {
    logger.info("feign.getUser.error");
    return null;
  }

  @Override
  public Map getUsers() {
    logger.info("feign.getUsers.error.retrun.default");
    return ImmutableMap.of("name", "lind", "sex", "male");
  }
}
