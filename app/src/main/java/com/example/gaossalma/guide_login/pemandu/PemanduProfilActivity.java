package com.example.gaossalma.guide_login.pemandu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.adapter.SharedPrefPemandu;

public class PemanduProfilActivity extends AppCompatActivity {
    SharedPrefPemandu sharedPrefPemandu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pemandu_profil );

        TextView fullname = (TextView) findViewById( R.id.PPfullname );
        TextView nickname = (TextView) findViewById( R.id.PPnickname );
        TextView email = (TextView) findViewById( R.id.PPemail );
        TextView jenis_kelamin = (TextView) findViewById( R.id.PPjenis_kelamin );
        TextView bahasa = (TextView) findViewById( R.id.PPlanguage );
        TextView tlp = (TextView) findViewById( R.id.PPphone_number );
        TextView harga = (TextView) findViewById( R.id.PPharga );
        sharedPrefPemandu = new SharedPrefPemandu(this);
        fullname.setText( sharedPrefPemandu.getSpFullname() );
        nickname.setText( sharedPrefPemandu.getSpNickname() );
        email.setText( sharedPrefPemandu.getSpEmail() );
        jenis_kelamin.setText( sharedPrefPemandu.getSpJenisKelamin() );
        bahasa.setText( sharedPrefPemandu.getSpBahasa() );
        tlp.setText( sharedPrefPemandu.getSpTlp() );
        harga.setText( sharedPrefPemandu.getSpHarga() );


    }
}
