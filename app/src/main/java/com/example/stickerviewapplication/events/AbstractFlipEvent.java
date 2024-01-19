package com.example.stickerviewapplication.events;

import android.view.MotionEvent;

import com.example.stickerviewapplication.stickerview.StickerView;
import com.example.stickerviewapplication.stickerview.StickerView.Flip;



public abstract class AbstractFlipEvent implements StickerIconEvent {
    @Override public void onActionDown(StickerView stickerView, MotionEvent event) {

    }

    @Override public void onActionMove(StickerView stickerView, MotionEvent event) {

    }

    @Override public void onActionUp(StickerView stickerView, MotionEvent event) {
      
        stickerView.flipCurrentSticker(getFlipDirection());
    }

    @StickerView.Flip
    protected abstract int getFlipDirection();
}
