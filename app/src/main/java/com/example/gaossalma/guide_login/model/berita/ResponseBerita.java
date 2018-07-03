package com.example.gaossalma.guide_login.model.berita;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBerita {

    @SerializedName("semuaberita")
    private List<SemuaBeritaItem> semuaBerita;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public void setSemuaBerita(List<SemuaBeritaItem> semuaBerita){
        this.semuaBerita = semuaBerita;
    }

    public List<SemuaBeritaItem> getSemuaBerita() {
        return semuaBerita;
    }


    public boolean isError() {
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResponseBerita{" +
                "semuaberita = '" + semuaBerita + '\'' +
                ",error = '" + error +'\'' +
                ",message = '" + message + '\'' + "}";
    }
}
