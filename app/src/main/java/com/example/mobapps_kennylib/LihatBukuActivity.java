package com.example.mobapps_kennylib;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LihatBukuActivity extends AppCompatActivity {

    List<Buku> fetchData;
    RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_buku);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        fetchData = new ArrayList<>();
//
//


    }
}