package com.example.mobapps_kennylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pindah(View view){
        switch (view.getId()){
            case R.id.lihat_buku:
                startActivity(new Intent(MainActivity.this, LihatBukuActivity.class));
                break;
            case R.id.cari_buku:
                startActivity(new Intent(MainActivity.this, CariBukuActivity.class));
                break;
        }
    }
}