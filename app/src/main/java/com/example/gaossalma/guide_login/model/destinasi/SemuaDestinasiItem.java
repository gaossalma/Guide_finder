package com.example.gaossalma.guide_login.model.destinasi;

import com.google.gson.annotations.SerializedName;

public class SemuaDestinasiItem {
    @SerializedName("id_destinasi")
    private String id_destinasi;

    @SerializedName("nama")
    private String nama;

    @SerializedName("langitude")
    private String langitude;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("fasilitas")
    private String fasilitas;

    public String getId_destinasi() {
        return id_destinasi;
    }

    public void setId_destinasi(String id_destinasi) {
        this.id_destinasi = id_destinasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLangitude() {
        return langitude;
    }

    public void setLangitude(String langitude) {
        this.langitude = langitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    @Override
    public String toString() {
        return "SemuaDestinasiItem{" +
                "nama = '" + nama + '\'' +
                ",langitude = '" + langitude +'\'' +
                ",latitude = '" + latitude + '\'' +
                ",deskripsi = '" + deskripsi + '\'' +
                ",fasilitas = '" + fasilitas + '\'' + "}";
    }
}
