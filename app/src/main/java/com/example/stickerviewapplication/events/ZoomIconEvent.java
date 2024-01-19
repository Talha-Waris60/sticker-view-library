package com.example.stickerviewapplication.events;

import static com.example.stickerviewapplication.activities.MainActivity.APP_TAG;

import android.util.Log;
import android.view.MotionEvent;

import com.example.stickerviewapplication.stickerview.StickerView;

public class ZoomIconEvent implements StickerIconEvent {
    @Override
    public void onActionDown(StickerView stickerView, MotionEvent event) {
    }

    @Override
    public void onActionMove(StickerView stickerView, MotionEvent event) {
        stickerView.zoomCurrentSticker(event);


    }

    @Override
    public void onActionUp(StickerView stickerView, MotionEvent event) {

        if (stickerView.getOnStickerOperationListener() != null) {
            stickerView.getOnStickerOperationListener()
                    .onStickerZoomFinished(stickerView.getCurrentSticker());
        }
    }
}
