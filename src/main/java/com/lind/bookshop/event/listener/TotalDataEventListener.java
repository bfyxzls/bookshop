package com.lind.bookshop.event.listener;

import com.lind.bookshop.event.source.ChangeBookEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 需要统计数量.
 */
@Component
@Order(5)
public class TotalDataEventListener {
  @EventListener
  public void handleCreateBookEvent(ChangeBookEvent event) {
    System.out.println("统计数据：图书统计增加:" + event.getTitle());
  }

}
