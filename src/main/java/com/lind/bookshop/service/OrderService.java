package com.lind.bookshop.service;

import com.lind.bookshop.entity.OrderInfo;
import com.lind.bookshop.entity.OrderItem;
import java.util.List;

public interface OrderService {
  void doPost(OrderInfo orderInfo, List<OrderItem> orderItems);

  List<OrderInfo> getOrders(Long userId);
}

