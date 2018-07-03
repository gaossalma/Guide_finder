package com.example.gaossalma.guide_login.pemandu;

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

import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.adapter.SharedPrefPemandu;
import com.example.gaossalma.guide_login.apihelper.BaseApiService;
import com.example.gaossalma.guide_login.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class LoginPemanduActivity extends AppCompatActivity {

    EditText etPassword, etEmail;
    Button btnLogin, btnRegister;
    SharedPrefPemandu sharedPrefPemandu;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_pemandu );

            sharedPrefPemandu = new SharedPrefPemandu(this);
            mContext = this;
            mApiService = UtilsApi.getApiService();
            initComponents();
            if (sharedPrefPemandu.getSpSudahLogin()){
                startActivity(new Intent(mContext, PemanduMenuActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }



        }

        private void initComponents() {
            etEmail = (EditText) findViewById(R.id.Pemandu_Email);
            etPassword = (EditText) findViewById(R.id.Pemandu_Password);
            btnLogin = (Button) findViewById(R.id.Pemandu_Login);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                loading = ProgressDialog.show(mContext, null, "harap Tunggu", true, false);
                    requestLogin();
                }
            });

//            btnRegister.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(mContext, RegisterActivity.class));
//                }
//            });


        }

        private void requestLogin() {
            mApiService.loginpemandu(etEmail.getText().toString(), etPassword.getText().toString()).enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
//                    loading.dismiss();
                        try {
                            JSONObject jsonResult = new JSONObject(response.body().string());
                            if (jsonResult.getString("error").equals("false")){
                                Toast.makeText(mContext, "Berhasil Login", Toast.LENGTH_SHORT).show();
                                String fullname = jsonResult.getJSONObject("pemandu").getString("fullname");
                                String nickname = jsonResult.getJSONObject("pemandu").getString("nickname");
                                String email = jsonResult.getJSONObject( "pemandu" ).getString( "email" );
                                String jenis_kelamin = jsonResult.getJSONObject( "pemandu" ).getString( "jenis_kelamin" );
                                String bahasa = jsonResult.getJSONObject( "pemandu" ).getString( "bahasa" );
                                String tlp = jsonResult.getJSONObject( "pemandu" ).getString( "tlp" );
                                String harga = jsonResult.getJSONObject( "pemandu" ).getString( "harga" );
//                                String bahasa = jsonResult.getJSONObject( "user" ).getString( "bahasa" );
                                sharedPrefPemandu.saveSPString(sharedPrefPemandu.SP_FULLNAME, fullname);
                                sharedPrefPemandu.saveSPString(sharedPrefPemandu.SP_NICKNAME, nickname);
                                sharedPrefPemandu.saveSPString(sharedPrefPemandu.SP_EMAIL, email);
                                sharedPrefPemandu.saveSPString(sharedPrefPemandu.SP_JENIS_KELAMIN, jenis_kelamin);
                                sharedPrefPemandu.saveSPString(sharedPrefPemandu.SP_BAHASA, bahasa);
                                sharedPrefPemandu.saveSPString(sharedPrefPemandu.SP_TLP, tlp);
                                sharedPrefPemandu.saveSPString(sharedPrefPemandu.SP_HARGA, harga);
//                                sharedPrefManager.saveSPString(SharedPrefManager.SP_BAHASA, bahasa);
                                //trigger session login
                                sharedPrefPemandu.saveSPBoolean(sharedPrefPemandu.SP_SUDAH_LOGIN, true);
//                            Intent intent = new Intent(mContext, MainActivity.class)
//                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
//                            intent.putExtra("result_nama", nama);
                                startActivity(new Intent(mContext, PemanduMenuActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                            startActivity( intent );

                                finish();
                            } else {
                                String error_message = jsonResult.getString("error_msg");
                                Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(mContext, "failed", Toast.LENGTH_SHORT).show();
//                        loading.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.toString());
                }
            });
        }

}
