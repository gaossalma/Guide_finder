package com.example.gaossalma.guide_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HasilActivity extends AppCompatActivity {

    @BindView( R.id.latitudinal )
    TextView tvlati;
    @BindView( R.id.longitudinal )
    TextView tvlongi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hasil );

        ButterKnife.bind(this);


    }
}
