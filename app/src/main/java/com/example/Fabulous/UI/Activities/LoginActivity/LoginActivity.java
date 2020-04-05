package com.example.Fabulous.UI.Activities.LoginActivity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.Models.User;
import com.example.Fabulous.R;

import com.example.Fabulous.SessionManager;
import com.example.Fabulous.UI.Activities.MainActivity.MainActivity;

import com.example.Fabulous.UI.Fragments.Resource;
import com.example.Fabulous.Utils.util;
import com.example.Fabulous.ViewModel.ViewModelProviderFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import io.paperdb.Paper;



import static android.content.ContentValues.TAG;

public class LoginActivity extends DaggerAppCompatActivity {
    @BindView(R.id.email_textInput)
    TextInputEditText emailEditText;
    @BindView(R.id.password_textInput)
    TextInputEditText passwordEditText;
    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;
    @BindView(R.id.eye)
    ImageView eyeButton;
    @BindView(R.id.login_button)
    Button loginButton;

    private LoginViewModel viewModel;

    BottomSheetBehavior sheetBehavior;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this,providerFactory).get(LoginViewModel.class);




        Paper.init(this);
        ButterKnife.bind(this);
        ProfileDetails element= Paper.book().read("loggedmodel");
        if(element==null){
            SubscribeObservers();
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        eyeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        return true;
                    case MotionEvent.ACTION_UP:
                        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        return true;
                }
                return false;
            }
        });
        }else{

           viewModel.AutoAuthentication(LoginResource.authenticated(element));
           viewModel.observeAuthState().observe(this, new Observer<LoginResource<ProfileDetails>>() {
               @Override
               public void onChanged(LoginResource<ProfileDetails> profileDetailsLoginResource) {
                   successfulLogin(profileDetailsLoginResource.data);
               }
           });

        }

    }


    /**
     * Once the login button is clicked , the function check password is triggered.
     * **/

    @OnClick(R.id.login_button)
    public void login(){
        util.HideSheet(sheetBehavior);
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        checkPassword(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

/**
 * The check password function will check if the entered fields email and password are correct , once they are we will pass to the main login fucntion.
 * @param email is going to be checked is it's empty and  if it's valid
 *  @param password is going to be checked if it's length is bigger than 5 chars
 * **/

    public void checkPassword(String email, String password){

        if(TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            util.showToast(getApplicationContext(), "Enter valid email");
        }else if(password.length() <= 3){
            util.showToast(getApplicationContext(), "Please enter password longer than 5 characters");
        }else{

            viewModel.AuthenticateWithUser(new User(email,password));

        }
    }




    @Override
    public void onBackPressed() {
        /***
         * Commented out super to disable backpress.
         * */
// super.onBackPressed();
    }


    public void successfulLogin(ProfileDetails loginResponseModel) {


        Paper.book().write(util.bookkey, loginResponseModel);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    /**
     * In case of an error , the show error function is called , to reveal a bottom sheet that contains an error message.
     * **/



    public void showError(String call, String message) {

        if(call.equals("network error")) util.showSheet(sheetBehavior);
    }


    /**
     * This is set to observe The authentification state.
     */
    private void SubscribeObservers(){
        viewModel.observeAuthState().observe(this, new androidx.lifecycle.Observer<LoginResource<ProfileDetails>>() {
            @Override
            public void onChanged(LoginResource<ProfileDetails> userLoginResource) {
                if(userLoginResource != null){
                    switch (userLoginResource.status){
                        case LOADING:{

                            break;
                        }
                        case AUTHENTICATED:
                            successfulLogin(userLoginResource.data);
                            Log.d(TAG, "onChanged:  LOGIN SUCESS" + userLoginResource.data.getProfile().details.name);
                            break;
                        case ERROR:{
                         showError("network error","");
                            //Toast.makeText(LoginActivity.this,userLoginResource.message +" \n , Did you enter a number between 1 and 10 ?", Toast.LENGTH_LONG).show();
                            break;
                        }case NOT_AUTHENTICATED:{
                            showError("network error","");
                            break;
                        }
                    }
                }
            }
        });
    }


}
