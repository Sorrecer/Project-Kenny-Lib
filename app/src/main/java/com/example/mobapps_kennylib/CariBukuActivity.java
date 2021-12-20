package com.example.mobapps_kennylib;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CariBukuActivity extends AppCompatActivity {

    List<Buku> fetchData;

    RecyclerView recyclerView;
    ImageButton btnBack;

    ArrayList<Buku> bukus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_buku);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData = new ArrayList<>();

        //INI BLOCK CODE TERKAIT DATABASE
        WebService webService = new WebService(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                BukuWebService bukuWebService = new BukuWebService();
                try {
                    System.out.println(s);
                    fetchData = bukuWebService.getBuku(s);
                    Adapter adapter = new Adapter(fetchData);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        webService.execute("buku/", "GET");


        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}