package com.freedom.firebaseapp.api.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mayur on 3/2/2017.
 */

public class User extends RealmObject {

    public static String USER_ID = "id";

    @PrimaryKey
    public String id;
    public String name;
    public String about;
    public String image;

    public User() {
    }

    public User(String id, String name, String about, String image) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.image = image;
    }
}