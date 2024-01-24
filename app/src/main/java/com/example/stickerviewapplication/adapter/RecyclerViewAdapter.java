package com.example.stickerviewapplication.adapter;


import static com.example.stickerviewapplication.activities.MainActivity.APP_TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.stickerviewapplication.R;
import com.example.stickerviewapplication.enums.StickerType;
import com.example.stickerviewapplication.model.StickerModel;
import com.example.stickerviewapplication.stickerview.DrawableSticker;
import com.example.stickerviewapplication.stickerview.Sticker;
import com.example.stickerviewapplication.stickerview.TextSticker;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StickerModel> stickerList = new ArrayList<>();

    // Define view types
    private static final int VIEW_TYPE_TEXT = 0;
    private static final int VIEW_TYPE_IMAGE = 1;
    public Context context;
    public RecyclerViewAdapter(Context context, ArrayList<StickerModel> recyclerViewItemsLists) {
        this.stickerList = recyclerViewItemsLists;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Inflate different layouts based on view type
        if (viewType == VIEW_TYPE_TEXT) {
            View view = inflater.inflate(R.layout.item_text_sticker, parent, false);
            return new TextStickerViewHolder(view);
        } else if (viewType == VIEW_TYPE_IMAGE) {
            View view = inflater.inflate(R.layout.item_image_sticker, parent, false);
            return new ImageStickerViewHolder(view);
        }

        throw new IllegalArgumentException("Invalid view type");

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StickerModel stickerModel = stickerList.get(position);
        if (holder instanceof TextStickerViewHolder && stickerModel.getType() == StickerModel.TYPE_TEXT) {
            ((TextStickerViewHolder) holder).text.setText( ((TextSticker) stickerModel.getSticker()).getText());
        } else if (holder instanceof ImageStickerViewHolder && stickerModel.getType() == StickerModel.TYPE_IMAGE) {
            ((ImageStickerViewHolder) holder).stickerImage.setImageDrawable(((DrawableSticker) stickerModel.getSticker()).getDrawable());
        }
    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return stickerList.get(position).getType();
    }

    // TextViewHolder class
    public class TextStickerViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public TextStickerViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.sticker_text);
        }
    }

    // ImageViewHolder
    private static class ImageStickerViewHolder extends RecyclerView.ViewHolder {
        private ImageView stickerImage;
        public ImageStickerViewHolder(@NonNull View itemView) {
            super(itemView);
            stickerImage = itemView.findViewById(R.id.sticker_image);
        }
        // Implement your view holder for image stickers
    }
}
