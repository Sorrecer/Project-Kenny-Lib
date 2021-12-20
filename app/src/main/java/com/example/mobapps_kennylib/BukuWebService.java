package com.example.mobapps_kennylib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BukuWebService {
    private final String ID = "idbuku";
    private final String ISBN = "isbn";
    private final String JUDUL = "judul";
    private final String KATEGORI = "kategori";
    private final String PENGARANG = "pengarang";
    private final String STOK = "stok";
    private final String KOTATERBIT = "kota_terbit";

    public ArrayList<Buku> getBuku(String response) throws JSONException {
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

    public Buku getOneBuku(String response) throws JSONException {
        JSONObject object = new JSONObject(response);

        Buku buku = new Buku(object.getInt(ID));
        buku.setIsbn(object.getString(ISBN));
        buku.setJudul(object.getString(JUDUL));
        buku.setPengarang(object.getString(PENGARANG));
        buku.setKategori(object.getString(KATEGORI));
        buku.setStok(object.getInt(STOK));
        buku.setKotaTerbit(object.getString(KOTATERBIT));
        return buku;
    }
}
