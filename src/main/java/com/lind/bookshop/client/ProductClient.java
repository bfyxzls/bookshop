package com.lind.bookshop.client;

import com.lind.bookshop.client.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "bookshop", fallback = ProductClientFallback.class)
public interface ProductClient {
  @GetMapping("v1/api/test/page")
  ResponseEntity<?> getProducts();
}
