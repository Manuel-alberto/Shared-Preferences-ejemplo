package com.neluamcode.sharedpreferences.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.neluamcode.sharedpreferences.R;
import com.neluamcode.sharedpreferences.Utils.Util;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private EditText editTextEmail, editTextPassword;
    private Switch switchRemember;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs=getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        bindUI();

        setCredentialsIfExists();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (login(email, password)){
                    goToMain();
                    saveOnPrefeneces(email, password);
                }
            }
        });

    }

    private void bindUI(){
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    private void saveOnPrefeneces(String email, String password){
        if(switchRemember.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.commit();
            editor.apply();
        }
    }

    private void setCredentialsIfExists(){
        String email = Util.getUserMailPreferences(prefs);
        String password = Util.getUserPasswordPreferences(prefs);
        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
            editTextPassword.setText(password);
            editTextEmail.setText(email);
        }
    }


    private boolean login(String email, String password){
        if (!isValiedEmail(email)){
            Toast.makeText(this, "No es valido el correo, introduce uno que lo sea", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!isValiedPassword(password)){
            Toast.makeText(this, "Password no es valido debe tener 4 caracteres o mas ", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private boolean isValiedEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValiedPassword(String password){
        return password.length() >= 4;
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}