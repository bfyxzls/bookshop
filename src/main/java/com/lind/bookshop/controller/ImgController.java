package com.lind.bookshop.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImgController {

  private byte[] fileToByte(File img) throws Exception {
    byte[] bytes = null;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      BufferedImage bi;
      bi = ImageIO.read(img);
      ImageIO.write(bi, "png", baos);
      bytes = baos.toByteArray();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      baos.close();
    }
    return bytes;
  }

  public File getResource(String name) throws Exception {
    File file = ResourceUtils.getFile("classpath:" + name);
    return file;
  }

  //http://localhost:9008/img/bg1.png
  @GetMapping(value = "/img/{name}", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<?> getImg(@PathVariable("name") String path) throws Exception {
    byte[] imageContent;
    imageContent = fileToByte(getResource("images/" + path));

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_PNG);
    return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
  }
}
