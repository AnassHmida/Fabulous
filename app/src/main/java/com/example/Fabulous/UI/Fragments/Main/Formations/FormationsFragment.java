package com.example.Fabulous.UI.Fragments.Main.Formations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.bumptech.glide.Glide;
import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.R;
import com.example.Fabulous.UI.Adapters.ViewPagerAdapter;
import com.example.Fabulous.UI.Fragments.Main.Formations.Formation.FormationFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.MesFormationsFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.MesFormationsViewModel;
import com.example.Fabulous.Utils.util;
import com.example.Fabulous.ViewModel.ViewModelProviderFactory;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.paperdb.Paper;



public class FormationsFragment extends DaggerFragment {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.Profilename)
    TextView profileName;
    @BindView(R.id.ProfileDescription)
    TextView profileDescription;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @Inject
    ViewPagerAdapter adapter;

    private static final String TAG = "MesFormationsFragment";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.formations_fragment, parent, false);
        ButterKnife.bind(this,view);

        setUpProfile();
        return view;
    }


    private void setupViewPager(ViewPager viewPager) {
       adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new FormationFragment(), "Formations");
        adapter.addFrag(new MesFormationsFragment(), "Mes Formations");
        viewPager.setAdapter(adapter);
    }

    private void setUpProfile(){
        Paper.init(getActivity().getApplicationContext());
        ProfileDetails  profileDetails= Paper.book().read("loggedmodel");
        Glide
                .with(this)
                .load(util.imagebaseURL+profileDetails.profile.details.avatarUrl)
                .into(avatar);
        profileName.setText(profileDetails.profile.details.name);
        profileDescription.setText(profileDetails.role.description );
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


}