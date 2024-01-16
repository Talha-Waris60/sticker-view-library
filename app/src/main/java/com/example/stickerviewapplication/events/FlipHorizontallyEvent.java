package com.example.stickerviewapplication.events;
import com.example.stickerviewapplication.stickerview.StickerView;
public class FlipHorizontallyEvent extends AbstractFlipEvent{


    @Override @StickerView.Flip protected int getFlipDirection() {
        return StickerView.FLIP_HORIZONTALLY;
    }
}
