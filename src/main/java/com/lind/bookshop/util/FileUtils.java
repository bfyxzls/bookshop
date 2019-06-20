package com.lind.bookshop.util;

import java.io.File;
import java.io.FileNotFoundException;
import org.springframework.util.ResourceUtils;

public class FileUtils {
  /**
   * 获取资源目录，解决运行jar包同时把资源文件指定的问题.
   * yml文件添加spring.resources: static-locations: classpath:static/,file:static/
   *
   * @param subdirectory 子目录
   * @return
   */
  public static String getPath(String subdirectory) {
    //调试时，目录为out/production/classes/subdirectory
    //执行单独jar时,目录为jar同级目录/subdirectory
    try {
      File path = new File(ResourceUtils.getURL("classpath:").getPath());
      if (!path.exists()) path = new File("");
      File upload = new File(path.getAbsolutePath(), subdirectory);
      if (!upload.exists()) upload.mkdirs();//如果不存在则创建目录
      String realPath = upload + "/";
      return realPath;
    } catch (FileNotFoundException e) {
      throw new RuntimeException("获取服务器路径发生错误！");
    }
  }
}
