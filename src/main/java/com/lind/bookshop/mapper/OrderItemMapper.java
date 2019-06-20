package com.lind.bookshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.bookshop.entity.OrderItem;
import java.util.List;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
  List<OrderItem> findOrderItemList(Long orderId);
}
