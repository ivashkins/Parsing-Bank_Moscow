package com.swa.exchangerate.SecondListActivity.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.swa.exchangerate.SecondListActivity.BankRateDay;
import com.swa.exchangerate.SecondListActivity.MyCompator;
import com.swa.exchangerate.R;


import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Action;

public class BankRateDayFragment extends Fragment implements @NonNull Action {

    private static ArrayList<BankRateDay> list1;
    private static boolean choiseBuy;
    private Context context;


    public static BankRateDayFragment newInstance(boolean buyChoise, ArrayList<BankRateDay> list) {
        Collections.sort(list,new MyCompator(buyChoise));
        list1=list;
        choiseBuy=buyChoise;
        return new BankRateDayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frgament_list_bank, container, false);
        if (view instanceof RecyclerView) {
            context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter ada=new MyBankRateDayAdapter(list1, choiseBuy);
        recyclerView.setAdapter(ada);

        ada.notifyDataSetChanged(); }
        return view;
    }

    @Override
    public void run() throws Throwable {
        Toast.makeText(context,"Data loading error",Toast.LENGTH_SHORT).show();
    }




}