package com.example.gaossalma.guide_login.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.listview.ListBeritaItemActivity;
import com.example.gaossalma.guide_login.model.berita.SemuaBeritaItem;
import com.example.gaossalma.guide_login.model.destinasi.SemuaDestinasiItem;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaHolder> {
    private List<SemuaBeritaItem> mSemuaBeritaItems ;
    private Context ctx;

    public String[] mColors = {
            "#39add1",
            "#3079ab",
            "#c25975"
    };

    public BeritaAdapter(Context ctx, List<SemuaBeritaItem> beritaList)
    {
        this.ctx = ctx;
        mSemuaBeritaItems = beritaList;
    }

    @Override
    public BeritaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_berita_item,parent, false);
        return new BeritaHolder(layout);

    }

    @Override
    public void onBindViewHolder(BeritaHolder holder, int position) {
        final SemuaBeritaItem semuaBeritaItem = mSemuaBeritaItems.get(position);
        holder.tgl_post.setText(semuaBeritaItem.getTgl_terbit());
        holder.judul.setText(semuaBeritaItem.getJudul());
        holder.isi.setText(semuaBeritaItem.getIsi());

        String judulBerita = semuaBeritaItem.getJudul();
        String firstCharJudulBerita = judulBerita.substring(0,1);
        TextDrawable drawable = TextDrawable.builder().buildRound(firstCharJudulBerita, getColor());
        holder.imgBerita.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return mSemuaBeritaItems.size();
    }

    public int getColor() {
        String color;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);
        return colorAsInt;
    }

    public class BeritaHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.judul)
        TextView judul;
        @BindView(R.id.tgl)
        TextView tgl_post;
        @BindView(R.id.isi)
        TextView isi;
        @BindView(R.id.imgBerita)
        ImageView imgBerita;

        public BeritaHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
