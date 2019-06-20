package com.lind.bookshop.service;

import com.lind.bookshop.entity.UserInfo;

public interface UserService {
  void registeUser(UserInfo userInfo);

  void modifyUser(UserInfo userInfo);

  UserInfo login(UserInfo userInfo);

  UserInfo getUser(String username);
}
