package com.lind.bookshop.event.source;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图书信息变更.
 * 建立和修改时需要通知，如发短信，发电邮，发系统消息，统计数据增加等.
 */
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChangeBookEvent {
  private String address;
  private String title;
}
