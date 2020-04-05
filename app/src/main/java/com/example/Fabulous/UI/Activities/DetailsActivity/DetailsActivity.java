package com.example.Fabulous.UI.Activities.DetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.R;

import com.example.Fabulous.UI.Activities.MainActivity.MainActivity;
import com.example.Fabulous.UI.Adapters.ViewPagerAdapter;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.Details.Cours.DetailsCoursFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.Details.Description.DetailsDescriptionFragment;
import com.example.Fabulous.UI.Fragments.Main.Formations.MesFormations.Details.Video.DetailsVideoFragment;

import com.example.Fabulous.Utils.util;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class DetailsActivity extends DaggerAppCompatActivity {
    private static final String TAG = DetailsActivity.class.getSimpleName();

    @BindView(R.id.header_title)
    TextView header_title;
    @BindView(R.id.htab_toolbar)
    Toolbar toolbar;
    @BindView(R.id.htab_viewpager)
    ViewPager viewPager;
    @BindView(R.id.htab_header)
    ImageView ImageHeader;
    @BindView(R.id.htab_tabs)
    TabLayout tabLayout;
    @Inject
    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_header);
        ButterKnife.bind(this);

        /** Receiving the Training object to use it in the chosen recyclerview item **/
        Intent i = getIntent();
        ProfileDetails.Training training;  training = (ProfileDetails.Training)i.getSerializableExtra("Training");

        initializingUI(training);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        setupViewPager(viewPager,training);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                Log.d(TAG, "onTabSelected: pos: " + tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                     //   showToast("One");
                        break;
                    case 1:
                  //     showToast("Two");
                        break;
                    case 2:
                        tabLayout.setSmoothScrollingEnabled(true);
                        tabLayout.setScrollPosition(2, 0f, true);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }



/**
 * InitilizeUI , sets up the header image and the header text.
 * */
    private void initializingUI(ProfileDetails.Training training){
        Glide
                .with(this)
                .load(util.imagebaseURL+training.coverUrl)
                .into(ImageHeader);

                header_title.setText(training.name);

    }

    /**
     * SetupViewPager sets up with viewpager with fragments ( Description , Videos , Cours )
     * */

    private void setupViewPager(ViewPager viewPager,ProfileDetails.Training training) {
         adapter = new ViewPagerAdapter(getSupportFragmentManager() );

        adapter.addFrag(new DetailsDescriptionFragment(training.description), "Description");
        adapter.addFrag(new DetailsVideoFragment(training), "Vidéos");
        adapter.addFrag(new DetailsCoursFragment(training.detailsURL), "Cours");
        viewPager.setOffscreenPageLimit(4); //before setAdapter
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// todo: goto back activity from here
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}