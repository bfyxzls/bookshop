package com.lind.bookshop.client.fallback;

import com.google.common.collect.ImmutableMap;
import com.lind.bookshop.client.ProductClient;
import com.lind.bookshop.client.UserClient;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductClientFallback implements ProductClient {

  @Override
  public ResponseEntity<?> getProducts() {
    logger.info("feign.getProducts.error");
    return null;
  }
}
