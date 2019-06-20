package com.lind.bookshop.exception;

import lombok.ToString;

@ToString
public class ErrorResponse {
  private int status;
  private String method;
  private String path;
  private Object extra;
  private ErrorItem error;

  /**
   * init.
   */
  public ErrorResponse() {
  }

  /**
   * init.
   */
  public ErrorResponse addError(String code, String message) {
    error = ErrorItem.builder().code(code).message(message).build();
    return this;
  }

  /**
   * init.
   */
  public ErrorResponse addError(String code, Object value) {
    error = ErrorItem.builder().code(code).value(value).build();
    return this;
  }

  /**
   * init.
   */
  public ErrorResponse addError(ErrorItem errorItem) {
    this.error = errorItem;
    return this;
  }

  /**
   * get errors.
   *
   * @return
   */
  public ErrorItem getError() {
    return this.error;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(final int status) {
    this.status = status;
  }

  public String getMethod() {
    return this.method;
  }

  public void setMethod(final String method) {
    this.method = method;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(final String path) {
    this.path = path;
  }

  public Object getExtra() {
    return this.extra;
  }

  public void setExtra(final Object extra) {
    this.extra = extra;
  }

}
