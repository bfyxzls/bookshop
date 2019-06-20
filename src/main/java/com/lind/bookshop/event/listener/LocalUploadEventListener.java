package com.lind.bookshop.event.listener;

import com.lind.bookshop.entity.Book;
import com.lind.bookshop.event.source.UploadBookImageEvent;
import com.lind.bookshop.mapper.BookMapper;
import com.lind.bookshop.util.FileUtils;
import java.io.File;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地上传.
 */
@Component
public class LocalUploadEventListener {
  @Autowired
  BookMapper bookMapper;

  /**
   * 上传不能用异步.
   *
   * @param event
   * @throws Exception
   */
  @EventListener
  public void handleEvent(UploadBookImageEvent event) throws Exception {
    MultipartFile blFile = event.getImgUrl();
    if (!blFile.isEmpty()) {
      String oldFileName = blFile.getOriginalFilename();
      ApplicationHome home = new ApplicationHome(getClass());
      String path = FileUtils.getPath("/static/upload");
      String randomStr = UUID.randomUUID().toString();
      String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
      File file = new File(path, newFileName);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      blFile.transferTo(file);
      Book book = Book.builder()
          .id(event.getId())
          .imgUrl(path + "/" + newFileName)
          .build();
      bookMapper.updateById(book);
    }
  }


}
