package com.swa.exchangerate.SecondListActivity.Contract;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.swa.exchangerate.SecondListActivity.BankRateDay;
import com.swa.exchangerate.SecondListActivity.Fragments.BottomSheetFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PresenterFragment extends AppCompatActivity implements ContractMainActivity.iPresenter {
    private ContractMainActivity.iView iView;
    private ContractMainActivity.iRepository iRepository;
    private LatLng p1 = null;
    private BankRateDay bankRateDay;
    private PresenterFragment pf;
    private Context ctx;
    private String number;
    private boolean Kremlin=false;
    String bankName;

    public PresenterFragment(ContractMainActivity.iListnerAdapter iListner,Context context) {
        iRepository=new Repository();
        iView= (ContractMainActivity.iView) context;
        ctx=context;
        pf= this;
    }

    @Override
    public void onButtonClick(int position, ArrayList<BankRateDay> list) {
        bankRateDay=list.get(position);
        bankName=bankRateDay.getBankData();
        number=bankRateDay.getTelefon();
        iRepository.loadBankStreet(bankName ,pf);
    }

    @Override
    public void RepositoryComplited(String s) {
        Log.e("mylog", "repository completed " + s);
        iRepository.destroy();
        if (s.equals("")){LatLng p1=getLocationFromAddress(ctx,"кремль, 1");
        Kremlin=true; }else Kremlin=false;
        LatLng p1=getLocationFromAddress(ctx,s);
        iView.startBottomSheet(p1,number,Kremlin,s,bankName);
    }



    public LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        try {
            address = coder.getFromLocationName(strAddress, 10);
            if (address == null||address.equals("")) {
                return null;
            }
            Log.e("mylog", "adress" + address);
            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return p1;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
