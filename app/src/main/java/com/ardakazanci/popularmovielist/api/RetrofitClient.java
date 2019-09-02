package com.ardakazanci.popularmovielist.api;

import com.ardakazanci.popularmovielist.common.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Tüm uygulama boyunca tek bir retrofit örneği üzerinden işlem yapabilmemiz için
 * retrofit nesnesi örneği oluşturuyoruz.
 */
public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


}
