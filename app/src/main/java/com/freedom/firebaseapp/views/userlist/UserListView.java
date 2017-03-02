package com.freedom.firebaseapp.views.userlist;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.freedom.firebaseapp.R;
import com.freedom.firebaseapp.api.model.User;
import com.freedom.firebaseapp.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mayur on 2/2/2017.
 */

public class UserListView extends AppCompatActivity implements UserListContract.View {

    Context context;

    private RecyclerView listUser;
    private CoordinatorLayout mainLayout;

    private List<User> dataUsers = new ArrayList<>();
    private UserAdapter userAdapter;

    UserListPresenter presenter;
    public UserListView() {
        presenter = new UserListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = UserListView.this;

        listUser = (RecyclerView) findViewById(R.id.list_users);
        mainLayout = (CoordinatorLayout) findViewById(R.id.user_list_main);

        userAdapter = new UserAdapter(dataUsers);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listUser.setLayoutManager(mLayoutManager);
        listUser.setItemAnimator(new DefaultItemAnimator());
        listUser.setAdapter(userAdapter);

        presenter.addUser("1", "Anand","CEO","----");
        presenter.addUser("2", "Arnab Saha","Tech Lead","---");
        presenter.addUser("3", "Mayur Patanvadiya","Android Dev","----");

        presenter.getUsersOffline();
    }

    @Override
    public void displayUsers(List<User> dataUsers) {
        this.dataUsers.clear();
        this.dataUsers.addAll(dataUsers);
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        Util.displaySnack(mainLayout, "Something went wrong, Try again.");
    }
}