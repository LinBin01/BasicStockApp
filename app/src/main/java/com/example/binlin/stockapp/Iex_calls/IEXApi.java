package com.example.binlin.stockapp.Iex_calls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IEXApi {

        @GET("stock/{symbol}/batch?types=quote,news,chart&range=1m&last=10")
        Call<Stock> getStock(@Path("symbol") String symbol);

}
