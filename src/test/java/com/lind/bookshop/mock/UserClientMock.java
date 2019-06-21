package com.lind.bookshop.mock;

import com.google.common.collect.ImmutableMap;
import com.lind.bookshop.client.UserClient;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


import static org.mockito.ArgumentMatchers.any;

@Configuration
@Profile("test")
public class UserClientMock {
  @Bean
  @Primary
  public UserClient userClient() throws RuntimeException {
    UserClient client = Mockito.mock(UserClient.class);
    Mockito.when(client.getUser(any()))
        .thenReturn(ImmutableMap.of("message", "success"));

    Mockito.when(client.getUsers()).thenAnswer((Answer) invocation -> {
      Object[] args = invocation.getArguments();
      Thread.sleep(1000);
      return ImmutableMap.of("message", "success");
    });
    return client;
  }
}
