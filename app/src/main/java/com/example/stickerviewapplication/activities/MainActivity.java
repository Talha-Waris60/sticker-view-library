package com.example.stickerviewapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.stickerviewapplication.R;
import com.example.stickerviewapplication.events.DeleteIconEvent;
import com.example.stickerviewapplication.events.FlipHorizontallyEvent;
import com.example.stickerviewapplication.events.RotateIconEvent;
import com.example.stickerviewapplication.events.ZoomIconEvent;
import com.example.stickerviewapplication.stickerview.BitmapStickerIcon;
import com.example.stickerviewapplication.stickerview.DrawableSticker;
import com.example.stickerviewapplication.stickerview.Sticker;
import com.example.stickerviewapplication.stickerview.StickerView;
import com.example.stickerviewapplication.stickerview.TextSticker;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static final String APP_TAG = "StickerView-App";
    private StickerView stickerView;
    private Sticker stickerPerson;
    private Button buttonNext, buttonAdd;

    private TextSticker stickertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stickerView = (StickerView) findViewById(R.id.sticker_view);
        buttonAdd = findViewById(R.id.aadd);
       // buttonNext = findViewById(R.id.next);

        loadSticker();
        setUpListener();
        stickerViewIconsAndEvents();

    }

    // Sticker-Icons and Icon events
    private void stickerViewIconsAndEvents() {
        //currently you can config your own icons and icon event
        //the event you can custom
        BitmapStickerIcon deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_delete),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());

        BitmapStickerIcon zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_zoom),
                BitmapStickerIcon.RIGHT_BOTTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());

        BitmapStickerIcon flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.ic_flip),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());

        BitmapStickerIcon rotateIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_rotation),
                        BitmapStickerIcon.LEFT_BOTTOM);
        rotateIcon.setIconEvent(new RotateIconEvent());

        stickerView.setConstrained(true);

        stickerView.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, rotateIcon));

    }

    // Click-listener
    private void setUpListener() {

 /*       buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });*/

        // Add new Sticker
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadSticker();
            }
        });

        // touch listener on StickerView
        stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                Log.d(APP_TAG, "onStickerAdded");
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                //stickerView.removeAllSticker();
                if (sticker instanceof TextSticker) {
                    stickerView.replace(sticker);
                    stickerView.invalidate();
                }
                Log.d(APP_TAG, "onStickerClicked");
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
                Log.d(APP_TAG, "onStickerDeleted");

            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {
                Log.d(APP_TAG, "onStickerDragFinished");
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                Log.d(APP_TAG, "onStickerTouchedDown");
            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {
                Log.d(APP_TAG, "onStickerZoomFinished");
            }

            @Override
            public void onStickerFlipped(@NonNull Sticker sticker) {
                Log.d(APP_TAG, "onStickerFlipped");
            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
                Log.d(APP_TAG, "onDoubleTapped: double tap will be with two click");
            }
        });
    }

    // load Sticker
    private void loadSticker() {
     /*  Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_weightlifter);
        Drawable drawable1 = ContextCompat.getDrawable(this, R.drawable.ic_milk_box);
        stickerPerson = new DrawableSticker(drawable);
        stickerView.addSticker(stickerPerson);*/

        stickertext = new TextSticker(this);
        stickertext.setText("Hello, world!");
        stickertext.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        stickertext.resizeText();

        stickerView.addSticker(stickertext);



    }

/*
     TODO: Use it latter - Methods for replace, lock, reset, remove, removeAll, add Sticker
    public void testReplace(View view) {
        if (stickerView.replace(sticker)) {
            Toast.makeText(MainActivity.this, "Replace Sticker successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Replace Sticker failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void testLock(View view) {
        stickerView.setLocked(!stickerView.isLocked());
    }

    public void testRemove(View view) {
        if (stickerView.removeCurrentSticker()) {
            Toast.makeText(MainActivity.this, "Remove current Sticker successfully!", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(MainActivity.this, "Remove current Sticker failed!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void testRemoveAll(View view) {
        stickerView.removeAllStickers();
    }

    public void reset(View view) {
        stickerView.removeAllStickers();
        loadSticker();
    }

    public void testAdd(View view) {
        final TextSticker sticker = new TextSticker(this);
        sticker.setText("Hello, world!");
        sticker.setTextColor(Color.BLUE);
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();

        stickerView.addSticker(sticker);
    }*/
}