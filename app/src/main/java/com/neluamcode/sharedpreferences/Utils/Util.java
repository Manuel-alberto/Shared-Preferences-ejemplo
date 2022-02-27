package com.neluamcode.sharedpreferences.Utils;

import android.content.SharedPreferences;

public class Util {

    public static String getUserMailPreferences(SharedPreferences sharedPreferences){
        return sharedPreferences.getString("email", "");
    }

    public static String getUserPasswordPreferences(SharedPreferences sharedPreferences){
        return sharedPreferences.getString("password", "");
    }

}
