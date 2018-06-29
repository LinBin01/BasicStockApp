package com.example.binlin.stockapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.binlin.stockapp.Ixe_call.IEXApi;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private IEXApi iexApi;
    private String iexBaseUrl;
    private Bundle bundle;
    private Retrofit iexRetrofit;
    private StockFragment stockFragment;
    @BindView(R.id.company_symbol_textView)
    protected TextInputEditText companySymbolEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bundle = new Bundle();

    }

    @Override
    protected void onStart() {
        super.onStart();
        iexBaseUrl = "https://api.iextrading.com/1.0";

        iexApi = getIexRetrofit().create(IEXApi.class);

    }

    private Retrofit getIexRetrofit() {
        if (iexRetrofit == null) {
            iexRetrofit = new Retrofit.Builder().baseUrl(iexBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return iexRetrofit;
    }

    @OnClick(R.id.search_button)
    protected void searchButtonClicked(){
        if (companySymbolEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "You need to enter a company's symbol", Toast.LENGTH_SHORT).show();
        } else {
            getStock(companySymbolEditText.getText().toString());
        }
    }

    private void getStock(String companyName) {

    }


    @Override
    public void onBackPressed() {
        if (stockFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(stockFragment).commit();
        } else {
            super.onBackPressed();
        }
    }
}
