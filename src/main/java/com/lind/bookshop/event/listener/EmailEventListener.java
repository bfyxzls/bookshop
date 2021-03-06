package com.lind.bookshop.event.listener;

import com.lind.bookshop.event.source.ChangeBookEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * email发送消息事件.
 * 这种模式是同步的，会有线程阻塞,但在事务@Transactional注解下是有必须的，如果希望事件是异步的，为订阅者加@Async即可.
 */
@Component
@Order(2)
public class EmailEventListener {
  @EventListener
  public void handleEvent(ChangeBookEvent event) throws Exception {
    System.out.println("Email-消息：建立图书:" + event.getTitle());
  }
}
