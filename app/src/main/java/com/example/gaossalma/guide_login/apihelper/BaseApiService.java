package com.example.gaossalma.guide_login.apihelper;

import com.example.gaossalma.guide_login.model.berita.ResponseBerita;
import com.example.gaossalma.guide_login.model.destinasi.ResponseDestinasi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("user")
    Call<ResponseBody> loginrequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("login_pemandu.php")
    Call<ResponseBody> loginpemandu(@Field("email") String email,
                                    @Field("password") String password);


    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerrequest(@Field("nama") String nama,
                                       @Field("username") String username,
                                       @Field("email") String email,
                                       @Field("jenis_kelamin") String jenis_kelamin,
                                       @Field("negara") String negara,
                                       @Field("ttl") String ttl,
                                       @Field("nomor_tlp") String nomor_tlp,
                                       @Field("bahasa") String bahasa,
                                       @Field("password") String password);

    @GET("beritas")
    Call<ResponseBerita> getsemuaberita();

    @GET("beritas/{id}")
    Call<ResponseBerita> getDetailBerita(@Path("tgl_terbit") String tgl_terbit,
                                         @Path("judul") String judul,
                                         @Path("isi") String isi);

    @GET("destinasis")
    Call<ResponseDestinasi> getsemuadestinasi();

    @GET("destinasis/{id}")
    Call<ResponseDestinasi> getDetailDestinasi(@Path("nama") String nama,
                                               @Path("langitud") String langitud,
                                               @Path("latitude") String latitud,
                                               @Path("deskripsi") String deskripsi);
}
