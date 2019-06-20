package com.lind.bookshop.client;

    import org.springframework.cloud.openfeign.FeignClient;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
  @GetMapping("v1/api/products/{id}")
  ResponseEntity<?> getProduct(@PathVariable("id") Long id);
}
