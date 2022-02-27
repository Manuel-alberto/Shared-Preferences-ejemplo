package com.neluamcode.sharedpreferences.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.neluamcode.sharedpreferences.Activities.LoginActivity;
import com.neluamcode.sharedpreferences.Activities.MainActivity;
import com.neluamcode.sharedpreferences.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin=new Intent(this, LoginActivity.class);
        Intent intentMain=new Intent(this, MainActivity.class);

        if (!TextUtils.isEmpty(Util.getUserMailPreferences(prefs)) && !TextUtils.isEmpty(Util.getUserPasswordPreferences(prefs))){
            startActivity(intentMain);
        }else{
            startActivity(intentLogin);
        }
        finish();

    }


}