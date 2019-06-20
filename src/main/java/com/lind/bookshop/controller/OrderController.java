package com.lind.bookshop.controller;

import com.lind.bookshop.entity.Book;
import com.lind.bookshop.entity.OrderInfo;
import com.lind.bookshop.entity.OrderItem;
import com.lind.bookshop.entity.UserInfo;
import com.lind.bookshop.enums.OrderStatus;
import com.lind.bookshop.exception.Exceptions;
import com.lind.bookshop.mapper.BookMapper;
import com.lind.bookshop.mapper.OrderInfoMapper;
import com.lind.bookshop.mapper.UserInfoMapper;
import com.lind.bookshop.service.OrderService;
import com.lind.bookshop.util.ResponseUtils;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
  static final String list = "order/list";
  static final String doPost = "order/doPost";
  static final String success = "order/success";
  static final String submitOk = "order/submitOK";
  @Autowired
  BookMapper bookMapper;
  @Autowired
  OrderService orderService;
  @Autowired
  OrderInfoMapper orderInfoMapper;
  @Autowired
  HttpSession httpSession;
  @Autowired
  UserInfoMapper userInfoMapper;

  UserInfo getCurrnetUser() {
    return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  @GetMapping(list)
  @PreAuthorize("hasRole('USER')")
  public String myList(Model model) {
    List<OrderInfo> orderInfos = orderInfoMapper.findOrderList(getCurrnetUser().getId());
    model.addAttribute("orders", orderInfos);
    return list;
  }

  @GetMapping("current-user")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> currnetUser(@AuthenticationPrincipal UserDetails user) {
    return ResponseUtils.ok(user);
  }

  @GetMapping(submitOk)
  public String submitOk() {
    return submitOk;
  }

  @GetMapping(success)
  public String success(@RequestParam Long id) {
    OrderInfo orderInfo = orderInfoMapper.selectById(id);
    orderInfo = orderInfo.toBuilder().orderStatus(OrderStatus.Success).build();
    orderInfoMapper.updateById(orderInfo);
    return "redirect:/" + list;
  }

  @GetMapping(doPost)
  public String doPost(@RequestParam Long productId) {
    Book book = bookMapper.selectById(productId);
    if (book == null)
      throw Exceptions.badRequestParams("产品不存在!");

    OrderInfo orderInfo = OrderInfo.builder()
        .orderStatus(OrderStatus.Create)
        .orderTime(LocalDateTime.now())
        .userId(1L)
        .userName("zzl")
        .build();
    OrderItem orderItem = OrderItem.builder()
        .bookId(book.getId())
        .bookName(book.getName())
        .price(book.getSalePrice())
        .orderInfoId(orderInfo.getId())
        .build();
    orderInfo = orderInfo.toBuilder().orderItemList(Arrays.asList(orderItem)).build();
    orderInfo.computeOrderPrice();
    orderInfo.validatePrice();

    orderService.doPost(orderInfo, null);
    return "redirect:/" + submitOk();

  }
}
