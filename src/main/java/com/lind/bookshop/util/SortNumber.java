package com.lind.bookshop.util;

public interface SortNumber {
  /**
   * 排序最后的索引号.
   *
   * @return
   */
  void setSortNumber(Integer sortNumber);

  /**
   * 比较的字段.
   *
   * @return
   */
  Integer getSortValue();
}
