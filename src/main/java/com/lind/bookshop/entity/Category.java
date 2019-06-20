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
public class Category extends EntityBase {
  private String name;
  private Long parentId;
  private Integer level;

  public Category(Integer isDelete, Long id, Timestamp createdOn, Timestamp updatedOn, String name, Long parentId, Integer level) {
    super(0, id, createdOn, updatedOn);
    this.name = name;
    this.parentId = parentId;
    this.level = level;
  }
}
