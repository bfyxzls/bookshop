package com.lind.bookshop.security;

import com.lind.bookshop.entity.UserInfo;
import com.lind.bookshop.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
  @Autowired
  UserInfoMapper userInfoMapper;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    UserInfo userInfo =
        userInfoMapper.findUserByName(name);

    return userInfo;
  }
}