package com.example.stickerviewapplication.events;

import static com.example.stickerviewapplication.activities.MainActivity.APP_TAG;

import android.util.Log;
import android.view.MotionEvent;

import com.example.stickerviewapplication.stickerview.StickerView;

public class ZoomIconEvent implements StickerIconEvent {
    @Override
    public void onActionDown(StickerView stickerView, MotionEvent event) {
        Log.d(APP_TAG, "1");
    }

    @Override
    public void onActionMove(StickerView stickerView, MotionEvent event) {
        stickerView.zoomCurrentSticker(event);
        Log.d(APP_TAG, "2");

    }

    @Override
    public void onActionUp(StickerView stickerView, MotionEvent event) {
        Log.d(APP_TAG, "3");
        if (stickerView.getOnStickerOperationListener() != null) {
            stickerView.getOnStickerOperationListener()
                    .onStickerZoomFinished(stickerView.getCurrentSticker());
        }
    }
}
