package com.example.gaossalma.guide_login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gaossalma.guide_login.adapter.ConstantDestinasi;
import com.example.gaossalma.guide_login.apihelper.BaseApiService;
import com.example.gaossalma.guide_login.apihelper.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailListDestinasiActivity extends AppCompatActivity {
    @BindView(R.id.tvNama)
    TextView nama;
    @BindView(R.id.tvlongitud)
    TextView longitud;
    @BindView(R.id.tvLatitude)
    TextView latitud;
    @BindView(R.id.tvDeskripsi)
    TextView deskripsi;
    String mNama, mLongitud, mLatitud, mDeskripsi;
    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_destinasi);

        getSupportActionBar().setTitle("Berita Detail");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getApiService();

        Intent intent = getIntent();
        mNama = intent.getStringExtra(ConstantDestinasi.KEY_NAMA);
        mLongitud = intent.getStringExtra(ConstantDestinasi.KEY_LONGITUD);
        mLatitud = intent.getStringExtra(ConstantDestinasi.KEY_LATITUD);
        mDeskripsi = intent.getStringExtra(ConstantDestinasi.KEY_DESKRIPSI);

        nama.setText(mNama);
        longitud.setText(mLongitud);
        latitud.setText(mLatitud);
        deskripsi.setText(mDeskripsi);

    }
}
