package com.example.Fabulous.UI.Fragments.Main.Actualites.NewPost;




import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.Fabulous.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpinnerDialogFragment extends BottomSheetDialogFragment {
    @BindView(R.id.first_item)
    TextView first_item;
    @BindView(R.id.second_item)
    TextView second_item;
    @BindView(R.id.third_item)
    TextView third_item;
    @BindView(R.id.fourth_item)
    TextView fourth_item;
private SpinnerDialogListener spinnerDialogListener;

    public static SpinnerDialogFragment newInstance() {
        return new SpinnerDialogFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            spinnerDialogListener =(SpinnerDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must  implement Spinner dialog listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_spinner_layout, container, false);
        ButterKnife.bind(this,view);
        return view;

    }
    @OnClick(R.id.first_item)
    public void first(){
spinnerDialogListener.ApplyText(first_item.getText().toString(),"1");

        dismiss();



    }
    @OnClick(R.id.second_item)
    public void  second(){
        spinnerDialogListener.ApplyText(second_item.getText().toString(),"2");
        dismiss();


    }
    @OnClick(R.id.third_item)
    public void third(){
        spinnerDialogListener.ApplyText(third_item.getText().toString(),"3");
        dismiss();


    }
    @OnClick(R.id.fourth_item)
    public void fourth(){
        spinnerDialogListener.ApplyText(fourth_item.getText().toString(),"4");
        dismiss();


    }
    public interface SpinnerDialogListener{
        void ApplyText(String chosenitem,String chosenid);
    }
}