package com.lind.bookshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.bookshop.dto.BookModel;
import com.lind.bookshop.entity.Book;
import com.lind.bookshop.entity.Category;
import com.lind.bookshop.event.source.ChangeBookEvent;
import com.lind.bookshop.event.source.UploadBookImageEvent;
import com.lind.bookshop.exception.Exceptions;
import com.lind.bookshop.mapper.BookMapper;
import com.lind.bookshop.mapper.CategoryMapper;
import com.lind.bookshop.util.EntityUtils;
import com.lind.bookshop.util.ResponseUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@Controller
public class BookController {
  static final String create = "book/create";
  static final String edit = "book/edit";
  static final String del = "book/del";
  static final String list = "book/list";
  @Autowired
  BookMapper bookMapper;
  @Autowired
  CategoryMapper categoryMapper;
  @Autowired
  ApplicationEventPublisher applicationEventPublisher;

  @GetMapping(create)
  public String create(Model model) {
    model.addAttribute("book", new BookModel());
    List<Category> list = categoryMapper.selectList(new QueryWrapper<>());
    model.addAttribute("list", list);
    return create;
  }

  @PostMapping(create)
  public ResponseEntity<?> create(@ModelAttribute BookModel bookModel) throws Exception {
    bookModel.valid();
    bookModel = bindCategoryName(bookModel);
    Book book = EntityUtils.convert(Book.class, bookModel);
    book = book.toBuilder()
        .isNew(1)
        .isRecommend(1)
        .userName("system")
        .userId(0L)
        .build();
    bookMapper.insert(book);
    // 添加图书时要触发的事件，订阅了CreateBookEvent的事件将被同步执行
    applicationEventPublisher.publishEvent(
        ChangeBookEvent.builder()
            .address("system")
            .title("新建图书")
            .build());

    // 上传图像
    if (bookModel.getImgUrl() != null) {
      applicationEventPublisher.publishEvent(UploadBookImageEvent.builder()
          .id(book.getId())
          .imgUrl(bookModel.getImgUrl())
          .build());
    }
    return ResponseUtils.okMessage("success");
  }

  @GetMapping(edit)
  public String edit(Model model, Long id) {
    model.addAttribute("book", bookMapper.selectById(id));
    List<Category> list = categoryMapper.selectList(new QueryWrapper<>());
    model.addAttribute("list", list);
    return edit;
  }

  @PostMapping(edit)
  public String edit(@ModelAttribute BookModel bookModel, Long id) throws Exception {
    bookModel.valid();
    bookModel = bindCategoryName(bookModel);
    Book book = EntityUtils.convert(Book.class, bookModel);
    book.setId(id);
    bookMapper.updateById(book);
    if (bookModel.getImgUrl() != null) {
      applicationEventPublisher.publishEvent(UploadBookImageEvent.builder()
          .id(book.getId())
          .imgUrl(bookModel.getImgUrl())
          .build());
    }
    return "redirect:/" + list;
  }

  @GetMapping(del)
  public String del(Long id) {
    bookMapper.deleteById(id);
    return "redirect:/" + list;
  }

  @GetMapping(list)
  public Mono<String> olds(Model model) {
    model.addAttribute("books", bookMapper.selectList(new QueryWrapper<>()));
    return Mono.just(list);
  }

  /**
   * 绑定分类.
   */
  public BookModel bindCategoryName(BookModel book) {
    Category category = categoryMapper.selectOne(
        new QueryWrapper<Category>().lambda().eq(Category::getId, book.getCategoryId()));
    if (category == null) {
      throw Exceptions.badRequestParams("分类有误!");
    }
    return book.toBuilder()
        .categoryName(category.getName())
        .build();
  }
}
