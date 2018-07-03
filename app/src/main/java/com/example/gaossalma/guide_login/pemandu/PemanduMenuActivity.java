package com.example.gaossalma.guide_login.pemandu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.StartActivity;
import com.example.gaossalma.guide_login.adapter.SharedPrefManager;
import com.example.gaossalma.guide_login.adapter.SharedPrefPemandu;

public class PemanduMenuActivity extends AppCompatActivity {

    SharedPrefPemandu sharedPrefPemandu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pemandu_menu );

        TextView tvFullname = (TextView) findViewById( R.id.Pnickname );
        LinearLayout pemberitahuan = (LinearLayout) findViewById( R.id.Pemberitahuan );
        LinearLayout history = (LinearLayout) findViewById( R.id.History );
        LinearLayout profil = (LinearLayout) findViewById( R.id.profile );
        LinearLayout about = (LinearLayout) findViewById( R.id.About );
//        initComponents();
        sharedPrefPemandu = new SharedPrefPemandu( this );
        tvFullname.setText( sharedPrefPemandu.getSpNickname() );
        Button btLogout = (Button) findViewById(R.id.logout);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefPemandu.saveSPBoolean( SharedPrefPemandu.SP_SUDAH_LOGIN, false );
                startActivity( new Intent( PemanduMenuActivity
                        .this, StartActivity.class )
                        .addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK ) );
                finish();
            }
        });

        profil.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goPemanduProfil = new Intent( PemanduMenuActivity.this, PemanduProfilActivity.class );
                startActivity( goPemanduProfil );
            }
        } );

        pemberitahuan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
    }
}
