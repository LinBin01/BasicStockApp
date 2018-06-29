package com.example.binlin.stockapp.Ixe_call;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IEXApi {

        @GET("/stock/{symbol}/batch")
        Call<Stock> getStock(@Path("symbol") String symbol);

}
