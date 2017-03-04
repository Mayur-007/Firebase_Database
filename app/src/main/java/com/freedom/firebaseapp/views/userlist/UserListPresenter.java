package com.freedom.firebaseapp.views.userlist;

import com.freedom.firebaseapp.api.model.User;
import com.freedom.firebaseapp.util.RealmController;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Mayur on 2/2/2017.
 */

public class UserListPresenter implements UserListContract.Presenter {

    private UserListContract.View usersView;
    private Realm realm;
    private DatabaseReference mFirebaseDatabase;

    public UserListPresenter(UserListContract.View view) {
        usersView = view;
        realm = RealmController.getInstance().getRealm();
        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
    }

    @Override
    public void getUsersOffline() {
        List<User> dataUsers = new ArrayList<>();
        dataUsers.addAll(RealmController.getInstance().getAllUsers());
        usersView.displayUsers(dataUsers);
    }

    @Override
    public void addUser(String id, String name, String about, String image) {
        User user = new User(id, name, about, image);
        mFirebaseDatabase.push().setValue(user);
        mFirebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(user);
                realm.commitTransaction();
                getUsersOffline();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(user);
                realm.commitTransaction();
                getUsersOffline();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<User> result = realm.where(User.class).equalTo(User.USER_ID,user.id).findAll();
                        result.deleteAllFromRealm();
                    }
                });
                getUsersOffline();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void addListener() {
        mFirebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    User user = dataSnapshot.getValue(User.class);
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    getUsersOffline();
                } catch (Exception ignored) {}
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                try {
                    User user = dataSnapshot.getValue(User.class);
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    getUsersOffline();
                } catch (Exception ignored) {}
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                try {
                    final User user = dataSnapshot.getValue(User.class);
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<User> result = realm.where(User.class).equalTo(User.USER_ID, user.id).findAll();
                            result.deleteAllFromRealm();
                        }
                    });
                    getUsersOffline();
                } catch (Exception ignored) {}
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}