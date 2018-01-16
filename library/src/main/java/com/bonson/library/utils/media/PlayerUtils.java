package com.bonson.library.utils.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

import javax.inject.Inject;

public class PlayerUtils {
    private MediaPlayer mediaPlayer;
    private Context context;

    @Inject
    public PlayerUtils(Context context) {
        this.context = context;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void play(int res) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, res);
        mediaPlayer.start();
    }

    public void play(String url) {
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
            } catch (Exception e) {
                mediaPlayer.release();
            }
        }
    }

    private static PlayerUtils playerUtils;

    public static PlayerUtils create(Context context) {
        if (playerUtils == null) {
            playerUtils = new PlayerUtils(context);
        }
        return playerUtils;
    }
}
