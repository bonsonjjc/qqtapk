package com.bonson.qqtapk.model.bean;

/**
 * Created by zjw on 2018/1/2.
 */

public final class Result<T> {
  private String code = "-1";
  private String msg = "";
  private T body;

  public Result() {

  }

  public Result(String code, String msg, T body) {
    this.code = code;
    this.msg = msg;
    this.body = body;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getBody() {
    return body;
  }

  public void setBody(T body) {
    this.body = body;
  }
}

