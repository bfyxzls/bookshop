package com.lind.bookshop.h2;

import com.lind.bookshop.entity.UserInfo;
import com.lind.bookshop.mapper.UserInfoMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class selectTest {
  @Autowired
  UserInfoMapper userInfoMapper;

  @Test
  public void dateFun() {
    userInfoMapper.insert(UserInfo.builder()
        .username("zzl")
        .realname("zhang")
        .age(35)
        .email("bfyxzs?@sona.com")
        .password("zzl")
        .telephone("13521972912")
        .build());
    List<UserInfo> userInfoList = userInfoMapper.findUsers();
    System.out.println(userInfoList);
  }
}
