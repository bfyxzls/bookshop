package com.lind.bookshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.bookshop.entity.OrderInfo;
import java.util.List;

public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
  List<OrderInfo> findOrderList(Long userId);
}
