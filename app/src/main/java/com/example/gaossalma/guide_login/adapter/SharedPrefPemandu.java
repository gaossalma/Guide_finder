package com.example.gaossalma.guide_login.adapter;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefPemandu {
    public static String SP_PEMANDU_APP = "spPemanduApp";
    public static String SP_FULLNAME = "SpFullname";
    public static String SP_NICKNAME = "spNickname";
    public static String SP_EMAIL = "spEmail";
    public static String SP_JENIS_KELAMIN = "spJenisKelamin";
    public static String SP_BAHASA = "spBahasa";
    public static String SP_TLP = "spTlp";
    public static String SP_HARGA = "spHarga";
    public static String SP_SUDAH_LOGIN = "spSudahLogin";



    int MODE_PRIVATE = 0;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;


    public SharedPrefPemandu(Context mContext) {
        sp = mContext.getSharedPreferences(SP_PEMANDU_APP, mContext.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public  void saveSPString(String keySP,String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }
    public String getSpFullname() {
        return sp.getString(SP_FULLNAME,"");
    }

    public String getSpNickname() {
        return sp.getString(SP_NICKNAME,"");
    }

    public String getSpEmail() {
        return sp.getString(SP_EMAIL,"");
    }

    public String getSpJenisKelamin() {
        return sp.getString(SP_JENIS_KELAMIN,"");
    }

    public String getSpBahasa() {
        return sp.getString(SP_BAHASA,"");
    }

    public String getSpTlp() {
        return sp.getString(SP_TLP,"");
    }

    public String getSpHarga() {
        return sp.getString(SP_HARGA,"");
    }

    public boolean getSpSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

}
