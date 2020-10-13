package com.nabil.myapplication;

import com.nabil.myapplication.model.Prayer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    String BASE_URL="https://api.aladhan.com/v1/";
    @GET("/timingsByAddress?address=&method=&school=")
    Call<Prayer> getPrayer(@Query("address") String address, @Query("method") int method, @Query("school") int school);
}
