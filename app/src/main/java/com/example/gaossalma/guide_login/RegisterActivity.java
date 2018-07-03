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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gaossalma.guide_login.apihelper.BaseApiService;
import com.example.gaossalma.guide_login.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etPassword, etEmail, etNama, etUsername, etJK, etTTL, etNomor_tlp, etBahasa, etNegara;
    RadioButton rbMale, rbFemale;
    RadioGroup rgJK;
    Button btnRegister;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;
        mApiService = UtilsApi.getApiService();
        initComponents();
    }

    private void initComponents() {
        etNama = (EditText) findViewById(R.id.nama);
        etUsername = (EditText) findViewById(R.id.username);
        etEmail = (EditText) findViewById(R.id.e_mail);
        etJK = (EditText) findViewById(R.id.jenis_kelamin);
        etTTL = (EditText) findViewById(R.id.ttl);
        etNegara = (EditText) findViewById(R.id.negara);
        etNomor_tlp = (EditText) findViewById(R.id.nomor_tlp);
        etBahasa = (EditText) findViewById(R.id.bahasa);
        etPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int selectedJK = rgJK.getCheckedRadioButtonId();
//                if (selectedJK == rbMale.getId()){
//                    rbMale.getText().toString();
//                } else if(selectedJK == rbFemale.getId()){
//                    rbFemale.getText().toString();
//                }
                //loading = ProgressDialog.show(mContext, null, "harap Tunggu", true, false);
                requestRegister();
            }
        });
    }

    private void requestRegister() {
        mApiService.registerrequest(etNama.getText().toString(),
                etUsername.getText().toString(),
                etEmail.getText().toString(),
                etJK.getText().toString(),
                etNegara.getText().toString(),
                etTTL.getText().toString(),
                etNomor_tlp.getText().toString(),
                etBahasa.getText().toString(),
                etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
//                            loading.dismiss();
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("error").equals("false")){
                                    Toast.makeText(mContext, "Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
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
                            Log.i("debug", "onResponse: Gagal");
                            Toast.makeText(mContext, "Gagal respon", Toast.LENGTH_SHORT).show();
//                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        Toast.makeText(mContext, "koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
