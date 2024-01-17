package com.example.stickerviewapplication.activities;

import static com.example.stickerviewapplication.activities.MainActivity.APP_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.stickerviewapplication.R;
import com.example.stickerviewapplication.stickerview.DrawableSticker;
import com.example.stickerviewapplication.stickerview.Sticker;
import com.example.stickerviewapplication.stickerview.StickerView;
import com.example.stickerviewapplication.stickerview.TextSticker;
import com.example.stickerviewapplication.util.FileUtil;

public class MainActivity2 extends AppCompatActivity {

    private StickerView stickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d(APP_TAG , "Hello");
        stickerView = findViewById(R.id.sticker_talha);
        if (stickerView != null) {
            loadSticker();
        } else {
            Log.d(APP_TAG, "Null");
        }

    }

    private void loadSticker() {

        Drawable stickerDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_weightlifter);
        Drawable stickerMilk = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_milk_box);
        stickerView.addSticker(new DrawableSticker(stickerDrawable));
        stickerView.addSticker(new DrawableSticker(stickerMilk), Sticker.Position.BOTTOM | Sticker.Position.CENTER);

    }
}