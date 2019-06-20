package com.lind.bookshop.entity.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UpdatedOnFuncation {
  /**
   * default .
   * @return
   */
  String value() default "";
}