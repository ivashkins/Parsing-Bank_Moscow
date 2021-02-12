package com.swa.exchangerate.StartChoiceActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.swa.exchangerate.SecondListActivity.MainActivity;
import com.swa.exchangerate.R;

public class PresenterStartActivity implements ContractStartActivity.iPresenter {
    private ContractStartActivity.iView iView;
    private String message;

    public PresenterStartActivity(ContractStartActivity.iView iView) {
        this.iView = iView;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onButtonClick(int id, String selected, String[] spinnerString) {
        Intent intent=new Intent((Context) iView, MainActivity.class);
        switch (id){
            case R.id.button:
                if(selected.equals(spinnerString[0])){
                    intent.putExtra("webSite","https://cash.rbc.ru/?currency=3&city=1&diapason=1");}
                else if(selected.equals(spinnerString[1])) {
                    intent.putExtra("webSite","https://cash.rbc.ru/?currency=3&city=1&diapason=2");}
                else intent.putExtra("webSite","https://cash.rbc.ru/?currency=3&city=1&diapason=3");
                intent.putExtra("transaction", "buy");
                break;
            case R.id.button2:
                if(selected.equals(spinnerString[0])){
                    intent.putExtra("webSite","https://cash.rbc.ru/?currency=3&city=1&diapason=1");}
                else if(selected.equals(spinnerString[1])) {
                    intent.putExtra("webSite","https://cash.rbc.ru/?currency=3&city=1&diapason=2");}
                else intent.putExtra("webSite","https://cash.rbc.ru/?currency=3&city=1&diapason=3");
                intent.putExtra("transaction", "sell");
                break;
            case R.id.button3:
                if(selected.equals(spinnerString[0])){
                    intent.putExtra("webSite","https://cash.rbc.ru/?currency=2&city=1&diapason=1");}
                else if(selected.equals(spinnerString[1])) {
                    intent.putExtra("webSite","https://cash.rbc.ru/?currency=2&city=1&diapason=2");}
                else intent.putExtra("webSite","https://cash.rbc.ru/?currency=2&city=1&diapason=3");
                intent.putExtra("transaction", "buy");
                break;
            case R.id.button4:
                if(selected.equals(spinnerString[0])){
                    intent.putExtra("webSite","https://cash.rbc.ru/?currency=2&city=1&diapason=1");}
                else if(selected.equals(spinnerString[1])) {
                    intent.putExtra("webSite","https://cash.rbc.ru/?currency=2&city=1&diapason=2");}
                else intent.putExtra("webSite","https://cash.rbc.ru/?currency=2&city=1&diapason=3");
                intent.putExtra("transaction", "sell");
                break;
        }
        iView.startIntent(intent);
    }



    @Override
    public void onDestroy() {

    }
}
