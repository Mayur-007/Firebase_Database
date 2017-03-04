package com.freedom.firebaseapp.views.userlist;

import com.freedom.firebaseapp.api.model.User;

import java.util.List;

/**
 * Created by Mayur on 2/2/2017.
 */

public class UserListContract {

    interface View {
        void showError(String errorMessage);

        void displayUsers(List<User> dataUsers);
    }

    interface Presenter {
        void getUsersOffline();

        void addUser(String id, String name, String about, String image);

        void addListener();
    }
}