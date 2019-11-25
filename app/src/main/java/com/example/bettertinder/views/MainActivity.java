package com.example.bettertinder.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.TextView;

import com.example.bettertinder.ItemModel;
import com.example.bettertinder.R;

import java.util.ArrayList;

import static com.example.bettertinder.cardstack.CardStackAdapter.MURDER;

public class MainActivity extends AppCompatActivity implements OnFragmentSwitchedListener {

    private HomeFragment homeFragment;
    private ResultFragment resultFragment;

    private FragmentManager fragmentManager;
//private TextView health;
    private ArrayList<ItemModel> likedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likedItems = new ArrayList<>();

        homeFragment = new HomeFragment();
        resultFragment = new ResultFragment();

        fragmentManager = getSupportFragmentManager();

        onFragmentSwitched(FragmentType.HOME);
    }

    @Override
    public void onFragmentSwitched(FragmentType type)
    {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch (type)
        {
            case HOME:
                transaction.replace(R.id.contentPage,homeFragment);
                break;
            case RESULT:
                transaction.replace(R.id.contentPage,resultFragment);
                break;

        }

        transaction.commit();
    }

    @Override
    public void setLikedItems(ArrayList<ItemModel> likedItems) {
        this.likedItems = likedItems;
    }

    @Override
    public ArrayList<ItemModel> getLikedItems() {
        return likedItems;
    }
}
