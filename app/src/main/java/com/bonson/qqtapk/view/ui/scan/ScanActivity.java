package com.bonson.qqtapk.view.ui.scan;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.bonson.qqtapk.R;
import com.bonson.qqtapk.app.Const;
import com.bonson.qqtapk.databinding.ActivityScanBinding;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.zxing.ViewfinderView;
import com.bonson.zxing.camera.CameraManager;
import com.bonson.zxing.decode.CaptureActivityHandler;
import com.bonson.zxing.decode.InactivityTimer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.Vector;

import javax.inject.Inject;

public class ScanActivity extends BaseDaggerActivity<ActivityScanBinding> implements SurfaceHolder.Callback, CaptureActivityHandler.CaptureCallBack {
    @Inject
    ScanViewModel viewModel;

    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_scan);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);
        binding.preview.setTips("将二维码/条形码放入框内，可自动扫描");

        binding.toolbar.setTitle("添加宝贝");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.toolbar.getTvRight().setText("手动输入");
        binding.toolbar.getTvRight().setOnClickListener(v -> {

        });
        CameraManager.init(getApplication());
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    public SurfaceView getSurfaceView() {
        return binding.surfaceView;
    }

    protected void request(String code) {
        if (!code.matches(Const.IMEI_VALIDATE)) {
            restart();
            toast("无法识别的二维码");
            return;
        }
        viewModel.add(code);
    }

    @Override
    public ViewfinderView getViewfinderView() {
        return binding.preview;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onResume() {
        super.onResume();
        SurfaceHolder surfaceHolder = getSurfaceView().getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String code = result.getText();
        if (code.equals("")) {
            restart();
        } else {
            request(code);
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, this, decodeFormats, characterSet);
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(com.bonson.zxing.R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = mediaPlayer -> mediaPlayer.seekTo(0);

    private void restart() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (handler != null) {
                    handler.restartPreviewAndDecode();
                }
            }
        }, 2000); // 5秒
    }
}

