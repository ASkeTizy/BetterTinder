package com.example.bettertinder.views;

import com.example.bettertinder.ItemModel;

import java.util.ArrayList;

public interface OnFragmentSwitchedListener
{
    public void onFragmentSwitched(FragmentType type);
    public void setLikedItems(ArrayList<ItemModel> likedItems);
    public ArrayList<ItemModel> getLikedItems();

    enum FragmentType
    {
        HOME,
        RESULT,

    }
}
