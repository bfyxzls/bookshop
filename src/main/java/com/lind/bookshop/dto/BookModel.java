package com.lind.bookshop.dto;

import com.lind.bookshop.exception.Exceptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter //前端传过来时，需要加setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
  private Long categoryId;
  private String categoryName;
  private String name;
  private Double salePrice;
  private Double floorPrice;
  private Integer discount;
  private MultipartFile imgUrl;
  private Integer isNew;
  private Integer isRecommend;

  /**
   * 校验.
   */
  public void valid() {
    if (StringUtils.isEmpty(name)) {
      throw Exceptions.badRequestParams("书名不能为空!");
    }
    if (categoryId == null) {
      throw Exceptions.badRequestParams("请选择分类!");
    }
    if (salePrice == null
        || floorPrice == null
        || salePrice < 0
        || floorPrice < 0) {
      throw Exceptions.badRequestParams("价格不能小于0!");
    }
  }
}
