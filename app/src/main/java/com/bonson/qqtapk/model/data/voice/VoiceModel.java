package com.bonson.qqtapk.model.data.voice;

import com.bonson.qqtapk.model.data.ApiServer;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class VoiceModel {
    private ApiServer voiceServer;

    @Inject
    public VoiceModel(ApiServer voiceServer) {
        this.voiceServer = voiceServer;
    }

    public void voices(int start, int pageSize) {

    }

    public void upload() {

    }

    public void addVoice() {

    }
}
