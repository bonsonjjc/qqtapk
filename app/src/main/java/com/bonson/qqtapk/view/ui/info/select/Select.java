package com.bonson.qqtapk.view.ui.info.select;

public class Select {
    private String name;
    private boolean checked = false;
    private int wht;
    private String value;

    public Select() {
    }

    public Select(String name, boolean checked) {
        this.name = name;
        this.checked = checked;
    }

    public Select(String name, boolean checked, String value) {
        this.name = name;
        this.checked = checked;
        this.value = value;
    }

    public Select(String name, boolean checked, int wht, String value) {
        this.name = name;
        this.checked = checked;
        this.wht = wht;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getWht() {
        return wht;
    }

    public void setWht(int wht) {
        this.wht = wht;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
