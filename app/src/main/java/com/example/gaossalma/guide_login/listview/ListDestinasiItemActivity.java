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

import com.example.gaossalma.guide_login.DetailListDestinasiActivity;
import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.adapter.ConstantDestinasi;
import com.example.gaossalma.guide_login.adapter.DestinasiAdapter;
import com.example.gaossalma.guide_login.adapter.RecyclerItemClickListener;
import com.example.gaossalma.guide_login.apihelper.BaseApiService;
import com.example.gaossalma.guide_login.apihelper.UtilsApi;
import com.example.gaossalma.guide_login.model.destinasi.ResponseDestinasi;
import com.example.gaossalma.guide_login.model.destinasi.SemuaDestinasiItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDestinasiItemActivity extends AppCompatActivity {

    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    @BindView(R.id.rvDestinasi)
    RecyclerView rvDestinasi;
    List<SemuaDestinasiItem> mItems = new ArrayList<>();
    DestinasiAdapter destinasiAdapter;

    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_destinasi);
        getSupportActionBar().setTitle("judul");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getApiService();
        destinasiAdapter = new DestinasiAdapter(this, mItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvDestinasi.setLayoutManager(mLayoutManager);
        adapter = new DestinasiAdapter( this, mItems );
//        rvDestinasi.setItemAnimator(new DefaultItemAnimator());
        rvDestinasi.setAdapter( adapter );
        getResultListDestinasi();
    }

    public void getResultListDestinasi() {
        mApiService.getsemuadestinasi().enqueue(new Callback<ResponseDestinasi>() {
            @Override
            public void onResponse(Call<ResponseDestinasi> call, Response<ResponseDestinasi> response) {
                if (response.isSuccessful()) {
                    final List<SemuaDestinasiItem> semuaDestinasiItemList = response.body().getSemuaDestinasi();
                    rvDestinasi.setAdapter(new DestinasiAdapter(mContext, semuaDestinasiItemList));
                    destinasiAdapter.notifyDataSetChanged();
                    initDataDestinasi(semuaDestinasiItemList);
                } else {
                    Toast.makeText(mContext, "gagal Mengambil Data Dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDestinasi> call, Throwable t) {
                Toast.makeText(mContext, "koneksi bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataDestinasi(final List<SemuaDestinasiItem> semuaDestinasiItemList) {
        rvDestinasi.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id_destinasi = semuaDestinasiItemList.get(position).getId_destinasi();
                String nama = semuaDestinasiItemList.get(position).getNama();
                String longitud = semuaDestinasiItemList.get(position).getLangitude();
                String latitud = semuaDestinasiItemList.get(position).getLatitude();
                String deskripsi = semuaDestinasiItemList.get(position).getDeskripsi();
                String fasilitas = semuaDestinasiItemList.get(position).getFasilitas();

                Intent detailDestinasi = new Intent(mContext, DetailListDestinasiActivity.class);
                detailDestinasi.putExtra(ConstantDestinasi.KEY_ID_DESTINASI, id_destinasi);
                detailDestinasi.putExtra(ConstantDestinasi.KEY_NAMA, nama);
                detailDestinasi.putExtra(ConstantDestinasi.KEY_LONGITUD, longitud);
                detailDestinasi.putExtra(ConstantDestinasi.KEY_LATITUD, latitud);
                detailDestinasi.putExtra(ConstantDestinasi.KEY_DESKRIPSI, deskripsi);
                detailDestinasi.putExtra(ConstantDestinasi.KEY_FASILITAS, fasilitas);
                startActivity(detailDestinasi);
            }
        }));
    }
}
