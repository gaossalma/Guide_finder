package com.example.gaossalma.guide_login.listview;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.gaossalma.guide_login.DetailListBeritaActivity;
import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.adapter.BeritaAdapter;
import com.example.gaossalma.guide_login.adapter.ConstantBerita;
import com.example.gaossalma.guide_login.adapter.RecyclerItemClickListener;
import com.example.gaossalma.guide_login.apihelper.BaseApiService;
import com.example.gaossalma.guide_login.apihelper.UtilsApi;
import com.example.gaossalma.guide_login.model.berita.ResponseBerita;
import com.example.gaossalma.guide_login.model.berita.SemuaBeritaItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBeritaItemActivity extends AppCompatActivity {

    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    @BindView(R.id.rvBerita)
    RecyclerView rvBerita;
    List<SemuaBeritaItem> mItems = new ArrayList<>();
    BeritaAdapter beritaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_berita);

        getSupportActionBar().setTitle("judul");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getApiService();

        beritaAdapter = new BeritaAdapter(this, mItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvBerita.setLayoutManager(mLayoutManager);
        rvBerita.setItemAnimator(new DefaultItemAnimator());
//        mRecycler = (RecyclerView) findViewById(R.id.rvBerita);
//        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        mRecycler.setLayoutManager(mManager);

//        mContext = this;


//        beritaAdapter = new BeritaAdapter(this, semuaBeritaItemsList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        rvBerita.setLayoutManager(mLayoutManager);
//        rvBerita.setItemAnimator(new DefaultItemAnimator());

        getResultListBerita();
    }

    private void getResultListBerita() {
        mApiService.getsemuaberita().enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {

//                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
//                mItems = response.body().getResult();
//                mRecycler.setAdapter(mAdapter);
//                mAdapter.notifyDataSetChanged();
                if (response.isSuccessful()){
                    final List<SemuaBeritaItem> semuaBeritaItemList = response.body().getSemuaBerita();
                    rvBerita.setAdapter(new BeritaAdapter(mContext, semuaBeritaItemList));
                    beritaAdapter.notifyDataSetChanged();
                    initDataBerita(semuaBeritaItemList);
                } else {
                    Toast.makeText(mContext, "gagal Mengambil Data Berita", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
//                Log.d("RETRO", "FAILED : RESPONS GAGAL");
                Toast.makeText(mContext, "koneksi bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataBerita(final List<SemuaBeritaItem> semuaBeritaItemList) {
        rvBerita.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id_berita = semuaBeritaItemList.get(position).getId_berita();
                String tgl_terbit = semuaBeritaItemList.get(position).getTgl_terbit();
                String judul = semuaBeritaItemList.get(position).getJudul();
                String isi = semuaBeritaItemList.get(position).getIsi();

                Intent detailBerita = new Intent(mContext, DetailListBeritaActivity.class);
                detailBerita.putExtra(ConstantBerita.KEY_ID_BERITA, id_berita);
                detailBerita.putExtra(ConstantBerita.KEY_TGL_TERBIT, tgl_terbit);
                detailBerita.putExtra(ConstantBerita.KEY_JUDUL, judul);
                detailBerita.putExtra(ConstantBerita.KEY_ISI, isi);
                startActivity(detailBerita);

            }
        }));
    }


}
