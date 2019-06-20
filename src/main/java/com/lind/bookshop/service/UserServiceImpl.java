package com.lind.bookshop.service;

import com.google.common.collect.ImmutableMap;
import com.lind.bookshop.entity.UserInfo;
import com.lind.bookshop.exception.Exceptions;
import com.lind.bookshop.mapper.UserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserInfoMapper userInfoBaseMapper;

  @Override
  public void registeUser(UserInfo userInfo) {
    if (StringUtils.isBlank(userInfo.getRealname())) {
      throw Exceptions.badRequestParams("请填写真实姓名");
    }
    UserInfo exist = userInfoBaseMapper.findUserByName(userInfo.getUsername());
    if (exist != null) {
      throw Exceptions.badRequestParams("用户已经存在不能重复注册");
    }
    userInfoBaseMapper.insert(userInfo);
  }

  @Override
  public void modifyUser(UserInfo userInfo) {
    userInfoBaseMapper.updateById(userInfo);
  }

  @Override
  public UserInfo login(UserInfo userInfo) {
    UserInfo user = userInfoBaseMapper.findUserByNamePassword(
        ImmutableMap.of("userName", userInfo.getUsername(), "password", userInfo.getPassword()));
    if (user == null)
      throw Exceptions.badRequestParams("用户密码不正确");
    return user;
  }

  @Override
  public UserInfo getUser(String username) {
    return userInfoBaseMapper.findUserByName(username);
  }
}
