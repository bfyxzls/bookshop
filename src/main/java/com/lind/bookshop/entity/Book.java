package com.lind.bookshop.entity;

import com.lind.bookshop.entity.base.EntityBase;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends EntityBase implements Serializable {
  private Long userId;
  private String userName;
  private Long categoryId;
  private String categoryName;
  private String name;
  private Double salePrice;
  private Double floorPrice;
  private Integer discount;
  private Integer isNew;
  private Integer isRecommend;
  private String imgUrl;

  @Builder(toBuilder = true)
  public Book(Integer isDelete, Long id, Timestamp createdOn, Timestamp updatedOn, Long userId, String userName, Long categoryId, String categoryName, String name, Double salePrice, Double floorPrice, Integer discount, Integer isNew, Integer isRecommend, String imgUrl) {
    super(isDelete, id, createdOn, updatedOn);
    this.userId = userId;
    this.userName = userName;
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.name = name;
    this.salePrice = salePrice;
    this.floorPrice = floorPrice;
    this.discount = discount;
    this.isNew = isNew;
    this.isRecommend = isRecommend;
    this.imgUrl = imgUrl;
  }

  /**
   * 获取折扣信息.
   *
   * @return
   */
  public String discountInfo() {
    return discount == 100 ? "无折扣" : discount / 10 + "折";
  }

  /**
   * 获取图像相对路径，直接在浏览器上输出的.
   *
   * @return
   */
  public String imgRelativeUrl() {
    if (StringUtils.isBlank(imgUrl)) {
      return null;
    }
    return imgUrl.substring(imgUrl.lastIndexOf("/upload"));
  }
}

