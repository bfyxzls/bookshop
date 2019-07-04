package com.lind.bookshop.client.fallback;

import com.lind.bookshop.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 降级回退类.
 */
@Component
@Slf4j
public class ProductClientFallback implements ProductClient {

  @Override
  public ResponseEntity<?> getProducts() {
    logger.info("feign.getProducts.error");
    return null;
  }
}
