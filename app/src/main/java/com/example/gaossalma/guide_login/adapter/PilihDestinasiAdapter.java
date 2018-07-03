package com.example.gaossalma.guide_login.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.model.destinasi.SemuaDestinasiItem;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PilihDestinasiAdapter extends RecyclerView.Adapter<PilihDestinasiAdapter.PilihDestinasiHolder>{
    private List<SemuaDestinasiItem> mList ;
    private Context ctx;
    private HashMap<Integer, Boolean> isChecked = new HashMap<>();

    public PilihDestinasiAdapter(Context ctx, List<SemuaDestinasiItem> destinasiList)
    {
        this.ctx = ctx;
        mList = destinasiList;
    }

    @NonNull
    @Override
    public PilihDestinasiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pilih_destinasi_item,parent,
                false);
        return new PilihDestinasiHolder(layout);
    }

    @Override
    public void onBindViewHolder(PilihDestinasiHolder holder, int position) {
        final SemuaDestinasiItem semuaDestinasiItem = mList.get(position);
        holder.cbDestinasi.setText(semuaDestinasiItem.getNama());
        if (isChecked.containsKey(position)) {
            holder.cbDestinasi.setChecked(isChecked.get(position));
        } else {
            holder.cbDestinasi.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

//    public int getColor() {
//        String color;
//        Random randomGenerator = new Random();
//        int randomNumber = randomGenerator.nextInt(mColors.length);
//
//        color = mColors[randomNumber];
//        int colorAsInt = Color.parseColor(color);
//        return colorAsInt;
//    }

    public class PilihDestinasiHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cbDestinasi)
        CheckBox cbDestinasi;
        public PilihDestinasiHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cbDestinasi.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        Toast.makeText( ctx, "checklist", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( ctx, "Unchecklist", Toast.LENGTH_SHORT ).show();
                    }
                }
            } );
        }
    }
}
