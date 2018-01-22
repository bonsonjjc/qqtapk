package com.bonson.qqtapk.model.data.voice;

import com.bonson.qqtapk.model.bean.Result;
import com.bonson.qqtapk.model.bean.Voice;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

interface VoiceDataSource {
    Observable<Result<List<Voice>>> voices(String fuser, String bid, int start, int pageSize);

    Observable<Result<String>> upload(File file);

    Observable<Result<String>> addVoice(Voice voice);
}
