package com.example.stickerviewapplication.events;

import com.example.stickerviewapplication.stickerview.StickerView;

public class FlipBothDirectionsEvent extends AbstractFlipEvent {
    @Override @StickerView.Flip protected int getFlipDirection() {
        return StickerView.FLIP_VERTICALLY | StickerView.FLIP_HORIZONTALLY;
    }
}
