package com.example.Fabulous.UI.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.Fabulous.Models.ActualiteDetails;
import com.example.Fabulous.R;
import com.example.Fabulous.Utils.util;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ActualitesAdapter extends RecyclerView.Adapter<ActualitesAdapter.ViewHolder>{

    List<ActualiteDetails.PostsEntity> Posts = new ArrayList<>();






    /***
     * Set training function is used to replace the existing item in the recycler view and refresh it
     * @param Posts is the list in which will replace the already existing posts list */

    public void setTrainings(List<ActualiteDetails.PostsEntity> Posts) {
        this.Posts = Posts;
        notifyDataSetChanged();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem= inflater.inflate(R.layout.actualite_item_layout, parent, false);
        viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.actualite_title.setText(Posts.get(position).training.name);
      holder.user_name.setText(Posts.get(position).creator.name);
        holder.actualite_description.setText(Posts.get(position).description);
        holder.like_count.setText(Posts.get(position).likes+" likes");
        String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
               DateFormat format = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(Posts.get(position).createdat);
            PrettyTime p = new PrettyTime();
          //  System.out.println("it's time to : "+p.format(date));
            holder.user_last_login.setText(p.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Glide
                .with(holder.itemView.getContext())
                .load(util.imagebaseURL+Posts.get(position).pictureurl)
                .into(holder.mainimg);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.like.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.like));
                holder.like_count.setText((Integer.valueOf(Posts.get(position).likes)+1)+" Likes");
            }
        });

        if(Posts.get(position).creator.provideravatarurl!=null){
            Glide
                    .with(holder.itemView.getContext())
                    .load(Posts.get(position).creator.provideravatarurl)
                    .into(holder.user_img);

        }
        if(Posts.get(position).liked){
            holder.like.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.like));
        }


    }
    @Override
    public int getItemCount() {
        return Posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView user_img,mainimg,title,like;
        private TextView user_name,actualite_description,actualite_title;
        private TextView itemDescription,like_count,user_last_login;
        public LinearLayout item;




        private ViewHolder(View itemView) {
            super(itemView);
            this.actualite_description = (TextView) itemView.findViewById(R.id.actualite_description);
           this.user_name = (TextView) itemView.findViewById(R.id.user_name);
            this.like = (ImageView) itemView.findViewById(R.id.like);
            this.like_count = (TextView) itemView.findViewById(R.id.llike_count);
            this.user_img = (ImageView) itemView.findViewById(R.id.user_img);
            this.mainimg = (ImageView) itemView.findViewById(R.id.mainimg);
            this.actualite_title =(TextView) itemView.findViewById(R.id.actualite_title);
            this.user_last_login =(TextView) itemView.findViewById(R.id.user_last_login);


        }
    }
}