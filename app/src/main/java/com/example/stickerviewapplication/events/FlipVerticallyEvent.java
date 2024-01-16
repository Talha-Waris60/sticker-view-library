package com.example.stickerviewapplication.events;

import com.example.stickerviewapplication.stickerview.StickerView;

public class FlipVerticallyEvent extends AbstractFlipEvent{
    @Override
    protected @StickerView.Flip  int getFlipDirection() {
        return StickerView.FLIP_VERTICALLY;;
    }
}
