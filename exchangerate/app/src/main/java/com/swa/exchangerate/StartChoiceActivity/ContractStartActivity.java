package com.swa.exchangerate.StartChoiceActivity;

import android.content.Intent;

public interface ContractStartActivity {
     interface iView{
        void startIntent(Intent i);
    }
    interface iPresenter{
         void onButtonClick(int id, String selected, String[] spinnerString);
         void onDestroy();
    }
}
