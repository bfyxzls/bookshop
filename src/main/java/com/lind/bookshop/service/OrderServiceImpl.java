package com.lind.bookshop.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.bookshop.entity.OrderInfo;
import com.lind.bookshop.entity.OrderItem;
import com.lind.bookshop.mapper.OrderInfoMapper;
import com.lind.bookshop.mapper.OrderItemMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  OrderInfoMapper orderInfoMapper;
  @Autowired
  OrderItemMapper orderItemMapper;

  @Transactional
  @Override
  public void doPost(OrderInfo orderInfo, List<OrderItem> orderItems) {
    orderInfoMapper.insert(orderInfo);
    orderInfo.getOrderItemList().forEach(o -> orderItemMapper.insert(o));
  }

  @Override
  public List<OrderInfo> getOrders(Long userId) {
    return orderInfoMapper.selectList(new QueryWrapper<>());
  }
}
