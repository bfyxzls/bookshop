package com.lind.bookshop.client;

import com.lind.bookshop.client.fallback.UserClientFallback;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallback = UserClientFallback.class)
public interface UserClient {
  @GetMapping("v1/api/users/{id}")
  Map getUser(@PathVariable("id") Long id);

  @GetMapping("v1/api/users")
  Map getUsers();
}
