package com.example.gaossalma.guide_login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gaossalma.guide_login.adapter.ConstantBerita;
import com.example.gaossalma.guide_login.apihelper.BaseApiService;
import com.example.gaossalma.guide_login.apihelper.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailListBeritaActivity extends AppCompatActivity {

    @BindView(R.id.judul)
    TextView tvJudul;

    @BindView(R.id.tgl)
    TextView tvTgl;

    @BindView(R.id.isi)
    TextView tvIsi;

//    @BindView(R.id.iv_berita)
//    TextView ivBerita;

    String mJudul, mTgl, mIsi;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_berita);

        getSupportActionBar().setTitle("Berita Detail");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getApiService();

        Intent intent = getIntent();
        mJudul = intent.getStringExtra(ConstantBerita.KEY_JUDUL);
        mTgl = intent.getStringExtra(ConstantBerita.KEY_TGL_TERBIT);
        mIsi = intent.getStringExtra(ConstantBerita.KEY_ISI);

        tvJudul.setText(mJudul);
        tvTgl.setText(mTgl);
        tvIsi.setText(mIsi);
    }
}
