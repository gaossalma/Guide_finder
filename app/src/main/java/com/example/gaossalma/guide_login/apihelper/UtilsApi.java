package com.example.gaossalma.guide_login.apihelper;

public class UtilsApi {
    public static final  String base_url = "http://192.168.1.8 /laravel-guide/public/api/";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(base_url).create(BaseApiService.class);
    }
}
