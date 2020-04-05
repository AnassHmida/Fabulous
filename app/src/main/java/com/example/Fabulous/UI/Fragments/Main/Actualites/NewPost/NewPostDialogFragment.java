package com.example.Fabulous.UI.Fragments.Main.Actualites.NewPost;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.Fabulous.R;
import com.example.Fabulous.UI.Activities.ActualiteActivity.NewActualitePostActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewPostDialogFragment extends BottomSheetDialogFragment {

    public static NewPostDialogFragment newInstance() {
        return new NewPostDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sheet_post_layout, container, false);
        ButterKnife.bind(this,view);



        return view;

    }
    @OnClick(R.id.confirm_new_post)
    public void Confirm(){

        Intent i = new Intent(getActivity().getApplicationContext(), NewActualitePostActivity.class);
        startActivity(i);


    }
    @OnClick(R.id.confirm_new_montage)
    public void  NewMontage(){
        dismiss();


    }
    @OnClick(R.id.cancel_post)
    public void Cancel(){
        dismiss();


    }
}