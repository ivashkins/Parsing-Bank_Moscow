package com.swa.exchangerate.SecondListActivity;

import android.os.Parcel;
import android.os.Parcelable;

public class BankRateDay implements Parcelable {
  private final Double buy;
  private final Double sell;
  private final String bankData;
  private final String metro;
  private final String reserve;
  private final String time;
  private final String telefon;

    protected BankRateDay(Parcel in) {
        if (in.readByte() == 0) {
            buy = null;
        } else {
            buy = in.readDouble();
        }
        if (in.readByte() == 0) {
            sell = null;
        } else {
            sell = in.readDouble();
        }
        bankData = in.readString();
        metro = in.readString();
        reserve = in.readString();
        time = in.readString();
        telefon = in.readString();
    }

    public static final Creator<BankRateDay> CREATOR = new Creator<BankRateDay>() {
        @Override
        public BankRateDay createFromParcel(Parcel in) {
            return new BankRateDay(in);
        }

        @Override
        public BankRateDay[] newArray(int size) {
            return new BankRateDay[size];
        }
    };

    public String getTelefon() {
        return telefon;
    }

    public Double getBuy() {
        return  buy;
    }

    public Double getSell() {
        return  sell;
    }

    public String getBankData() {
        return bankData;
    }

    public String getMetro() {
        return metro;
    }

    public String getReserve() {
        return reserve;
    }

    public String getTime() {
        return time;
    }

    public BankRateDay(Double buy, Double sell, String bankData, String metro, String reserve, String time,String telefon) {
        this.buy = buy;
        this.sell = sell;
        this.bankData = bankData;
        this.metro = metro;
        this.reserve = reserve;
        this.time = time;
        this.telefon=telefon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (buy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(buy);
        }
        if (sell == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(sell);
        }
        dest.writeString(bankData);
        dest.writeString(metro);
        dest.writeString(reserve);
        dest.writeString(time);
        dest.writeString(telefon);
    }
}
