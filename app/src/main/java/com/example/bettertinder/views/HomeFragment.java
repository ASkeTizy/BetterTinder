package com.example.bettertinder.views;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bettertinder.ItemModel;
import com.example.bettertinder.R;
import com.example.bettertinder.cardstack.CardStackAdapter;
import com.example.bettertinder.cardstack.CardStackLayoutManager;
import com.example.bettertinder.cardstack.input.CardStackTouchHelperCallback;

import java.util.ArrayList;

import static com.example.bettertinder.cardstack.CardStackAdapter.MURDER;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private OnFragmentSwitchedListener fragmentSwitchedListener;
        private TextView health;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        fragmentSwitchedListener = ((OnFragmentSwitchedListener) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        ArrayList<ItemModel> items = createItems();
        health=getActivity().findViewById(R.id.health);
        health.setText(String.valueOf(MURDER));
        CardStackAdapter adapter = new CardStackAdapter(items,fragmentSwitchedListener,health);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new CardStackTouchHelperCallback(adapter));
        touchHelper.attachToRecyclerView(recyclerView);

        CardStackLayoutManager manager = new CardStackLayoutManager();
        manager.setMaxShowCount(items.size());
        manager.setTransYGap(0);
        manager.setScaleGap(0.1f);
        manager.setAngle(30);
        manager.setAnimationDuratuion(450);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }

    private ArrayList<ItemModel> createItems()
    {
        ArrayList<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel("1/10",R.drawable.image001,true));
        items.add(new ItemModel( "2/10", R.drawable.image002));
        items.add(new ItemModel("3/10", R.drawable.image003));
        items.add(new ItemModel( "4/10", R.drawable.image004));
        items.add(new ItemModel( "5/10", R.drawable.image005));
        items.add(new ItemModel( "6/10", R.drawable.image006));
        items.add(new ItemModel( "7/10", R.drawable.image007));
        items.add(new ItemModel( "8/10", R.drawable.image008));
        items.add(new ItemModel("9/10", R.drawable.image009));
        items.add(new ItemModel( "10/10", R.drawable.image010));
       // items.add(new ItemModel("10 Lost","S","drawable/killer.PNG"));
       // https://source.unsplash.com/Xq1ntWruZQI/600x800
        return items;
    }

}
