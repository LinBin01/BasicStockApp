package com.example.binlin.stockapp;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.binlin.stockapp.Iex_calls.IEXApi;
import com.example.binlin.stockapp.Iex_calls.Stock;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private IEXApi iexApi;
    private String iexBaseUrl;
    private Bundle bundle;
    private Retrofit iexRetrofit;
    private StockFragment stockFragment;
    public static final String STOCK_KEY = "stock key";

    @BindView(R.id.company_symbol_textView)
    protected TextInputEditText companySymbolEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bundle = new Bundle();
        stockFragment = StockFragment.newInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        iexBaseUrl = "https://api.iextrading.com/1.0/";

        iexApi = getIexRetrofit().create(IEXApi.class);

    }

    private Retrofit getIexRetrofit() {
        if (iexRetrofit == null) {
            iexRetrofit = new Retrofit.Builder().baseUrl(iexBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return iexRetrofit;
    }

    @OnClick(R.id.search_button)
    protected void searchButtonClicked() {
        if (companySymbolEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "You need to enter a company's symbol", Toast.LENGTH_SHORT).show();
        } else {
            getStock(companySymbolEditText.getText().toString());
        }
    }

    private void getStock(String companyName) {
        iexApi.getStock(companyName).enqueue(new Callback<Stock>() {
            @Override
            public void onResponse(Call<Stock> call, Response<Stock> response) {
                if (response.isSuccessful()) {
                    bundle.putParcelable(STOCK_KEY, response.body());
                    transitionToStockFragment();
                } else {
                    Toast.makeText(MainActivity.this, "IEX call made, but unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Stock> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void transitionToStockFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, stockFragment).commit();
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
