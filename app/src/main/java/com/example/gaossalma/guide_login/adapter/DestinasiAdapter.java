package com.example.gaossalma.guide_login.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.model.berita.SemuaBeritaItem;
import com.example.gaossalma.guide_login.model.destinasi.SemuaDestinasiItem;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DestinasiAdapter extends RecyclerView.Adapter<DestinasiAdapter.DestinasiHolder>{
    private List<SemuaDestinasiItem> mList ;
    private Context ctx;

    public String[] mColors = {
            "#39add1",
            "#3079ab",
            "#c25975"
    };

    public DestinasiAdapter(Context ctx, List<SemuaDestinasiItem> destinasiList)
    {
        this.ctx = ctx;
        mList = destinasiList;
    }

    @NonNull
    @Override
    public DestinasiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_destinasi_item,parent, false);
        return new DestinasiHolder(layout);
    }

    @Override
    public void onBindViewHolder(DestinasiHolder holder, int position) {
        final SemuaDestinasiItem semuaDestinasiItem = mList.get(position);
        holder.nama.setText(semuaDestinasiItem.getNama());
        holder.longitude.setText(semuaDestinasiItem.getLangitude());
        holder.latitude.setText(semuaDestinasiItem.getLatitude());
        holder.deskripsi.setText(semuaDestinasiItem.getDeskripsi());

        String namaDestinasi = semuaDestinasiItem.getNama();
        String firstCharJudulDestinasi = namaDestinasi.substring(0,1);
        TextDrawable drawable = TextDrawable.builder().buildRound(firstCharJudulDestinasi, getColor());
        holder.imgDestinasi.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public int getColor() {
        String color;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);
        return colorAsInt;
    }

    public class DestinasiHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNama)
        TextView nama;
        @BindView(R.id.longitud)
        TextView longitude;
        @BindView(R.id.latitude)
        TextView latitude;
        @BindView(R.id.tvDeskripsi)
        TextView deskripsi;
        @BindView(R.id.ivDestinasi)
        ImageView imgDestinasi;
        public DestinasiHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
