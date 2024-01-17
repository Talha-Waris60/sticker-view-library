package com.example.stickerviewapplication.events;

import android.view.MotionEvent;

import com.example.stickerviewapplication.stickerview.Sticker;
import com.example.stickerviewapplication.stickerview.StickerView;

public class DeleteIconEvent implements StickerIconEvent {
    @Override
    public void onActionDown(StickerView stickerView, MotionEvent event) {
        Sticker currentSticker = stickerView.getCurrentSticker();
        if (currentSticker != null) {
            stickerView.remove(currentSticker);
        }
    }

    @Override
    public void onActionMove(StickerView stickerView, MotionEvent event) {

    }

    @Override
    public void onActionUp(StickerView stickerView, MotionEvent event) {

    }
}
