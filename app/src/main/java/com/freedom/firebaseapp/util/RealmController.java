package com.freedom.firebaseapp.util;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.freedom.firebaseapp.api.model.User;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Mayur on 2/2/2017.
 */

public class RealmController {

    private static RealmController instance;
    private Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
        return realm;
    }

    // =======================================================================================================
    // ========================================= ALL MODELS DEFINED HERE =====================================
    // =======================================================================================================

    // ========================================= User ==============================================

    // Find all objects in the User
    public List<User> getAllUsers() {

        return realm.where(User.class).findAll();
    }

    // Clear all objects from User
    public void clearAllUsers() {

        realm.beginTransaction();
        realm.delete(User.class);
        realm.commitTransaction();
    }
}