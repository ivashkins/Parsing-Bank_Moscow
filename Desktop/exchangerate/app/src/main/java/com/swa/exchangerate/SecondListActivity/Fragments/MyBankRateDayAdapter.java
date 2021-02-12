package com.swa.exchangerate.SecondListActivity.Fragments;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swa.exchangerate.SecondListActivity.BankRateDay;
import com.swa.exchangerate.R;
import com.swa.exchangerate.SecondListActivity.Contract.ContractMainActivity;
import com.swa.exchangerate.SecondListActivity.Contract.PresenterFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MyBankRateDayAdapter extends RecyclerView.Adapter<MyBankRateDayAdapter.ViewHolder> implements ContractMainActivity.iListnerAdapter {
private PresenterFragment pf;




    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        pf=new PresenterFragment(this,recyclerView.getContext());


    }

    private final ArrayList<BankRateDay> list;
    private static boolean choiseBuy;

    public MyBankRateDayAdapter(ArrayList<BankRateDay> list1,boolean buyChoise) {
       list=list1;
        choiseBuy=buyChoise;

    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_bank, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BankRateDay BRD = holder.getBRD(position);
        holder.itemView.setOnClickListener((v -> {
            pf.onButtonClick(position,list);
            Log.e("mylog","a " + BRD.getBankData());}));
        holder.bankData.setText(BRD.getBankData());
        if(choiseBuy)holder.buyOrSell.setText(BRD.getBuy().toString());
        else holder.buyOrSell.setText(BRD.getSell().toString());
        holder.metro.setText(BRD.getMetro());
        holder.reserve.setText(BRD.getReserve());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView bankData;
        public final TextView buyOrSell;
        public final TextView reserve;
        public final TextView metro;

        public ViewHolder(View view) {
            super(view);
            bankData = ((TextView) view.findViewById(R.id.bankData));
            buyOrSell = ((TextView) view.findViewById(R.id.sellOrBuy));
            reserve = ((TextView) view.findViewById(R.id.reserve));
            metro = ((TextView) view.findViewById(R.id.metro));
        }

        private BankRateDay getBRD(int s) {
            return list.get(s);
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "bankData=" + bankData +
                    ", buyOrSell=" + buyOrSell +
                    ", reserve=" + reserve +
                    ", metro=" + metro +
                    '}';
        }
    }
}