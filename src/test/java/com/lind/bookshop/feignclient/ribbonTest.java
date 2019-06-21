package com.lind.bookshop.feignclient;

import com.lind.bookshop.client.UserClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 负载平衡和熔断测试.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ribbonTest {
  @Autowired
  UserClient userClient;

  @Test
  public void userApiTest() {
    Assert.assertEquals("success", userClient.getUser(1L).get("message"));
  }

  @Test
  public void usersTest() {
    userClient.getUsers();
  }
}
