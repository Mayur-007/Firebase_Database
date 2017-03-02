package com.freedom.firebaseapp.views.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freedom.firebaseapp.R;
import com.freedom.firebaseapp.api.model.User;

import java.util.List;

/**
 * Created by Mayur on 2/2/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> usersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, about;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.user_name);
            about = (TextView) view.findViewById(R.id.user_about);
        }
    }


    public UserAdapter(List<User> usersList) {
        this.usersList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User User = usersList.get(position);
        holder.name.setText(User.name);
        holder.about.setText(User.about);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}