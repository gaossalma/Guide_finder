package com.example.gaossalma.guide_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gaossalma.guide_login.adapter.SharedPrefManager;
import com.example.gaossalma.guide_login.listview.ListBeritaItemActivity;
import com.example.gaossalma.guide_login.listview.ListDestinasiItemActivity;
import com.example.gaossalma.guide_login.listview.ListPilihDestinasiItemActivity;

public class MainActivity extends AppCompatActivity {
    TextView tvResultNama;
    Button btLogout;
//    String resultNama;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout news = (LinearLayout) findViewById(R.id.News);
        LinearLayout pencarian = (LinearLayout) findViewById(R.id.pencarian);
        LinearLayout destination = (LinearLayout) findViewById(R.id.destination);
        LinearLayout profile = (LinearLayout) findViewById(R.id.profile);
        TextView tvResultNama = (TextView) findViewById( R.id.Username );
        TextView tvResultUsername = (TextView) findViewById( R.id.Nama );
        TextView tvResultNegara = (TextView) findViewById( R.id.Negara );
        TextView tvResultTTL = (TextView) findViewById( R.id.TTL );
        TextView tvResultJK = (TextView) findViewById( R.id.Jenis_kelamin );
        TextView tvResultBahasa = (TextView) findViewById( R.id.Bahasa );
        TextView tvResultNo_tlp = (TextView) findViewById( R.id.No_tlp );
//        initComponents();
        sharedPrefManager = new SharedPrefManager( this );
        tvResultNama.setText( sharedPrefManager.getSpUsername() );
        tvResultUsername.setText( sharedPrefManager.getSpUsername() );
        tvResultNegara.setText( sharedPrefManager.getSpNegara() );
        tvResultTTL.setText( sharedPrefManager.getSpTtl() );
        tvResultJK.setText( sharedPrefManager.getSpJk() );
        tvResultBahasa.setText( sharedPrefManager.getSpBahasa() );
        tvResultNo_tlp.setText( sharedPrefManager.getSpNoTlp() );





        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gonews = new Intent(MainActivity.this, ListBeritaItemActivity.class);
                startActivity(gonews);
            }
        });

        pencarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gofind = new Intent(MainActivity.this, ListPilihDestinasiItemActivity.class);
                startActivity(gofind);
            }
        });

        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDestination = new Intent(MainActivity.this, ListDestinasiItemActivity.class);
                startActivity(goDestination);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goprofile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(goprofile);
            }
        });
        sharedPrefManager = new SharedPrefManager(this);

        btLogout = (Button) findViewById(R.id.logout);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(MainActivity.this, StartActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });


    }

    private void initComponents() {
        tvResultNama = (TextView) findViewById(R.id.username);
    }
}
