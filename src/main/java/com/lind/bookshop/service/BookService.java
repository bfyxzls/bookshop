package com.lind.bookshop.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.bookshop.entity.Book;
import java.util.List;

public interface BookService {
  List<Book> getBooks(Long userId, boolean isNew);

  void addBook(Book book);

  void editBook(Book book);

  void delBook(Long id);

  List<Book> getRecommendBooks();

  List<Book> getNewBooks();

  List<Book> getOldBooks();

  List<Book> getActivityBooks();

  IPage<Book> getBooks(IPage<Book> page, Wrapper<Book> params);

}
