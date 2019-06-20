package com.lind.bookshop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lind.bookshop.util.EnumUtils;

/**
 * 订单状态.
 */
public enum OrderStatus implements BaseEnum {
  Create(1, "建立"), NoConfirm(2, "待付款"),
  Paid(3, "已付款"), Shipping(4, "已发货"), Success(5, "已完成"), Close(6, "已关闭");

  private Integer code;
  private String text;

  OrderStatus(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  /**
   * 把数据表整型自动转为枚举类型.
   *
   * @param code .
   * @return
   */
  @JsonCreator
  public static OrderStatus jsonCreate(Integer code) {
    return EnumUtils.codeOf(OrderStatus.class, code);
  }

  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getText() {
    return this.text;
  }

  @JsonValue
  public Integer getCodeStr() {
    return this.code;
  }
}
