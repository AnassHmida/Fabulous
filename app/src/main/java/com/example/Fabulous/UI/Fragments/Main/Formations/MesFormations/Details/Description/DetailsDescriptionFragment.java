package com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.Details.Description;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.Fabulous.R;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public class DetailsDescriptionFragment extends DaggerFragment {
    private String Description;

    public DetailsDescriptionFragment(String description) {
        this.Description = description;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_description_layout, parent, false);

        ButterKnife.bind(this,view);
       TextView txt =  (TextView)view.findViewById(R.id.details_description);
        txt.setMovementMethod(new ScrollingMovementMethod());
       txt.setText(Description);
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}