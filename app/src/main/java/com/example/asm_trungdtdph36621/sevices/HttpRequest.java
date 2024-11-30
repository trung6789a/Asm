package com.example.asm_trungdtdph36621.sevices;

import static com.example.asm_trungdtdph36621.sevices.ApiServices.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {
    private ApiServices requestInterface;

    public HttpRequest() {
        requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServices.class);
    }

    public ApiServices CallApi() {
        return requestInterface;
    }
}
