package com.example.bettertinder.views;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.bettertinder.R;
import com.example.bettertinder.cardstack.CardStackAdapter;
import com.example.bettertinder.views.custom.CanvasHomeButton;

import static com.example.bettertinder.cardstack.CardStackAdapter.WIN;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    private OnFragmentSwitchedListener fragmentSwitchedListener;
    private CanvasHomeButton homeButton;
private FrameLayout killer;
    public ResultFragment() {
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
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
killer=getActivity().findViewById(R.id.killerKilled);
if(WIN>0)
    killer.setVisibility(View.VISIBLE);
        homeButton = getActivity().findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSwitchedListener.onFragmentSwitched(OnFragmentSwitchedListener.FragmentType.HOME);
            }
        });

        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerLikes);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);


        recyclerView.setAdapter(new CardStackAdapter(fragmentSwitchedListener.getLikedItems(),fragmentSwitchedListener));
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
    }
}


