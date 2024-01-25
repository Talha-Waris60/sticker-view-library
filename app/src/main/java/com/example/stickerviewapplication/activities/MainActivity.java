package com.example.stickerviewapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.stickerviewapplication.R;
import com.example.stickerviewapplication.adapter.RecyclerViewAdapter;
import com.example.stickerviewapplication.events.DeleteIconEvent;
import com.example.stickerviewapplication.events.FlipHorizontallyEvent;
import com.example.stickerviewapplication.events.FlipVerticallyEvent;
import com.example.stickerviewapplication.events.RotateIconEvent;
import com.example.stickerviewapplication.events.ZoomIconEvent;
import com.example.stickerviewapplication.model.StickerModel;
import com.example.stickerviewapplication.stickerview.BitmapStickerIcon;
import com.example.stickerviewapplication.stickerview.DrawableSticker;
import com.example.stickerviewapplication.stickerview.Sticker;
import com.example.stickerviewapplication.stickerview.StickerView;
import com.example.stickerviewapplication.stickerview.TextSticker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static final String APP_TAG = "StickerView-App";
    private StickerView stickerView;
    private Sticker stickerPerson, milkSticker;
    private Button buttonNext, buttonTextSticker, buttonImageSticker, buttonMilkSticker;

    ImageView layerImage;
    RecyclerView dialogRecyclerView;

    private RecyclerViewAdapter adapter;
    public ArrayList<StickerModel> stickerList = new ArrayList<>();


    private TextSticker stickertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stickerView = (StickerView) findViewById(R.id.sticker_view);
        buttonTextSticker = findViewById(R.id.add_text_Sticker);
        buttonImageSticker = findViewById(R.id.add_image_sticker);
        buttonMilkSticker = findViewById(R.id.add_image_sticker_milk);
        layerImage = findViewById(R.id.ic_layer);

      //  loadTextSticker();
        setUpListener();
        stickerViewIconsAndEvents();

        Log.d(APP_TAG, "List - " + stickerView.getStickers().size());

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

            buttonMilkSticker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMilkSticker();
                }
            });

            layerImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showRecyclerViewDialogBox();
                }
            });
            // Add new Sticker
            buttonTextSticker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadTextSticker();
                }
            });

            buttonImageSticker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadImageSticker();
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
                    removeStickerFromList(sticker);
                 //   adapter.notifyDataSetChanged();
                    Log.d(APP_TAG, "onStickerDeleted");

                }

                @Override
                public void onStickerDragFinished(@NonNull Sticker sticker) {
                    Log.d(APP_TAG, "onStickerDragFinished");
                }

                @Override
                public void onStickerDrag(@NonNull Sticker sticker) {
                    Log.d(APP_TAG, "onDrag");
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

    // remove the sticker from list
        private void removeStickerFromList(Sticker sticker) {
            // Iterate through the stickerList to find and remove the corresponding StickerModel
            for (StickerModel stickerModel : stickerList) {
                if (stickerModel.getSticker() == sticker) {
                    stickerList.remove(stickerModel);
                    return;
                }
            }
        }

        // load Sticker
        private void loadTextSticker() {
            stickertext = new TextSticker(this);
            stickertext.setText("Jiggly cake");
            stickertext.setTextAlign(Layout.Alignment.ALIGN_CENTER);
            stickertext.resizeText();
            stickerList.add(0,new StickerModel(StickerModel.TYPE_TEXT, stickertext));
            stickerView.addSticker(stickertext);


        }

        private void loadImageSticker() {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_cake);
            stickerPerson = new DrawableSticker(drawable);
            stickerList.add(0,new StickerModel(StickerModel.TYPE_IMAGE, stickerPerson));
            stickerView.addSticker(stickerPerson);
        }


    private void loadMilkSticker() {

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_milk_box);
        milkSticker = new DrawableSticker(drawable);
        stickerList.add(0,new StickerModel(StickerModel.TYPE_IMAGE, milkSticker));
        stickerView.addSticker(milkSticker);
    }

    private void showRecyclerViewDialogBox() {

            Log.d(APP_TAG, "List - " + stickerView.getStickers().size());
            Log.d(APP_TAG, "StickerViewList 0 - " + stickerView.getStickers().get(0).getStickerType());
            Log.d(APP_TAG, "StickerViewList 1 - "  + stickerView.getStickers().get(1).getStickerType());
            Log.d(APP_TAG, "StickerViewList 2 - "  + stickerView.getStickers().get(2).getStickerType());

            Log.d(APP_TAG, "Main 0 - " + stickerList.get(0).getSticker());
        //      Log.d(APP_TAG, "Main 1 - "  + stickerList.get(1).getSticker());
//            Log.d(APP_TAG, "Postion 2 - "  + stickerList.get(2).getType());

            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.dialogue_recycler_view, null);

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setView(dialogLayout)
                    .create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();

            alertDialog.setCanceledOnTouchOutside(true);
            // Custom height of Dialogue box and alignment
            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setLayout(600, 900);
                alertDialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
            }


            dialogRecyclerView = dialogLayout.findViewById(R.id.dilogue_recycler_view_item);
            dialogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            dialogRecyclerView.setAdapter(adapter = new RecyclerViewAdapter(this, stickerList));
            touchEventsToRecyclerView(); // Pass the dialogRecyclerView to touchEventsToRecyclerView
        }


        private void touchEventsToRecyclerView() {
        ArrayList<Sticker> newStickerList = new ArrayList<>();


            ItemTouchHelper touchHelper = new ItemTouchHelper(
                    new ItemTouchHelper.Callback() {

                        @Override
                        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                                    0);
                        }

                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            int draggedItemIndex = viewHolder.getAdapterPosition();
                            int targetIndex = target.getAdapterPosition();
                            // Swap the items in the stickerList
                            Collections.swap(stickerList, draggedItemIndex, targetIndex);
                          //  Sticker selectedSticker = stickerView.getSticker(draggedItemIndex);
                            // stickerView.moveStickerToLayer(selectedSticker, targetIndex,  draggedItemIndex)
                            adapter.notifyItemMoved(draggedItemIndex, targetIndex);

                            newStickerList.clear();
                            for (StickerModel stickerModel : stickerList) {
                                newStickerList.add(0,stickerModel.getSticker());
                            }
                            stickerView.moveStickerToLayer(newStickerList);
                            return true;
                        }

                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                        }

                    }
            );

            touchHelper.attachToRecyclerView(dialogRecyclerView);
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