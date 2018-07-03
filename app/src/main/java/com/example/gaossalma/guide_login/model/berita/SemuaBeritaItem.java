package com.example.gaossalma.guide_login.model.berita;

import com.google.gson.annotations.SerializedName;

public class SemuaBeritaItem {

//    String id_berita, judul, tgl_post, isi;
//
//    public String getId_berita() {
//        return id_berita;
//    }
//
//    public void setId_berita(String id_berita) {
//        this.id_berita = id_berita;
//    }
//
//    public String getJudul() {
//        return judul;
//    }
//
//    public void setJudul(String judul) {
//        this.judul = judul;
//    }
//
//    public String getTgl_post() {
//        return tgl_post;
//    }
//
//    public void setTgl_post(String tgl_post) {
//        this.tgl_post = tgl_post;
//    }
//
//    public String getIsi() {
//        return isi;
//    }
//
//    public void setIsi(String isi) {
//        this.isi = isi;
//    }



    @SerializedName("tgl_terbit")
    private String tgl_terbit;

    @SerializedName("judul")
    private String judul;

    @SerializedName("isi")
    private String isi;

    @SerializedName("id_berita")
    private String id_berita;

    public void setId_berita(String id_berita) {
        this.id_berita = id_berita;
    }

    public void setTgl_terbit(String tgl_terbit) {
        this.tgl_terbit = tgl_terbit;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getId_berita() {
        return id_berita;
    }

    public String getTgl_terbit() {
        return tgl_terbit;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }

    @Override
    public String toString() {
        return "SemuaBeritaItem{" +
                "tgl_terbit = '" + tgl_terbit + '\'' +
                ",judul = '" + judul +'\'' +
                ",isi = '" + isi + '\'' + "}";
    }
}
