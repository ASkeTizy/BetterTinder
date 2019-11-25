package com.example.bettertinder.cardstack.input;

import com.example.bettertinder.cardstack.CardStackAdapter;

public interface OnItemSwipedListener
{
    void onItemSwiped();
    void onItemSwipedLeft();
    void onItemSwipedRight(CardStackAdapter.ItemView itemView);
}
