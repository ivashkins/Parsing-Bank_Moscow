package com.swa.exchangerate.SecondListActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.swa.exchangerate.SecondListActivity.Contract.ContractMainActivity;
import com.swa.exchangerate.SecondListActivity.Contract.PresenterFragment;
import com.swa.exchangerate.SecondListActivity.Fragments.BankRateDayFragment;
import com.swa.exchangerate.SecondListActivity.Fragments.BottomSheetFragment;
import com.swa.exchangerate.R;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ContractMainActivity.iView{
    private PresenterFragment pf;
    private  String siteIntent;
    private boolean buyChoise=false;
    private ArrayList<BankRateDay>list;
    CompositeDisposable disposable;
    private Observable<ArrayList> documentObservable;
    TextView tv;

    public MainActivity() {
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            list=savedInstanceState.getParcelableArrayList("list");
            buyChoise=savedInstanceState.getBoolean("choise");
            BankRateDayFragment.newInstance(buyChoise, list);
            setContentView(R.layout.activity_main);
        }else {
            list = new ArrayList<>();
            getIntentStartActtivity();
            disposable=new CompositeDisposable();
            disposable.add(Observable.fromCallable(this::pars)
            .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(Throwable::printStackTrace)
                    .subscribe(list -> {
                        if(list.isEmpty()) Toast.makeText(this,"Загрузка закончилась ошибкой, проверьте интернет соединение, если это не помогло опишите свою ситуацию и оправьте на почту", Toast.LENGTH_LONG).show();
                        BankRateDayFragment.newInstance(buyChoise, list);
                        setContentView(R.layout.activity_main);
                    }));

            setContentView(R.layout.loading_view);
        }
}

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list",list);
        outState.putBoolean("choise",buyChoise);
        Log.e("mylog" ," save complited");
    }




    public ArrayList pars(){
    ParsingBank PB=new ParsingBank();
    PB.parseBank(siteIntent,list);
    return list;
    }


    public void getIntentStartActtivity(){
        Intent intent=getIntent();
        siteIntent=intent.getStringExtra("webSite");
        String choise=intent.getStringExtra("transaction");
        if(choise.equals("buy"))buyChoise=true;

    }

    @Override
    public void startBottomSheet(LatLng p1, String number, boolean kremlin, String street,String bankData) {
        BottomSheetFragment bottomSheetFragment=BottomSheetFragment.newInstance(p1,number,kremlin,street,bankData);
        bottomSheetFragment.show(getSupportFragmentManager(),"bottomSheet");
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}