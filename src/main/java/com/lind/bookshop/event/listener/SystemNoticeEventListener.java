package com.lind.bookshop.event.listener;

import com.lind.bookshop.entity.Category;
import com.lind.bookshop.event.source.ChangeBookEvent;
import com.lind.bookshop.event.source.DelCategoryEvent;
import com.lind.bookshop.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 系统消息事件.
 */
@Component
@Slf4j
public class SystemNoticeEventListener {
  @Autowired
  CategoryMapper categoryMapper;

  @EventListener
  public void handleCreateBookEvent(ChangeBookEvent event) {
    System.out.println("系统消息：建立图书:" + event.getTitle());
  }

  @EventListener
  public void handleDelCategoryEvent(DelCategoryEvent event) {
    Category category = categoryMapper.selectById(event.getId());
    if (category == null) {
      logger.error("分类没有找到:{}", event.getId());
    } else {
      System.out.println("系统消息：删除分类:" + category.getName());
    }
  }
}
