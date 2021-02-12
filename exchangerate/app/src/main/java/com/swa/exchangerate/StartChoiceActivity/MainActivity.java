package com.swa.exchangerate.StartChoiceActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.swa.exchangerate.R;

public class MainActivity extends AppCompatActivity implements OnClickListener, ContractStartActivity.iView {
    private Button bb, bb1, bs, bs1;
    private Spinner spinner;
    private String[] spinnerString;
    PresenterStartActivity presenter;
    private View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_choise);
        presenter = new PresenterStartActivity((ContractStartActivity.iView) this);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        spinner = findViewById(R.id.spinner);
        spinnerString = getResources().getStringArray(R.array.spinerArray);
        ArrayAdapter<String> spinerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerString);
        spinner.setAdapter(spinerAdapter);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        String selected = spinner.getSelectedItem().toString();
        presenter.onButtonClick(v.getId(), selected, spinnerString);
    }

    @Override
    public void startIntent(Intent i) {
        startActivity(i);
    }
}