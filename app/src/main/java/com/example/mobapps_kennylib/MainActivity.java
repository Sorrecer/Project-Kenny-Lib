package com.example.mobapps_kennylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.tv_user);
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String nim = sharedPref.getString("nim", "0");
        user.setText(nim);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String nim = sharedPref.getString("nim", "0");
        System.out.println(nim);
        user.setText(nim);
    }

    public void pindah(View view){
        switch (view.getId()){
            case R.id.lihat_buku:
                startActivity(new Intent(MainActivity.this, LihatBukuActivity.class));
                break;
            case R.id.cari_buku:
                startActivity(new Intent(MainActivity.this, CariBukuActivity.class));
                break;
            case R.id.ganti_session:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
    }
}