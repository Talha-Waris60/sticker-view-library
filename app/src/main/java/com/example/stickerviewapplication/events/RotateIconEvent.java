package com.example.stickerviewapplication.events;

import android.view.MotionEvent;

import com.example.stickerviewapplication.stickerview.StickerView;

public class RotateIconEvent implements StickerIconEvent{
    @Override
    public void onActionDown(StickerView stickerView, MotionEvent event) {

    }

    @Override
    public void onActionMove(StickerView stickerView, MotionEvent event) {
        stickerView.rotateCurrentSticker(event);
    }

    @Override
    public void onActionUp(StickerView stickerView, MotionEvent event) {

    }
}
