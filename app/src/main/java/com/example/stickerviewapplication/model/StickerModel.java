package com.example.stickerviewapplication.model;

import com.example.stickerviewapplication.stickerview.Sticker;

public class StickerModel {
    private int type;
    private Sticker sticker;

    private int position; // Add position attribute
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;

    public StickerModel(int type, Sticker sticker) {
        this.type = type;
        this.sticker = sticker;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public Sticker getSticker() {
        return sticker;
    }
}
