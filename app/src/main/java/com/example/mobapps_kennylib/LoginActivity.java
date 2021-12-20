package com.example.mobapps_kennylib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    ImageButton btnBack;
    EditText enim;
    EditText epassword;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enim = findViewById(R.id.username);
        epassword = findViewById(R.id.password);

        SharedPreferences sharedPref = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        editor = sharedPref.edit();

        /*
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        */

    }

    public void Login(View view) throws NoSuchAlgorithmException {
        switch (view.getId()) {
            case R.id.btn_login:
                if(enim.getText().toString().equals("") || epassword.getText().toString().equals("")){
                    Toast.makeText(view.getContext(), "Isian ada yang kosong", Toast.LENGTH_SHORT).show();
                }
                else {
                    String nim = enim.getText().toString();
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] p = md.digest(epassword.getText().toString().getBytes());
                    BigInteger no = new BigInteger(1, p);

                    // Convert message digest into hex value
                    String hashtext = no.toString(16);
                    while (hashtext.length() < 32) {
                        hashtext = "0" + hashtext;
                    }
                    String password = hashtext;
                    WebService webService = new WebService(){
                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            if(s.equals("true")){
                                editor.putString("nim", nim);
                                System.out.println(nim);
                                editor.apply();
                                finish();
                            }
                            else {
                                Toast.makeText(view.getContext(), "nim atau password tidak sesuai", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    webService.execute("user/validate/", "POST", "{\"nim\": \""+nim+"\", \"password\":\""+password+"\"}");
                }
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
        }
    }
}