package com.example.asm_trungdtdph36621.sevices;

import com.example.asm_trungdtdph36621.DTO.Hoadon;
import com.example.asm_trungdtdph36621.DTO.Response;
import com.example.asm_trungdtdph36621.DTO.Sanpham;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiServices {

    public static String BASE_URL = "http://10.0.2.2:3000/api/";
//public static String BASE_URL = "http://192.168.1.194:3000/api/";


    @GET("get-list-sanpham")
    Call<Response<ArrayList<Sanpham>>> getListStudent();

    @GET("get-list-hoadon")
    Call<Response<ArrayList<Hoadon>>> getListHoadon();
    @GET("get-list-hoadon1")
    Call<Response<ArrayList<Hoadon>>> getListHoadon1();
    @POST("add-hoadon")
    Call<Response<Hoadon>> addHoadon(@Body Hoadon hoadon);
    @DELETE("delete-hoadon-by-id/{id}")
    Call<Response<Hoadon>> deleteStudentById(@Path("id") String id);

    @PUT("update-hoadon/{id}")
    Call<Response<Hoadon>> updateHoadon(@Path("id") String id, @Body Hoadon hoadon);
}
