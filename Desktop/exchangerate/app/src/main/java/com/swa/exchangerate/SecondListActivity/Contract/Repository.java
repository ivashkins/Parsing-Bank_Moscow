package com.swa.exchangerate.SecondListActivity.Contract;





import android.util.Log;

import com.swa.exchangerate.SecondListActivity.ParsingBank;
import com.swa.exchangerate.SecondListActivity.ParsingStreet;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository implements ContractMainActivity.iRepository ,ContractMainActivity.iListen{
 PresenterFragment pf;
 Observable<String> observable;

    @Override
    public void loadBankStreet(String s,PresenterFragment presenterFragment) {
        observable=Observable.fromCallable(()->new ParsingStreet().parseStreet(s));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e("mylog", "lodbankStrret " + s);
                        pf=presenterFragment;
                        pf.RepositoryComplited(s);
                    }
                });


    }

    @Override
    public void destroy(){
        observable.subscribe().dispose();
    }



}
