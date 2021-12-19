package com.example.mobapps_kennylib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BukuWebService {
    private final String serviceuri = "http://localhost/mini-project/service/";
    private final String ID = "idbuku";
    private final String ISBN = "isbn";
    private final String JUDUL = "judul";
    private final String KATEGORI = "kategori";
    private final String PENGARANG = "pengarang";
    private final String STOK = "stok";

    public String getService(String uri) throws IOException{
        URL url = new URL(serviceuri+uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        System.out.println("Response status: " + status);
        System.out.println(content.toString());
        return content.toString();
    }

    public ArrayList<Buku> getBuku() throws IOException, JSONException {
        String response = getService("buku/");
        JSONArray array = new JSONArray(response);

        ArrayList<Buku> bukus = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            Buku buku = new Buku(obj.getInt(ID));
            buku.setIsbn(obj.getString(ISBN));
            buku.setJudul(obj.getString(JUDUL));
            buku.setPengarang(obj.getString(PENGARANG));
            buku.setKategori(obj.getString(KATEGORI));
            buku.setStok(obj.getInt(STOK));
            bukus.add(buku);
        }
        return bukus;
    }
}
