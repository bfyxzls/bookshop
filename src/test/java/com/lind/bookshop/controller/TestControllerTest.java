package com.lind.bookshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.bookshop.TestBase;
import com.lind.bookshop.entity.Book;
import com.lind.bookshop.entity.UserInfo;
import com.lind.bookshop.entity.UserShipper;
import com.lind.bookshop.mapper.UserInfoMapper;
import com.lind.bookshop.service.BookService;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestControllerTest extends TestBase {
  @Autowired
  BookService bookService;
  @Autowired
  UserInfoMapper userInfoMapper;

  @Test
  public void pageList() throws Exception {
    mockMvc.perform(
        get("/api/v1/test/page")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.message").value("操作成功"))
        .andExpect(jsonPath("$.totalRecords").value(2));

  }

  @Test
  public void validateNameSuccess() throws Exception {
    mockMvc.perform(
        get("/api/v1/test/validate-name?name=zzllr")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.message").value("操作成功"));

  }

  @Test
  public void validateNameFail() throws Exception {
    mockMvc.perform(
        get("/api/v1/test/validate-name?name=zzl")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.message").value("操作成功"));

  }

  @Test
  public void pageSelect() throws Exception {
    init();

    IPage<Book> book1 = bookService.getBooks(new Page<>(1, 1), new QueryWrapper<>());
    IPage<Book> book2 = bookService.getBooks(new Page<>(2, 1), new QueryWrapper<>());
    Assert.assertEquals(2, book1.getTotal());
    Assert.assertEquals(2, book2.getTotal());

  }

  @Test
  public void lazyData() throws Exception {
    init();
    List<UserInfo> entity = userInfoMapper.findUsers();
    UserShipper userShipper = entity.get(0).getUserShipper();
    String address = userShipper.getAddress();
    Assert.assertEquals("beijing", address);
    List<Map<String, Object>> mapList = userInfoMapper.findMapUsers();
  }


  private void init() throws Exception {
    mockMvc.perform(
        get("/api/v1/test/init")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
