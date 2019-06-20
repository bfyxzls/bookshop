package com.lind.bookshop.entity;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lind.bookshop.entity.base.EntityBase;
import com.lind.bookshop.enums.OrderStatus;
import com.lind.bookshop.exception.Exceptions;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo extends EntityBase {
  private Long userId;
  private String userName;
  private LocalDateTime orderTime;
  private OrderStatus orderStatus;
  private Double totalPrice;
  private List<OrderItem> orderItemList;

  public OrderInfo(Integer isDelete, Long id, Timestamp createdOn, Timestamp updatedOn, Long userId, String userName, LocalDateTime orderTime, OrderStatus orderStatus, Double totalPrice, List<OrderItem> orderItems) {
    super(isDelete, id, createdOn, updatedOn);
    this.userId = userId;
    this.userName = userName;
    this.orderTime = orderTime;
    this.orderStatus = orderStatus;
    this.totalPrice = totalPrice;
    this.orderItemList = orderItems;
  }

  /**
   * 检验价格.
   */
  public void validatePrice() {
    if (totalPrice == null || totalPrice < 0) {
      throw Exceptions.badRequestParams("订单金额需要大于等于0");
    }
  }

  /**
   * 计算订单价格.
   */
  public void computeOrderPrice() {
    if (CollectionUtils.isEmpty(orderItemList)) {
      throw Exceptions.badRequestParams("订单为空!");
    }
    this.totalPrice = orderItemList.stream()
        .map(o -> o.getPrice())
        .reduce((a, b) -> a + b).orElse(0d);
  }
}