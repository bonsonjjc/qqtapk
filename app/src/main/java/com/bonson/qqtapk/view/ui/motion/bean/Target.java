package com.bonson.qqtapk.view.ui.motion.bean;

import com.bonson.qqtapk.model.bean.Base;

public class Target extends Base{
    private String fwalk = "0"; // 目标步数
    private String fsleep = "0"; // 目标睡眠
    private String frequency = "90";

    public String getFwalk() {
        return fwalk;
    }

    public void setFwalk(String fwalk) {
        this.fwalk = fwalk;
    }

    public String getFsleep() {
        return fsleep;
    }

    public void setFsleep(String fsleep) {
        this.fsleep = fsleep;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
