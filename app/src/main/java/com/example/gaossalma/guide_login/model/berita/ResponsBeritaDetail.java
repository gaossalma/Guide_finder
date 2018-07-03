package com.example.gaossalma.guide_login.model.berita;

import com.google.gson.annotations.SerializedName;

public class ResponsBeritaDetail {
    @SerializedName("tgl_terbit")
    private String tgl_terbit;

    @SerializedName("judul")
    private String judul;

    @SerializedName("isi")
    private String isi;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public String getTgl_terbit() {
        return tgl_terbit;
    }

    public String getJudul() {
        return judul;
    }

    public void setTgl_terbit(String tgl_terbit) {
        this.tgl_terbit = tgl_terbit;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "ResponseBeritaDetail{" +
                "tgl_terbit = '" + tgl_terbit + '\'' +
                ",judul = '" + judul +'\'' +
                ",isi = '" + isi +'\'' +
                ",error = '" + error +'\'' +
                ",message = '" + message + '\'' + "}";
    }
}
