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
public class UserShipper extends EntityBase {
  private Long userId;
  private String address;

  public UserShipper(Integer isDelete, Long id, Timestamp createdOn, Timestamp updatedOn, Long userId, String address) {
    super(isDelete, id, createdOn, updatedOn);
    this.userId = userId;
    this.address = address;
  }
}
