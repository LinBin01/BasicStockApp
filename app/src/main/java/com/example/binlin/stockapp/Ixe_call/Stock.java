package com.example.binlin.stockapp.Ixe_call;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stock implements Parcelable {
    @SerializedName("quote")
    private IncStock quote;
    @SerializedName("chart")
    private List<chartStock> chart;

    protected Stock(Parcel in) {
    }

    public static final Creator<Stock> CREATOR = new Creator<Stock>() {
        @Override
        public Stock createFromParcel(Parcel in) {
            return new Stock(in);
        }

        @Override
        public Stock[] newArray(int size) {
            return new Stock[size];
        }
    };

    public IncStock getQuote() {
        return quote;
    }

    public List<chartStock> getChart() {
        return chart;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public class IncStock {
        @SerializedName("companyName")
        private String companyName;
        @SerializedName("latestPrice")
        private String currentPrice;
        @SerializedName("change")
        private double currentChange;

        public String getCompanyName() {
            return companyName;
        }

        public String getCurrentPrice() {
            return currentPrice;
        }

        public double getCurrentChange() {
            return currentChange;
        }
    }

    public class chartStock {
        @SerializedName("date")
        private String date;
        @SerializedName("changePercent")
        private double changePercent;
        @SerializedName("vwap")
        private double value;

        public String getDate() {
            return date;
        }

        public double getChangePercent() {
            return changePercent;
        }

        public double getValue() {
            return value;
        }
    }

}
