package com.freedom.firebaseapp.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Created by Mayur on 2/2/2017.
 */

public class Util {

    private static final Pattern INDIAN_PHONE_NUMBER_PATTERN = Pattern.compile("^((\\\\+91-?)|0)?[0-9]{10}$");

    public static boolean isBlank(CharSequence string){
        return (string == null || string.toString().trim().length() == 0);
    }

    public static boolean isValidEmailId(String email)
    {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobileNo(String phoneNumber)
    {
        return !TextUtils.isEmpty(phoneNumber) && INDIAN_PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void displaySnack(View view, String message)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static Bitmap getBitmap(Context context, String folderName, String filename)
    {
        try {
            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getDir(folderName, Context.MODE_PRIVATE);
            File fileImage = new File(directory , filename);
            return BitmapFactory.decodeStream(new FileInputStream(fileImage));
        }
        catch (FileNotFoundException e) {
            return null;
        }
    }

    public static String saveBitmap(Context context, Bitmap bitmapImage, String folderName, String filename) throws IOException {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(folderName, Context.MODE_PRIVATE);
        File fileImage = new File(directory , filename);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileImage);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
        } finally {
            fos.close();
        }
        return directory.getAbsolutePath();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            return null;
        }
    }
}
