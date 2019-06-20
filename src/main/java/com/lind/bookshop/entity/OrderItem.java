package com.lind.bookshop.entity;

import com.lind.bookshop.entity.base.EntityBase;
import java.sql.Timestamp;
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
public class OrderItem extends EntityBase {
  private Long orderInfoId;
  private Long bookId;
  private String bookName;
  private Double price;
  private VideoLesson videoLesson;

  public OrderItem(Integer isDelete, Long id, Timestamp createdOn, Timestamp updatedOn, Long orderInfoId, Long bookId, String bookName, Double price, VideoLesson videoLesson) {
    super(isDelete, id, createdOn, updatedOn);
    this.orderInfoId = orderInfoId;
    this.bookId = bookId;
    this.bookName = bookName;
    this.price = price;
    this.videoLesson = videoLesson;
  }
}
