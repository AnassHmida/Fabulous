package com.example.Fabulous.UI.Activities.SplashScreenActivity;



import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;



import android.view.View;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.Fabulous.R;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginActivity;
import com.example.Fabulous.UI.Activities.LoginActivity.LoginViewModel;
import com.example.Fabulous.ViewModel.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;


public class SplashScreenActivity extends DaggerAppCompatActivity {

    private SplashScreenViewModel viewModel;


    @Inject
    ViewModelProviderFactory providerFactory;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.splashscreen_layout);
        ButterKnife.bind(this);

        viewModel = new ViewModelProvider(this,providerFactory).get(SplashScreenViewModel.class);

        viewModel.observeState().observe(this, splashScreenResource -> {
            switch (splashScreenResource.status){
                case DONE:{
                    goToMainActivity();
                    break;
                }

        }
        });
    }
        private void goToMainActivity() {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

    @Override
    public void onBackPressed() {
// super.onBackPressed();
    }

}