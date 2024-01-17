package com.example.stickerviewapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.stickerviewapplication.R;
import com.example.stickerviewapplication.events.DeleteIconEvent;
import com.example.stickerviewapplication.events.FlipHorizontallyEvent;
import com.example.stickerviewapplication.events.HelloIconEvent;
import com.example.stickerviewapplication.events.ZoomIconEvent;
import com.example.stickerviewapplication.stickerview.BitmapStickerIcon;
import com.example.stickerviewapplication.stickerview.DrawableSticker;
import com.example.stickerviewapplication.stickerview.Sticker;
import com.example.stickerviewapplication.stickerview.StickerView;
import com.example.stickerviewapplication.stickerview.TextSticker;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static final String APP_TAG = "StickerView-App";
    public static final int PERM_RQST_CODE = 110;
    private StickerView stickerView;
    private TextSticker sticker;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stickerView = (StickerView) findViewById(R.id.sticker_view);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

      //  stickerView = new StickerView(this);
        buttonNext = findViewById(R.id.next);
         buttonNext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                 startActivity(intent);
             }
         });

        //currently you can config your own icons and icon event
        //the event you can custom
        BitmapStickerIcon deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.close),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());

        BitmapStickerIcon zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_zoom_in),
                BitmapStickerIcon.RIGHT_BOTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());

        BitmapStickerIcon flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(this,
                R.drawable.ic_flip),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());

        BitmapStickerIcon heartIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(this, R.drawable.ic_heart),
                        BitmapStickerIcon.LEFT_BOTTOM);
        heartIcon.setIconEvent(new HelloIconEvent());

        stickerView.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, heartIcon));

        //default icon layout
        // stickerView.configDefaultIcons();

        stickerView.setBackgroundColor(Color.WHITE);
        stickerView.setLocked(false);
        stickerView.setConstrained(true);

        sticker = new TextSticker(this);

        sticker.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_weightlifter));
        sticker.setText("Hello, world!");
        sticker.setTextColor(Color.BLACK);
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();

        stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                Log.d(APP_TAG, "onStickerAdded");
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                //stickerView.removeAllSticker();
                if (sticker instanceof TextSticker) {
                    ((TextSticker) sticker).setTextColor(Color.RED);
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

//        if (toolbar != null) {
//            toolbar.setTitle(R.string.app_name);
//            toolbar.inflateMenu(R.menu.menu_save);
//            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    if (item.getItemId() == R.id.item_save) {
//                        File file = FileUtil.getNewFile(MainActivity.this, "Sticker");
//                        if (file != null) {
//                            stickerView.save(file);
//                            Toast.makeText(MainActivity.this, "saved in " + file.getAbsolutePath(),
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "the file is null", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    //                    stickerView.replace(new DrawableSticker(
//                    //                            ContextCompat.getDrawable(MainActivity.this, R.drawable.haizewang_90)
//                    //                    ));
//                    return false;
//                }
//            });
    }

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERM_RQST_CODE);
//        } else {
//            loadSticker();
//        }


    private void loadSticker() {
        Drawable drawable =
                ContextCompat.getDrawable(this, R.drawable.ic_weightlifter);
        Drawable drawable1 =
                ContextCompat.getDrawable(this, R.drawable.ic_weightlifter);
        stickerView.addSticker(new DrawableSticker(drawable));
        stickerView.addSticker(new DrawableSticker(drawable1), Sticker.Position.BOTTOM | Sticker.Position.RIGHT);

        Drawable bubble = ContextCompat.getDrawable(this, R.drawable.ic_weightlifter);
        stickerView.addSticker(
                new TextSticker(getApplicationContext())
                        .setDrawable(bubble)
                        .setText("Sticker\n")
                        .setMaxTextSize(14)
                        .resizeText()
                , Sticker.Position.TOP);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERM_RQST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadSticker();
        }
    }

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
    }
}