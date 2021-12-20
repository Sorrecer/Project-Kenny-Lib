package com.example.mobapps_kennylib;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class LihatBukuActivity extends AppCompatActivity {

    ArrayList<Buku> bukusList;
    RecyclerView recyclerView2;
    RecyclerAdapter recyclerAdapter;
    Context act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_buku);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        act = this;

        WebService webService = new WebService(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                BukuWebService bukuWebService = new BukuWebService();
                try {
                    bukusList = bukuWebService.getBuku(s);
                    recyclerView2 = findViewById(R.id.recyclerView2);
                    recyclerAdapter = new RecyclerAdapter(bukusList);

                    System.out.println(recyclerAdapter);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(act));
                    recyclerView2.setAdapter(recyclerAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        SharedPreferences sharedPref = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String nim = sharedPref.getString("nim", "0");
        webService.execute("buku/pinjam/"+nim+"/", "GET");

//        recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        fetchData = new ArrayList<>();
//
//


    }
}