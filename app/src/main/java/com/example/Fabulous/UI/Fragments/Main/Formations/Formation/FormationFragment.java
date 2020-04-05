package com.example.Fabulous.UI.Fragments.Main.Formations.Formation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.Fabulous.R;

import dagger.android.support.DaggerFragment;


public class FormationFragment extends DaggerFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment1, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}