package com.lind.bookshop.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProfileTest {
  @Value("${author:zzl}")
  private String authorName;

  @Value("${cache.expire:60}")
  private String cacheExpire;

  @Test
  public void author() {
    Assert.assertEquals("zzl-test", authorName);
  }

  @Test
  public void cache() {
    Assert.assertEquals("120", cacheExpire);
  }
}
