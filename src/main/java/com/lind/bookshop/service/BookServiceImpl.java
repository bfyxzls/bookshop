package com.lind.bookshop.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.bookshop.entity.Book;
import com.lind.bookshop.mapper.BookMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  BookMapper bookMapper;

  @Override
  public List<Book> getBooks(Long userId, boolean isNew) {
    return bookMapper.selectList(
        new QueryWrapper<Book>().lambda().eq(Book::getUserId, userId).eq(Book::getIsNew, isNew));
  }

  @Override
  public void addBook(Book book) {
    bookMapper.insert(book);
  }

  @Override
  public void editBook(Book book) {
    bookMapper.updateById(book);
  }

  @Override
  public void delBook(Long id) {
    bookMapper.deleteById(id);
  }

  /**
   * 加缓存后，缓存需要实现Serializable接口
   *
   * @return
   */
  @CachePut(value = "books")
  @Override
  public List<Book> getRecommendBooks() {
    return bookMapper.selectList(
        new QueryWrapper<Book>().lambda().eq(Book::getIsRecommend, 1));
  }

  @Override
  public List<Book> getNewBooks() {
    return bookMapper.selectList(
        new QueryWrapper<Book>().lambda().eq(Book::getIsNew, 1));

  }

  @Override
  public List<Book> getOldBooks() {
    return bookMapper.selectList(
        new QueryWrapper<Book>().lambda().eq(Book::getIsNew, 0));

  }

  @Override
  public List<Book> getActivityBooks() {
    return bookMapper.selectList(
        new QueryWrapper<Book>().lambda().lt(Book::getDiscount, 100));
  }

  @Override
  public IPage<Book> getBooks(IPage<Book> page, Wrapper<Book> params) {
    return bookMapper.selectPage(page, params);
  }
}
