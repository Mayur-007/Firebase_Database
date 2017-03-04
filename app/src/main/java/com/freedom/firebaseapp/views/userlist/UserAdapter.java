package com.freedom.firebaseapp.views.userlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.freedom.firebaseapp.R;
import com.freedom.firebaseapp.api.model.User;

import java.util.List;

/**
 * Created by Mayur on 2/2/2017.
 */

class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    Context context;
    private List<User> usersList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView dp;
        TextView name, about;

        MyViewHolder(View view) {
            super(view);
            dp = (ImageView) view.findViewById(R.id.user_dp);
            name = (TextView) view.findViewById(R.id.user_name);
            about = (TextView) view.findViewById(R.id.user_about);
        }
    }


    UserAdapter(Context context, List<User> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = usersList.get(position);

        final String imgUrl = user.image;
        if(imgUrl.contains("http"))
            Glide.with(context).load(imgUrl).into(holder.dp);

        holder.name.setText(user.name);
        holder.about.setText(user.about);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}