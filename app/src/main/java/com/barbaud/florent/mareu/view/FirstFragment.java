package com.barbaud.florent.mareu.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.barbaud.florent.mareu.R;
import com.barbaud.florent.mareu.API.ReunionFictif;
import com.barbaud.florent.mareu.model.Reunion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstFragment extends Fragment {

    @BindView(R.id.content_main_recyclerview) RecyclerView mRecyclerView;
    private List<Reunion> mReunions;
    private ReunionRecyclerViewAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        this.configureRecycleView();
        return view;
    }

    private void configureRecycleView() {
        this.mReunions = ReunionFictif.generateReunion();
        this.mAdapter = new ReunionRecyclerViewAdapter(mReunions);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
