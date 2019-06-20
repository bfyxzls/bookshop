package com.lind.bookshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.bookshop.entity.Category;
import com.lind.bookshop.event.source.DelCategoryEvent;
import com.lind.bookshop.mapper.CategoryMapper;
import com.lind.bookshop.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class CategoryController {
  static final String create = "category/create";
  static final String list = "category/list";
  static final String delete = "category/del";
  @Autowired
  CategoryMapper categoryMapper;
  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @GetMapping(create)
  public String create(Model model) {
    model.addAttribute("category", new Category());
    return create;
  }

  @PostMapping(create)
  public ResponseEntity<?> create(@ModelAttribute Category category) {
    categoryMapper.insert(category);
    return ResponseUtils.okMessage("success");
  }

  @GetMapping(delete)
  public String del(@RequestParam Long id) {
    applicationEventPublisher.publishEvent(DelCategoryEvent.builder().id(id).build());
    categoryMapper.deleteById(id);
    return "redirect:" + list;
  }


  @GetMapping(list)
  public String list(Model model) {
    model.addAttribute("list", categoryMapper.selectList(new QueryWrapper<>()));
    return list;
  }
}
