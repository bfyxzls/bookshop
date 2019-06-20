package com.lind.bookshop.controller;

import com.lind.bookshop.entity.UserInfo;
import com.lind.bookshop.service.UserService;
import com.lind.bookshop.util.ResponseUtils;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  HttpSession httpSession;
  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("user", new UserInfo());
    return "user/register";
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ResponseEntity<?> postUser(@ModelAttribute UserInfo userInfo) {
    userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
    userService.registeUser(userInfo);
    return ResponseUtils.okMessage("success");
  }

  @GetMapping("/login")
  public String login() {
    return "user/login";
  }

  @GetMapping("/logout")
  public String logout(HttpSession httpSession) {
    httpSession.removeAttribute("user");
    return "/";
  }
}
