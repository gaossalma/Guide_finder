package com.example.gaossalma.guide_login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gaossalma.guide_login.adapter.SharedPrefManager;
import com.example.gaossalma.guide_login.apihelper.BaseApiService;
import com.example.gaossalma.guide_login.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

//    private static String SP_SUDAH_LOGIN = "spSudahLogin";

    EditText etPassword, etEmail;
    Button btnLogin, btnRegister;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPrefManager = new SharedPrefManager(this);
        mContext = this;
        mApiService = UtilsApi.getApiService();
        initComponents();
        if (sharedPrefManager.getSpSudahLogin()){
            startActivity(new Intent(mContext, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }



    }

    private void initComponents() {
        etEmail = (EditText) findViewById(R.id.e_mail);
        etPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        btnRegister = (Button) findViewById(R.id.register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loading = ProgressDialog.show(mContext, null, "harap Tunggu", true, false);
                requestLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });


    }

    private void requestLogin() {
        mApiService.loginrequest(etEmail.getText().toString(), etPassword.getText().toString()).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
//                    loading.dismiss();
                    try {
                        JSONObject jsonResult = new JSONObject(response.body().string());
                        if (jsonResult.getString("error").equals("false")){
                            Toast.makeText(mContext, "Berhasil Login", Toast.LENGTH_SHORT).show();
//                            String nama = jsonResult.getJSONObject("user").getString("nama");
//                            String username = jsonResult.getJSONObject("user").getString("username");
                            String email = jsonResult.getJSONObject( "user" ).getString( "email" );
//                            String jenis_kelamin = jsonResult.getJSONObject( "user" ).getString( "jenis_kelamin" );
//                            String negara = jsonResult.getJSONObject( "user" ).getString( "negara" );
//                            String ttl = jsonResult.getJSONObject( "user" ).getString( "ttl" );
//                            String nomor_tlp = jsonResult.getJSONObject( "user" ).getString( "nomor_tlp" );
//                            String bahasa = jsonResult.getJSONObject( "user" ).getString( "bahasa" );
//                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
//                            sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, username);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
//                            sharedPrefManager.saveSPString(SharedPrefManager.SP_JK, jenis_kelamin);
//                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NEGARA, negara);
//                            sharedPrefManager.saveSPString(SharedPrefManager.SP_TTL, ttl);
//                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NO_TLP, nomor_tlp);
//                            sharedPrefManager.saveSPString(SharedPrefManager.SP_BAHASA, bahasa);
                            //trigger session login
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
//                            Intent intent = new Intent(mContext, MainActivity.class)
//                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
//                            intent.putExtra("result_nama", nama);
                            startActivity(new Intent(mContext, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                            startActivity( intent );

                            finish();
                        } else {
                            String error_message = jsonResult.getString("error_msg");
                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                            Toast.makeText( mContext, "gagal Login", Toast.LENGTH_SHORT ).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(mContext, "failed jaringan", Toast.LENGTH_SHORT).show();
//                    loading.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                Toast.makeText( mContext, "gagal", Toast.LENGTH_SHORT ).show();
            }
        });
    }
}
