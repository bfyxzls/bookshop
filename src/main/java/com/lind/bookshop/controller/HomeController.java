package com.lind.bookshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.bookshop.mapper.VideoLessonMapper;
import com.lind.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @Autowired
  BookService bookService;
  @Autowired
  VideoLessonMapper videoLessonMapper;

  @GetMapping("/")
  public String index() {
    return "home/index";
  }

  @GetMapping("/news")
  public String news(Model model) {
    model.addAttribute("books", bookService.getNewBooks());
    model.addAttribute("videos", videoLessonMapper.selectList(new QueryWrapper<>()));
    return "home/news";
  }

  @GetMapping("/recommends")
  public String recommends(Model model) {
    model.addAttribute("books", bookService.getRecommendBooks());
    return "home/recommends";
  }
}
