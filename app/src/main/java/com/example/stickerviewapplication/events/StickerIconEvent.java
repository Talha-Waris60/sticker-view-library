package com.example.stickerviewapplication.events;

import android.view.MotionEvent;

import com.example.stickerviewapplication.stickerview.StickerView;

public interface StickerIconEvent {
    void onActionDown(StickerView stickerView, MotionEvent event);

    void onActionMove(StickerView stickerView, MotionEvent event);

    void onActionUp(StickerView stickerView, MotionEvent event);
}
