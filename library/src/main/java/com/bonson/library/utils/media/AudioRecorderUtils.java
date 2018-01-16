package com.bonson.library.utils.media;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

public class AudioRecorderUtils {

    //文件路径
    private String filePath;
    //文件夹路径
    private String folderPath;

    private MediaRecorder mMediaRecorder;
    private final String TAG = "fan";
    private static final int MAX_LENGTH = 1000 * 60 * 16;// 最大录音时长1000*60*10;

    private long startTime;

    @Inject
    public AudioRecorderUtils() {
        filePath = Environment.getExternalStorageDirectory() + "/ydqqt/voice/";
        File path = new File(filePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        this.folderPath = filePath;
    }

    public AudioRecorderUtils(String filePath) {
        this.filePath = filePath;
        File path = new File(filePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        this.folderPath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * 开始录音 使用amr格式
     * 录音文件
     *
     * @return
     */
    public void startRecord() {
        // 开始录音
        /* ①Initial：实例化MediaRecorder对象 */
        if (mMediaRecorder == null)
            mMediaRecorder = new MediaRecorder();
        try {
            /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);// 设置麦克风
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            filePath = folderPath + System.currentTimeMillis() + ".amr";
            /* ③准备 */
            mMediaRecorder.setOutputFile(filePath);
            mMediaRecorder.setMaxDuration(MAX_LENGTH);
            mMediaRecorder.prepare();
            /* ④开始 */
            mMediaRecorder.start();
            startTime = System.currentTimeMillis();
            // AudioRecord audioRecord.
        } catch (IllegalStateException e) {
            Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        } catch (IOException e) {
            Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        }
    }

    /**
     * 停止录音
     */
    public int stopRecord() {
        if (mMediaRecorder == null)
            return 0;
        //有一些网友反应在5.0以上在调用stop的时候会报错，翻阅了一下谷歌文档发现上面确实写的有可能会报错的情况，捕获异常清理一下就行了，感谢大家反馈！
        long endTime = System.currentTimeMillis();
        try {
            mMediaRecorder.stop();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        return (int) ((endTime - startTime) / 1000 / 60);
    }

    /**
     * 取消录音
     */
    public void cancelRecord() {
        try {
            mMediaRecorder.stop();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        File file = new File(filePath);
        if (file.exists())
            file.delete();
        filePath = "";
    }
}
