package com.swa.exchangerate.SecondListActivity.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.swa.exchangerate.R;
import com.swa.exchangerate.SecondListActivity.MainActivity;

import java.util.concurrent.ExecutionException;

public class BottomSheetFragment extends BottomSheetDialogFragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private LatLng latLng;





    public static BottomSheetFragment newInstance(LatLng p1, String number, boolean kremlin, String street,String bankName){
        BottomSheetFragment fragment=new BottomSheetFragment();
        double lat=p1.latitude;
        double lng=p1.longitude;
        Bundle args = new Bundle();
        args.putDouble("lat",lat);
        args.putDouble("lng",lng);
        args.putBoolean("kreml",kremlin);
        args.putString("pos",number);
        args.putString("street",street);
        args.putString("bankName",bankName);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("ResourceType") View v=inflater.inflate(R.layout.fragment_bottom_sheet_item, container, false);
       ((TextView)v.findViewById(R.id.tv2)).setText(String.valueOf(getArguments().getString("street")));
        ((Button)v.findViewById(R.id.button5)).setOnClickListener((View.OnClickListener) v1 -> {
            try{
            Uri u=Uri.parse("tel:" + getArguments().getString("pos"));
            Context ctx= v1.getContext();
            Intent intent1=new Intent(Intent.ACTION_DIAL,u);
            ctx.startActivity(intent1);
        }
            catch (Exception e){Log.e("mylog","exeption with dial " +e);
            Toast.makeText(v1.getContext(),"Невозможно позвонить",Toast.LENGTH_SHORT).show();}});

        ((Button)v.findViewById(R.id.button6)).setOnClickListener((View.OnClickListener)v1->{
           try {
              // Uri u=Uri.parse("geo:"+ getArguments().getDouble("lat")+","+getArguments().getDouble("lng"));
               String bankname=getArguments().getString("street");
               bankname=bankname.replaceAll(",","%20");
               bankname=bankname.replaceAll(" ","+");
               Uri u =Uri.parse("geo:0,0?q="+bankname);
               Log.e("mylog", "bnkname " + bankname);
               Intent i=new Intent(Intent.ACTION_VIEW,u);
               Intent ci=Intent.createChooser(i,"выберите карту");
               startActivity(ci);
           }catch (Exception e){
               Log.e("mylog", " exep with view " + e);
               Toast.makeText(v.getContext(),e.toString(),Toast.LENGTH_SHORT).show();
           }
        });
        MapView mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        if (getArguments().getBoolean("kreml")) {Toast.makeText(v.getContext(),"Извините у нас пока нет этого банка на картах",Toast.LENGTH_LONG).show();}
        return v;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        assert getArguments() != null;
        latLng=new LatLng(getArguments().getDouble("lat"),getArguments().getDouble("lng"));
        this.googleMap=googleMap;
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("hey you"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.setMinZoomPreference(10);





    }


}
