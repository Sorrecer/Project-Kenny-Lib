package com.example.mobapps_kennylib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

public class DetailBukuActivity extends AppCompatActivity {

    TextView eIsbn;
    TextView eJudul;
    TextView ePenulis;
    TextView eKategori;
    TextView eKota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);
        eIsbn = findViewById(R.id.tv_isbn);
        eJudul = findViewById(R.id.tv_judul);
        ePenulis = findViewById(R.id.tv_penulis);
        eKategori = findViewById(R.id.tv_kategori);
        eKota = findViewById(R.id.tv_kota_terbit);

        WebService webService = new WebService(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                BukuWebService bukuWebService = new BukuWebService();
                try {
                    Buku buku = bukuWebService.getOneBuku(s);
                    eIsbn.setText(buku.getIsbn());
                    eJudul.setText(buku.getJudul());
                    ePenulis.setText(buku.getPengarang());
                    eKategori.setText(buku.getKategori());
                    eKota.setText(buku.getKotaTerbit());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        Intent intent = getIntent();
        webService.execute("buku/"+intent.getIntExtra("id", 0)+"/", "GET");

    }
}