package com.bonson.zxing.decode;

import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bonson.zxing.Ids;
import com.bonson.zxing.ViewfinderResultPointCallback;
import com.bonson.zxing.ViewfinderView;
import com.bonson.zxing.camera.CameraManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

/**
 * This class handles all the messaging which comprises the state machine for capture.
 */
public final class CaptureActivityHandler extends Handler {

    private static final String TAG = CaptureActivityHandler.class.getSimpleName();

    private final CaptureCallBack callBack;
    private final Activity activity;
    private final DecodeThread decodeThread;
    private State state;

    private enum State {
        PREVIEW, SUCCESS, DONE
    }

    public CaptureActivityHandler(Activity activity, CaptureCallBack callBack, Vector<BarcodeFormat> decodeFormats, String characterSet) {
        this.callBack = callBack;
        this.activity=activity;
        ViewfinderResultPointCallback viewfinderResultPointCallback = new ViewfinderResultPointCallback(callBack.getViewfinderView());
        decodeThread = new DecodeThread(this, decodeFormats, characterSet, viewfinderResultPointCallback);
        decodeThread.start();
        state = State.SUCCESS;
        // Start ourselves capturing previews and decoding.
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case Ids.auto_focus:
                // Log.d(TAG, "Got auto-focus message");
                // When one auto focus pass finishes, start another. This is the closest thing to
                // continuous AF. It does seem to hunt a bit, but I'm not sure what else to do.
                if (state == State.PREVIEW) {
                    CameraManager.get().requestAutoFocus(this, Ids.auto_focus);
                }
                break;
            case Ids.restart_preview:
                Log.d(TAG, "Got restart preview message");
                restartPreviewAndDecode();
                break;
            case Ids.decode_succeeded:
                Log.d(TAG, "Got decode succeeded message");
                state = State.SUCCESS;
                Bundle bundle = message.getData();

                /***********************************************************************/
                Bitmap barcode = bundle == null ? null : (Bitmap) bundle.getParcelable(DecodeThread.BARCODE_BITMAP);// 锟斤拷锟矫憋拷锟斤拷锟竭筹拷

                callBack.handleDecode((Result) message.obj, barcode);// 锟斤拷锟截斤拷锟? /***********************************************************************/
                break;
            case Ids.decode_failed:
                // We're decoding as fast as possible, so when one decode fails, start another.
                state = State.PREVIEW;
                CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), Ids.decode);
                break;
            case Ids.return_scan_result:
                Log.d(TAG, "Got return scan result message");
                activity.setResult(Activity.RESULT_OK, (Intent) message.obj);
                activity.finish();
                break;
            case Ids.launch_product_query:
                Log.d(TAG, "Got product query message");
                String url = (String) message.obj;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                activity.startActivity(intent);
                break;
        }
    }

    public void quitSynchronously() {
        state = State.DONE;
        CameraManager.get().stopPreview();
        Message quit = Message.obtain(decodeThread.getHandler(), Ids.quit);
        quit.sendToTarget();
        try {
            decodeThread.join();
        } catch (InterruptedException e) {
            // continue
        }

        // Be absolutely sure we don't send any queued up messages
        removeMessages(Ids.decode_succeeded);
        removeMessages(Ids.decode_failed);
    }

    public void restartPreviewAndDecode() {
        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), Ids.decode);
            CameraManager.get().requestAutoFocus(this, Ids.auto_focus);
            callBack.getViewfinderView().drawViewfinder();
        }
    }

    public interface CaptureCallBack {

        void handleDecode(Result result, Bitmap barcode);

        ViewfinderView getViewfinderView();
    }
}
