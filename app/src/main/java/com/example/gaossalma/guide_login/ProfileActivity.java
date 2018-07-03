package com.example.gaossalma.guide_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gaossalma.guide_login.adapter.SharedPrefManager;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        TextView tvNama = (TextView) findViewById( R.id.Pfullname );
        TextView tvNegara = (TextView) findViewById( R.id.Pnegara );
        TextView tvUsername = (TextView) findViewById( R.id.PUsername );
        TextView tvEmail = (TextView) findViewById( R.id.Pe_mail );
        TextView tvJK = (TextView) findViewById( R.id.PJK );
        TextView tvResultNo_tlp = (TextView) findViewById( R.id.Pphone_number );
        TextView tvBahasa = (TextView) findViewById( R.id.Pbahasa );
        TextView tvTtl = (TextView) findViewById( R.id.Pttl );
        sharedPrefManager = new SharedPrefManager( this );
        tvUsername.setText( sharedPrefManager.getSpUsername() );
        tvNama.setText(sharedPrefManager.getSP_Nama());
        tvEmail.setText(sharedPrefManager.getSP_Email());
        tvNegara.setText( sharedPrefManager.getSpNegara() );
        tvJK.setText(sharedPrefManager.getSpJk());
        tvResultNo_tlp.setText( sharedPrefManager.getSpNoTlp() );
        tvBahasa.setText( sharedPrefManager.getSpBahasa() );
        tvTtl.setText( sharedPrefManager.getSpTtl() );

    }
}
