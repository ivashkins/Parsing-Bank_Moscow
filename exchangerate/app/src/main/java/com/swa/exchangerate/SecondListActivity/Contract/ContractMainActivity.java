package com.swa.exchangerate.SecondListActivity.Contract;


import com.google.android.gms.maps.model.LatLng;
import com.swa.exchangerate.SecondListActivity.BankRateDay;

import java.util.ArrayList;

public interface ContractMainActivity {
    interface iView {
        void startBottomSheet(LatLng p1, String number, boolean kremlin, String s,String bankData);
    }

    interface iPresenter {
        void onButtonClick(int position, ArrayList<BankRateDay> list);
        void RepositoryComplited(String s);
        void onDestroy();
    }

    interface iRepository {
        void loadBankStreet(String s,PresenterFragment presenterFragment);
        void destroy();
    }

    interface iListnerAdapter {
    }
    interface iListen{}
}

