package com.example.gaossalma.guide_login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gaossalma.guide_login.pemandu.LoginPemanduActivity;

public class StartActivity extends AppCompatActivity {

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start );

        mContext = this;
       Button guide = (Button) findViewById( R.id.guide );
       Button tourist = (Button) findViewById( R.id.tourist );

       guide.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent goGuide = new Intent( mContext, LoginPemanduActivity.class );
               startActivity(goGuide);
           }
       } );

       tourist.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent goTourist = new Intent( mContext, LoginActivity.class );
               startActivity( goTourist );
           }
       } );
    }
}
