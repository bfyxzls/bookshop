package com.lind.bookshop.event.listener;

import com.lind.bookshop.event.source.ChangeBookEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 短信发送消息事件.
 */
@Component
@Order(3)
public class SmsEventListener {
  @EventListener
  public void handleEvent(ChangeBookEvent event) {
    System.out.println("SMS-消息：建立图书:" + event.getTitle());
  }

}
