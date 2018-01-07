package com.bonson.resource.adapter;

import android.view.View;

/**
 * Created by zjw on 2017/12/29.
 */

public class Item {
  private String name = "";
  private String detail = "";
  private Integer icon;
  private int type;
  private boolean arrow = true;
  private View.OnClickListener onClickListener;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public Integer getIcon() {
    return icon;
  }

  public void setIcon(Integer icon) {
    this.icon = icon;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public boolean isArrow() {
    return arrow;
  }

  public void setArrow(boolean arrow) {
    this.arrow = arrow;
  }

  public View.OnClickListener getOnClickListener() {
    return onClickListener;
  }

  public void setOnClickListener(View.OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
  }
}
