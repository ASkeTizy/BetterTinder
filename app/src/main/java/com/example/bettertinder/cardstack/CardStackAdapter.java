package com.example.bettertinder.cardstack;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.bettertinder.ItemModel;
import com.example.bettertinder.views.OnFragmentSwitchedListener;
import com.example.bettertinder.R;
import com.example.bettertinder.cardstack.input.OnItemSwipedListener;
import com.example.bettertinder.cardstack.input.OnItemSwipedPrecentageListener;
import com.example.bettertinder.views.custom.CanvasImageView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ItemView> implements OnItemSwipedListener {
    private ArrayList<ItemModel> data;
    // private ViewPager mpaper;
    public static int MURDER = 3;
    public static int WIN=0;
    private static int pos=0;
    private ArrayList<ItemModel> likedItems;
    private OnFragmentSwitchedListener fragmentSwitchedListener;
    private TextView health;

    public CardStackAdapter(ArrayList<ItemModel> data, OnFragmentSwitchedListener fragmentSwitchedListener) {
        this.data = data;

        this.fragmentSwitchedListener = fragmentSwitchedListener;

        likedItems = new ArrayList<>();
    }

    public CardStackAdapter(ArrayList<ItemModel> data, OnFragmentSwitchedListener fragmentSwitchedListener,TextView view)
    {
        this(data,fragmentSwitchedListener);
        health = view;
    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spot, parent, false);
        return new ItemView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {
        holder.SetData(data.get(position));
        setPos(position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onItemSwiped() {
//        if (data.size() == 0) {
//            //fragmentSwitchedListener.setLikedItems(likedItems);
//            fragmentSwitchedListener.onFragmentSwitched(OnFragmentSwitchedListener.FragmentType.HOME);
//        }
    }

    @Override
    public void onItemSwipedLeft() {
        data.add(data.get(0));
        data.remove(0);
        notifyDataSetChanged();
    }

    @Override
    public void onItemSwipedRight(ItemView itemView) {

        itemView.tryToKill();

        //likedItems.add(data.get(0));
//        if(MURDER!=getPos()) {
//
//            data.add(data.get(10));
//            data.remove(0);
//            notifyItemRemoved(0);
//            //fragmentSwitchedListener.onFragmentSwitched(OnFragmentSwitchedListener.FragmentType.RESULT);
//            Log.i(TAG, "onItemSwipedRight: Help me");
//        }

    }

    public static int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public class ItemView extends RecyclerView.ViewHolder implements OnItemSwipedPrecentageListener {
        private CanvasImageView image;
        private TextView title;

        private View leftOverlay, rightOverlay;
        private View innocentKilledOverlay;

        private ItemModel model;

        public ItemView(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_name);

            leftOverlay = itemView.findViewById(R.id.left_overlay);
            rightOverlay = itemView.findViewById(R.id.right_overlay);

            innocentKilledOverlay = itemView.findViewById(R.id.innocentKilled);
        }

        public void SetData(ItemModel model) {
            title.setText(model.getTitle());


            this.model = model;
            if(model.isOverlay())
                innocentKilledOverlay.setVisibility(View.VISIBLE);

            Glide.with(image).asBitmap().load(model.getImageUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    image.setBitmapToDraw(resource);

                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        }

        @Override
        public void onItemSwipePercentage(float percentage) {
            if (percentage < 0)
                leftOverlay.setAlpha(Math.abs(percentage));
            else if (percentage == 0) {
                rightOverlay.setAlpha(0);
                leftOverlay.setAlpha(0);
            } else
                rightOverlay.setAlpha(percentage);
        }

        public void tryToKill()
        {

            if(model.isKiller())
            {
                WIN=1;
                fragmentSwitchedListener.onFragmentSwitched(OnFragmentSwitchedListener.FragmentType.RESULT);

            }
            else
            {

                WIN=0;
                model.setOverlay(true);
                MURDER--;
                health.setText(String.valueOf(MURDER));
                if(MURDER==0) {
                    fragmentSwitchedListener.onFragmentSwitched(OnFragmentSwitchedListener.FragmentType.RESULT);
                    MURDER=3;
                }
                notifyItemChanged(0);

            }
        }
    }
}
