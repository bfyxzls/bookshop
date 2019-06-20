package com.lind.bookshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.bookshop.entity.Book;
import com.lind.bookshop.entity.Category;
import com.lind.bookshop.entity.OrderInfo;
import com.lind.bookshop.entity.OrderItem;
import com.lind.bookshop.entity.UserInfo;
import com.lind.bookshop.entity.UserShipper;
import com.lind.bookshop.entity.VideoLesson;
import com.lind.bookshop.enums.OrderStatus;
import com.lind.bookshop.mapper.BookMapper;
import com.lind.bookshop.mapper.CategoryMapper;
import com.lind.bookshop.mapper.OrderInfoMapper;
import com.lind.bookshop.mapper.OrderItemMapper;
import com.lind.bookshop.mapper.UserInfoMapper;
import com.lind.bookshop.mapper.UserShipperMapper;
import com.lind.bookshop.mapper.VideoLessonMapper;
import com.lind.bookshop.util.ResponseUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Autowired
  UserInfoMapper userInfoMapper;
  @Autowired
  CategoryMapper categoryMapper;
  @Autowired
  BookMapper bookMapper;
  @Autowired
  VideoLessonMapper videoLessonMapper;
  @Autowired
  UserShipperMapper userShipperMapper;
  @Autowired
  OrderInfoMapper orderInfoMapper;
  @Autowired
  OrderItemMapper orderItemMapper;

  @GetMapping("/api/v1/test")
  public ResponseEntity<?> userGet() {
    userInfoMapper.selectById(0);
    return ResponseUtils.okMessage("success");
  }

  @GetMapping("/api/v1/test/page")
  public ResponseEntity<?> dataGet() {
    List<UserInfo> userInfoList = new ArrayList<>();
    userInfoList.add(UserInfo.builder().username("zzl1").realname("lind1").build());
    userInfoList.add(UserInfo.builder().username("zzl2").realname("lind2").build());
    return ResponseUtils.ok(userInfoList, userInfoList.size());
  }

  @GetMapping("/api/v1/test/validate-name")
  public ResponseEntity<?> userValidate(@RequestParam String name) {
    UserInfo userInfo = UserInfo.builder()
        .username(name)
        .build();
    userInfo.validateName();
    return ResponseUtils.ok(userInfo);
  }

  @GetMapping("/api/v1/test/validate-price")
  public String userValidatePrice() {
    OrderInfo orderInfo = OrderInfo.builder().build();
    orderInfo.validatePrice();
    return "ok";
  }

  @GetMapping("/api/v1/test/init")
  public String init() {
    if (categoryMapper.selectCount(
        new QueryWrapper<Category>().lambda().eq(Category::getName, "大叔商店")) == 0) {
      Category root = Category.builder()
          .level(0)
          .name("大叔商店")
          .build();
      categoryMapper.insert(root);
      Category video = Category.builder()
          .level(1)
          .name("视频课程")
          .parentId(root.getId())
          .build();
      categoryMapper.insert(video);
      Category code = Category.builder()
          .level(1)
          .name("源代码框架")
          .parentId(root.getId())
          .build();
      categoryMapper.insert(code);
      UserInfo userInfo = UserInfo.builder()
          .username("lind")
          .password("zzl123")
          .realname("大叔")
          .age(37)
          .telephone("13521972990")
          .build();
      userInfoMapper.insert(userInfo);
      UserShipper userShipper = UserShipper.builder()
          .userId(userInfo.getId())
          .address("beijing")
          .build();
      userShipperMapper.insert(userShipper);
      Book lindCode = Book.builder()
          .categoryId(code.getId())
          .categoryName(code.getName())
          .discount(80)
          .floorPrice(1500d)
          .salePrice(2000d)
          .isNew(1)
          .isRecommend(1)
          .name("Lind.DDD源代码+免费升级+免费支持")
          .userId(userInfo.getId())
          .userName(userInfo.getUsername())
          .build();
      bookMapper.insert(lindCode);
      Book lindCodeCore = Book.builder()
          .categoryId(code.getId())
          .categoryName(code.getName())
          .discount(99)
          .floorPrice(1000d)
          .salePrice(2000d)
          .isNew(1)
          .isRecommend(1)
          .name("LindDotNetCore源代码+免费升级+免费支持")
          .userId(userInfo.getId())
          .userName(userInfo.getUsername())
          .build();
      bookMapper.insert(lindCodeCore);
      VideoLesson videoLesson = VideoLesson.builder()
          .filePath("/video/di.mp4")
          .information("介绍依赖注入的实现与原理")
          .title("依赖注入的介绍")
          .build();
      videoLessonMapper.insert(videoLesson);
      VideoLesson videoLesson2 = VideoLesson.builder()
          .filePath("/video/cache.mp4")
          .information("介绍面向方法的缓存的实现与原理")
          .title("缓存拦截器的介绍")
          .build();
      videoLessonMapper.insert(videoLesson2);
      OrderInfo orderInfo = OrderInfo.builder()
          .totalPrice(1000d)
          .userId(userInfo.getId())
          .orderTime(LocalDateTime.now())
          .orderStatus(OrderStatus.Create)
          .userName(userInfo.getUsername())
          .build();
      orderInfoMapper.insert(orderInfo);
      OrderItem orderItem = OrderItem.builder()
          .orderInfoId(orderInfo.getId())
          .price(500d)
          .bookName(videoLesson.getTitle())
          .bookId(videoLesson.getId())
          .build();
      orderItemMapper.insert(orderItem);
      OrderItem orderItem2 = OrderItem.builder()
          .orderInfoId(orderInfo.getId())
          .price(500d)
          .bookName(videoLesson2.getTitle())
          .bookId(videoLesson2.getId())
          .build();
      orderItemMapper.insert(orderItem2);
    }
    return "ok";
  }
}
