package com.example.Fabulous.UI.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.Fabulous.Models.ProfileDetails;
import com.example.Fabulous.R;
import com.example.Fabulous.UI.Activities.DetailsActivity.DetailsActivity;
import com.example.Fabulous.Utils.util;
import java.util.ArrayList;



public class MesFormationsAdapter extends RecyclerView.Adapter<MesFormationsAdapter.ViewHolder>{

    ArrayList<ProfileDetails.Training> trainings = new ArrayList<>();






/***
 * Set training function is used to replace the existing item in the recycler view and refresh it
 * @param training is the list in which will replace the already existing trainings list */

    public void setData(ArrayList<ProfileDetails.Training> training) {
        this.trainings = training;
        notifyDataSetChanged();

    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem= inflater.inflate(R.layout.mesformations_single_item, parent, false);
        viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.itemTitle.setText(trainings.get(position).name);
        holder.itemDescription.setText(trainings.get(position).description);
        Glide
                .with(holder.itemView.getContext())
                .load(util.imagebaseURL+trainings.get(position).coverUrl)
                .into(holder.itemImage);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), DetailsActivity.class);

                i.putExtra("Training", trainings.get(position));
                holder.itemView.getContext().startActivity(i);

            }
        });
    }
  @Override
    public int getItemCount() {
        return trainings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemDescription;
        public LinearLayout item;




        private ViewHolder(View itemView) {
            super(itemView);
            this.item = (LinearLayout) itemView.findViewById(R.id.item);
            this.itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            this.itemTitle = (TextView) itemView.findViewById(R.id.itemName);
            this.itemDescription = (TextView) itemView.findViewById(R.id.itemDescription);
        }
    }
}