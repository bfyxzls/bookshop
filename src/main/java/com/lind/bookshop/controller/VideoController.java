package com.lind.bookshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.bookshop.entity.VideoLesson;
import com.lind.bookshop.event.source.DelCategoryEvent;
import com.lind.bookshop.mapper.VideoLessonMapper;
import com.lind.bookshop.util.ResponseUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class VideoController {
  static final String create = "video/create";
  static final String list = "video/list";
  static final String delete = "video/del";
  @Autowired
  VideoLessonMapper videoLessonMapper;
  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @GetMapping(create)
  public String create(Model model) {
    model.addAttribute("video", new VideoLesson());
    return create;
  }

  @PostMapping(create)
  public ResponseEntity<?> create(@ModelAttribute VideoLesson videoLesson) {
    videoLessonMapper.insert(videoLesson);
    return ResponseUtils.okMessage("success");
  }

  @GetMapping(delete)
  public String del(@RequestParam Long id) {
    applicationEventPublisher.publishEvent(DelCategoryEvent.builder().id(id).build());
    videoLessonMapper.deleteById(id);
    return "redirect:" + list;
  }


  @GetMapping(list)
  public String list(Model model) {
    List<VideoLesson> result = videoLessonMapper.selectList(new QueryWrapper<>());
    model.addAttribute("list", result);
    return list;
  }

  @RequestMapping("/video/download")
  public String downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) {
    VideoLesson videoLesson = videoLessonMapper.selectById(id);
    String fileName = System.currentTimeMillis() + videoLesson.getFilePath().substring(videoLesson.getFilePath().lastIndexOf("."));
    String realPath = videoLesson.getFilePath();
    if (realPath != null) {
      File file = new File(realPath);
      if (file.exists()) {
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
          fis = new FileInputStream(file);
          bis = new BufferedInputStream(fis);
          OutputStream os = response.getOutputStream();
          int i = bis.read(buffer);
          while (i != -1) {
            os.write(buffer, 0, i);
            i = bis.read(buffer);
          }
          System.out.println("success");
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          if (bis != null) {
            try {
              bis.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          if (fis != null) {
            try {
              fis.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
    return null;
  }
}
