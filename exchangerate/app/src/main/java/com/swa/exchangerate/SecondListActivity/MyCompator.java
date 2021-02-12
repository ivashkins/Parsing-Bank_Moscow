package com.swa.exchangerate.SecondListActivity;

import com.swa.exchangerate.SecondListActivity.BankRateDay;

import java.util.Comparator;

public class MyCompator implements Comparator<BankRateDay> {
    private boolean choiseBuy;

    public MyCompator(boolean choiseBuy) {
        this.choiseBuy = choiseBuy;
    }


    @Override
    public int compare(BankRateDay o1, BankRateDay o2) {
        if(choiseBuy){
            return o1.getBuy().compareTo(o2.getBuy());
        }
        else {
            return o2.getSell().compareTo(o1.getSell());
        }
    }
}
