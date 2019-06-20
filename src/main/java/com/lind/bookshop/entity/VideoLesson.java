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
public class VideoLesson extends EntityBase {
  private String title;
  private String filePath;
  private String information;

  public VideoLesson(Integer isDelete, Long id, Timestamp createdOn, Timestamp updatedOn, String title, String filePath, String information) {
    super(isDelete, id, createdOn, updatedOn);
    this.title = title;
    this.filePath = filePath;
    this.information = information;
  }
}