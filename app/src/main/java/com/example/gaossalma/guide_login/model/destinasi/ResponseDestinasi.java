package com.example.gaossalma.guide_login.model.destinasi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDestinasi {
    @SerializedName("semuadestinasi")
    private List<SemuaDestinasiItem> semuaDestinasi;

    @SerializedName("error")
    private boolean error;


    @SerializedName("message")
    private String message;

    public List<SemuaDestinasiItem> getSemuaDestinasi() {
        return semuaDestinasi;
    }

    public void setSemuaDestinasi(List<SemuaDestinasiItem> semuaDestinasi) {
        this.semuaDestinasi = semuaDestinasi;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




    @Override
    public String toString() {
        return "ResponseDestinasiItem{" +
                "semuadestinasi = '" + semuaDestinasi + '\'' +
                ",error = '" + error +'\'' +
                ",message = '" + message + '\'' + "}";
    }
}
