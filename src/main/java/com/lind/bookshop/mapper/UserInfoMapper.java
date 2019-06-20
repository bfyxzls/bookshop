package com.lind.bookshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.bookshop.entity.UserInfo;
import java.util.List;
import java.util.Map;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
  List<UserInfo> findUsers();

  /**
   * 返回map时，为null的字段会被过滤掉.
   * 返回map时，可以使用resultMap，让字段序列化为这个规则.
   *
   * @return
   */
  List<Map<String, Object>> findMapUsers();

  UserInfo findUserById(Long id);

  UserInfo findUserByNamePassword(Map<String, Object> params);

  UserInfo findUserByName(String userName);

}
