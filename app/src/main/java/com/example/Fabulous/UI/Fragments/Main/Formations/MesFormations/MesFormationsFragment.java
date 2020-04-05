package com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.R;
import com.example.Fabulous.SessionManager;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginViewModel;
import com.example.Fabulous.UI.Activities.SplashScreenActivity.SplashScreenViewModel;
import com.example.Fabulous.UI.Adapters.MesFormationsAdapter;
import com.example.Fabulous.ViewModel.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.paperdb.Paper;




public class MesFormationsFragment extends DaggerFragment {
    @BindView(R.id.mesformationRecycler)
    RecyclerView formationRecyclerView;

    private MesFormationsViewModel viewModel;
    private static final String TAG = "MesFormationsFragment";


    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    MesFormationsAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, parent, false);
        ButterKnife.bind(this,view);

        viewModel = new ViewModelProvider(this,providerFactory).get(MesFormationsViewModel.class);
        formationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MesFormationsAdapter();
        formationRecyclerView.setAdapter(adapter);
        viewModel.observeState().observe(getViewLifecycleOwner(), MesFormationsResource -> {
            switch (MesFormationsResource.status){
                case DONE:{
                    adapter.setData(MesFormationsResource.data.profile.trainings.trainings);
                    break;
                }
                case ERROR:{
                    Log.d(TAG, "onCreateView:  Error");
                    break;
                }

            }
        });


        return view;
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }


}