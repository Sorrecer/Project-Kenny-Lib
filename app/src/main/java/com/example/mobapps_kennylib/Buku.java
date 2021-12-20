package com.example.mobapps_kennylib;

public class Buku {
    private int idbuku;
    private String isbn;
    private String judul;
    private String kategori;
    private String pengarang;
    private String kotaTerbit;
    private int stok;

    public Buku(int idbuku){
        this.idbuku = idbuku;
    }

    public Buku(int idbuku, String isbn, String judul){
        this.idbuku = idbuku;
        this.isbn = isbn;
        this.judul = judul;
    }

    public Buku(int idbuku, String isbn, String judul, String kategori, String pengarang, int stok){
        this.idbuku = idbuku;
        this.isbn = isbn;
        this.judul = judul;
        this.kategori = kategori;
        this.pengarang = pengarang;
        this.stok = stok;
    }

    public void setIdbuku(int idbuku) {
        this.idbuku = idbuku;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setKotaTerbit(String kotaTerbit) {
        this.kotaTerbit = kotaTerbit;
    }

    public int getIdbuku() {
        return idbuku;
    }

    public int getStok() {
        return stok;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getJudul() {
        return judul;
    }

    public String getKategori() {
        return kategori;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getKotaTerbit() {
        return kotaTerbit;
    }
}
