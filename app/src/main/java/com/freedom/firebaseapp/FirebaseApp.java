package com.freedom.firebaseapp;

import android.app.Application;

import com.freedom.firebaseapp.util.RealmController;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Mayur on 3/2/2017.
 */

public class FirebaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        com.google.firebase.FirebaseApp.initializeApp(getApplicationContext());
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        RealmController.with(this);
    }
}
