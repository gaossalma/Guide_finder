package com.example.gaossalma.guide_login.adapter;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static String SP_WISATAWAN_APP = "spWisatawanApp";
    public static String SP_NAMA = "spNama";
    public static String SP_NEGARA = "spNegara";
    public static String SP_EMAIL = "spEmail";
    public static String SP_SUDAH_LOGIN = "spSudahLogin";
    public static String SP_USERNAME = "spUsername";
    public static String SP_TTL = "spTtl";
    public static String SP_JK = "spJK";
    public static String SP_NO_TLP = "spNo_tlp";
    public static String SP_BAHASA = "spBahasa";
//    public static String SP_HARGA = "spHarga";

    int MODE_PRIVATE = 0;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context mConext){
        sp = mConext.getSharedPreferences(SP_WISATAWAN_APP, mConext.MODE_PRIVATE);
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

//    public String getSpHarga() {
////        return SP_HARGA;
//        return sp.getString(SP_HARGA,"");
//    }

    public String getSP_Nama(){
        return sp.getString(SP_NAMA,"");
    }
    public String getSpUsername() {       return sp.getString(SP_USERNAME,"");
    }

    public String getSpNegara() {
//        return SP_NEGARA;
        return sp.getString(SP_NEGARA,"");
    }
    public String getSpTtl() {
//        return SP_TTL;
        return sp.getString(SP_TTL,"");
    }

    public String getSpJk() {
//        return SP_JK;
        return sp.getString(SP_JK,"");
    }

    public String getSpNoTlp() {
//        return SP_NO_TLP;
        return sp.getString(SP_NO_TLP,"");
    }

    public String getSpBahasa() {
//        return SP_BAHASA;
        return sp.getString(SP_BAHASA,"");
    }

    public static void setSpNegara(String spNegara) {
        SP_NEGARA = spNegara;
    }


    public String getSP_Email(){
        return sp.getString(SP_EMAIL,"");
    }

    public boolean getSpSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
