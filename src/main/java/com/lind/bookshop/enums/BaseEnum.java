package com.lind.bookshop.enums;

public interface BaseEnum<E extends Enum<?>, T> {

  public Integer getCode();

  public String getText();
}
