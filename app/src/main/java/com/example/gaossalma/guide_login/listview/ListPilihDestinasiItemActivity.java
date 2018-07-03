package com.example.gaossalma.guide_login.listview;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaossalma.guide_login.BuildConfig;
import com.example.gaossalma.guide_login.DetailListDestinasiActivity;
import com.example.gaossalma.guide_login.R;
import com.example.gaossalma.guide_login.adapter.ConstantDestinasi;
import com.example.gaossalma.guide_login.adapter.DestinasiAdapter;
import com.example.gaossalma.guide_login.adapter.PilihDestinasiAdapter;
import com.example.gaossalma.guide_login.adapter.RecyclerItemClickListener;
import com.example.gaossalma.guide_login.apihelper.BaseApiService;
import com.example.gaossalma.guide_login.apihelper.UtilsApi;
import com.example.gaossalma.guide_login.model.destinasi.ResponseDestinasi;
import com.example.gaossalma.guide_login.model.destinasi.SemuaDestinasiItem;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPilihDestinasiItemActivity extends AppCompatActivity {

    private static final String TAG = ListPilihDestinasiItemActivity.class.getSimpleName();
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    @BindView(R.id.rvPilihDestinasi)
    RecyclerView rvDestinasi;
    @BindView(R.id.cari)
    Button btnCari;
    @BindView( R.id.Clatitude )
    TextView tvLatitude;
    @BindView( R.id.Clongitud )
    TextView tvLongitude;
    List<SemuaDestinasiItem> mItems = new ArrayList<>();
    PilihDestinasiAdapter PilihDestinasiAdapter;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private FusedLocationProviderClient mFusedLocationClient;
    protected Location mLastLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_destinasi);
        getSupportActionBar().setTitle("judul");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getApiService();
        PilihDestinasiAdapter = new PilihDestinasiAdapter(this, mItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvDestinasi.setLayoutManager(mLayoutManager);
        getResultListDestinasi();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient( this );

        btnCari.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocation();
                }
        });

    }

    @SuppressWarnings( "MissingPermission" )
    private void getLocation(){
        mFusedLocationClient.getLastLocation().addOnCompleteListener( this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() && task.getResult() !=null){
                    mLastLocation = task.getResult();
                    tvLatitude.setText( String.format( Locale.ENGLISH, "%s: %f",
                            "Latitude",mLastLocation.getLatitude()) );
                    tvLongitude.setText( String.format( Locale.ENGLISH, "%s: %f",
                            "Longitude", mLastLocation.getLongitude()) );
                } else {
                    Log.w( TAG, "getLastLocation:exception", task.getException() );

                }
            }
        } );
    }

    private void showSnackbar(final String text){
        View container = findViewById( R.id.main_cari_container );
        if (container != null){
            Snackbar.make( container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener){
        Snackbar.make( findViewById( android.R.id.content ),getString( mainTextStringId ),
                Snackbar.LENGTH_INDEFINITE).setAction( getString( actionStringId ), listener ).show();
    }

    private boolean chechkPermission(){
        int permissionState = ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION );
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest(){
        ActivityCompat.requestPermissions( ListPilihDestinasiItemActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void requestPermission(){
        boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.ACCESS_COARSE_LOCATION );

        if (shouldProvideRationale){
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

            showSnackbar( R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startLocationPermissionRequest();
                        }
                    } );
        } else {
            Log.i(TAG, "Requesting permission");

            startLocationPermissionRequest();
        }
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions,
                                          @NonNull int[] grantResults){
        Log.i( TAG, "onRequestPermissionResult" );
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE){
            if (grantResults.length<=0){
                Log.i(TAG, "User interaction was Canceled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLocation();
            }
        } else {
            showSnackbar( R.string.permission_denied_explanation, R.string.setting, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent( );
                    intent.setAction( Settings.ACTION_APPLICATION_DETAILS_SETTINGS );
                    Uri uri = Uri.fromParts( "package", BuildConfig.APPLICATION_ID, null );
                    intent.setData( uri );
                    intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity( intent );
                }
            } );
        }
    }

    public void getResultListDestinasi() {
        mApiService.getsemuadestinasi().enqueue(new Callback<ResponseDestinasi>() {
            @Override
            public void onResponse(Call<ResponseDestinasi> call, Response<ResponseDestinasi> response) {
                if (response.isSuccessful()) {
                    final List<SemuaDestinasiItem> semuaDestinasiItemList = response.body().getSemuaDestinasi();
                    rvDestinasi.setAdapter(new PilihDestinasiAdapter(mContext, semuaDestinasiItemList));
                    PilihDestinasiAdapter.notifyDataSetChanged();
//                    initDataDestinasi(semuaDestinasiItemList);
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

//    private void initDataDestinasi(final List<SemuaDestinasiItem> semuaDestinasiItemList) {
//        cbDestinasi.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    Toast.makeText( mContext, "checklist", Toast.LENGTH_SHORT ).show();
//                } else {
//                    Toast.makeText( mContext, "Unchecklist", Toast.LENGTH_SHORT ).show();
//                }
//            }
//        } );
//    }
}
