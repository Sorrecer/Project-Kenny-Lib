package com.example.mobapps_kennylib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CariBukuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    ArrayList<Buku> bukusList;
    ArrayList<Buku> bukus;
    Context act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(this);
        super.onCreate(savedInstanceState);
        act = this;
        setContentView(R.layout.activity_cari_buku);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        WebService webService = new WebService(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                BukuWebService bukuWebService = new BukuWebService();
                try {
                    bukusList = bukuWebService.getBuku(s);
                    recyclerView = findViewById(R.id.recyclerView);
                    recyclerAdapter = new RecyclerAdapter(bukusList);
                    System.out.println(recyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(act));
                    recyclerView.setAdapter(recyclerAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        webService.execute("buku/", "GET");

        /*
        moviesList = new ArrayList<>();
        moviesList.add("Iron Man");
        moviesList.add("The Incredible Hulk");
        moviesList.add("Iron Man 2");
        moviesList.add("Thor");
        moviesList.add("Captain America: The First Avenger");
        moviesList.add("The Avengers");
        moviesList.add("Iron Man 3");
        moviesList.add("Thor: The Dark World");
        moviesList.add("Captain America: The Winter Soldier");
        moviesList.add("Guardians of the Galaxy");
        moviesList.add("Avengers: Age of Ultron");
        moviesList.add("Ant-Man");
        moviesList.add("Captain America: Civil War");
        moviesList.add("Doctor Strange");
        moviesList.add("Guardians of the Galaxy Vol. 2");
        moviesList.add("Spider-Man: Homecoming");
        moviesList.add("Thor: Ragnarok");
        moviesList.add("Black Panther");
        moviesList.add("Avengers: Infinity War");
        moviesList.add("Ant-Man and the Wasp");
        moviesList.add("Captain Marvel");
        moviesList.add("Avengers: Endgame");
        moviesList.add("Spider-Man: Far From Home");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(moviesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}