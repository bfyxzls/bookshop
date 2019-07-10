package com.lind.bookshop.event.listener;

import com.lind.bookshop.event.source.ChangeBookEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CallPhoneEventListener {
  @EventListener
  public void handleEvent(ChangeBookEvent event) throws Exception {
    Thread.sleep(5000);
    System.out.println("TEL-为对应的负责人打电话:" + event.getTitle());
  }
}
