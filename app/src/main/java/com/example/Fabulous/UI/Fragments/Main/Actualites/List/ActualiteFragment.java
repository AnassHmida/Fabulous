package com.example.Fabulous.UI.Fragments.Main.Actualites.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Fabulous.Models.ActualiteDetails;
import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.R;

import com.example.Fabulous.UI.Adapters.ActualitesAdapter;
import com.example.Fabulous.UI.Fragments.Main.Actualites.NewPost.NewPostDialogFragment;
import com.example.Fabulous.UI.Fragments.Resource;
import com.example.Fabulous.ViewModel.ViewModelProviderFactory;
import com.google.gson.JsonElement;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;
import io.paperdb.Paper;

import static android.content.ContentValues.TAG;


public class ActualiteFragment extends DaggerFragment {
    @BindView(R.id.actualite_recyclerview)
    RecyclerView actualitesRecyclerView;
    private ActualiteViewModel viewModel;
    private  rx.Subscription subscription;
    @Inject
     ActualitesAdapter adapter;
    @Inject
    ViewModelProviderFactory providerFactory;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actualite_layout, parent, false);
        ButterKnife.bind(this,view);
        Paper.init(getActivity().getApplicationContext());
        actualitesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         adapter = new ActualitesAdapter();
        actualitesRecyclerView.setAdapter(adapter);
        //  LoadActualites(ProfileDetailsParser.getIDfromResponse(element));
        return view;


    }

    @OnClick(R.id.new_post)
    public void NewPost(){
        NewPostDialogFragment addPostDialogFragment = NewPostDialogFragment.newInstance();
        addPostDialogFragment.show(getFragmentManager(),
                "add_post_dialog_fragment");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this,providerFactory).get(ActualiteViewModel.class);
        ProfileDetails element= Paper.book().read("loggedmodel");
        SubscribeOberservers(element.token.id);
    }
    private void successfulLogin(JsonElement actualiteResponseModel) {
//        actualitesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
   //     ActualitesAdapter adapter = new ActualitesAdapter(getActivity(), ActualitesParser.getActualitesPostsFromElement(actualiteResponseModel));
  //      actualitesRecyclerView.setAdapter(adapter);

    }
    private void showError(String call) {

        if(call.equals("network error")) {
            Log.d(TAG, "showError: "+"Network error");
        }
    }


    private void SubscribeOberservers(String Authorization){
        viewModel.queryAllActualiteByAuthorization(Authorization).removeObservers(getViewLifecycleOwner());
        viewModel.queryAllActualiteByAuthorization(Authorization).observe(getViewLifecycleOwner(), new Observer<Resource<ActualiteDetails>>() {
            @Override
            public void onChanged(Resource<ActualiteDetails> postPostsResource) {
                if(postPostsResource != null){
                    switch(postPostsResource.status){
                        case DATARECEIVED:{
                             Log.d(TAG, "onChanged: Successfully got all of the data, TITLE : " + postPostsResource.data.posts.get(0).description);

                            adapter.setTrainings(postPostsResource.data.posts);

                            break;
                        }
                        case ERROR:{

                            Log.d(TAG, "onChanged:  Error ! " + postPostsResource.message);
                            break;

                        }case LOADING:{
                            Log.d(TAG, "onChanged:  Loading data ...");
                            break;
                        }
                        case NOT_AUTHENTICATED:
                        {
                            break;
                        }
                    }
                }
            }
        });
    }



}