package com.bonson.qqtapk.model.data.voice;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/8.
 */

public class VoiceModel {
    private VoiceServer voiceServer;

    @Inject
    public VoiceModel(VoiceServer voiceServer) {
        this.voiceServer = voiceServer;
    }

    public void voices(int start, int pageSize) {

    }

    public void upload() {

    }

    public void addVoice() {

    }
}
