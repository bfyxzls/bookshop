package com.lind.bookshop.utils;

import com.lind.bookshop.util.CommonUtils;
import com.lind.bookshop.util.SortNumber;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class CommonUtilsTest {
  @Test
  public void random() {
    Assert.isTrue(!StringUtils.isEmpty(CommonUtils.getRandomString()));
  }

  @Test
  public void tokenSplit() {
    String token = "token=123";
    Assert.isTrue("123" .equals(token.substring(6)));
    String token2 = "token=OR123455";
    Assert.isTrue("OR123455" .equals(token2.substring(token2.lastIndexOf("OR"))));
  }

  @Test
  public void sortTest() {
    List<SortDemo> sortDemoList = new ArrayList<>();
    SortDemo sortDemo = new SortDemo();
    sortDemo.setName("zzl");
    sortDemo.setValue(1);
    SortDemo sortDemo2 = new SortDemo();
    sortDemo2.setName("zzl2");
    sortDemo2.setValue(2);
    sortDemoList.add(sortDemo);
    sortDemoList.add(sortDemo2);
    CommonUtils.sortNumber(sortDemoList);
    for (SortDemo item : sortDemoList) {
      System.out.println(item.toString());
    }
  }

  public class SortDemo implements SortNumber {
    private Integer order;
    private String name;
    private Integer value;

    public Integer getValue() {
      return value;
    }

    public void setValue(Integer value) {
      this.value = value;
    }

    public Integer getOrder() {
      return order;
    }

    public void setOrder(Integer order) {
      this.order = order;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "SortDemo{" +
          "order=" + order +
          ", name='" + name + '\'' +
          '}';
    }

    /**
     * 排序最后的索引号.
     *
     * @param sortNumber
     * @return
     */
    @Override
    public void setSortNumber(Integer sortNumber) {
      this.order = sortNumber;
    }

    /**
     * 比较的字段.
     *
     * @return
     */
    @Override
    public Integer getSortValue() {
      return this.value;
    }
  }
}
