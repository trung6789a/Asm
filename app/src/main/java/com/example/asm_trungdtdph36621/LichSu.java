package com.example.asm_trungdtdph36621;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_trungdtdph36621.Adapter.HoadonAdapter;
import com.example.asm_trungdtdph36621.DTO.Hoadon;
import com.example.asm_trungdtdph36621.DTO.Response;
import com.example.asm_trungdtdph36621.handles.Item_Hoadon_Handle;
import com.example.asm_trungdtdph36621.sevices.HttpRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class LichSu extends AppCompatActivity implements Item_Hoadon_Handle {
    private HttpRequest httpRequest;
    private HoadonAdapter studentAdapter;
    private RecyclerView rcvDistributor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lich_su);
        rcvDistributor = findViewById(R.id.rcv_distributors);
        httpRequest = new HttpRequest();
        httpRequest.CallApi().getListHoadon1().enqueue(getListHoadon1);
}

    @Override
    public void Delete(String id) {

    }

    @Override
    public void Update(String id, Hoadon sampham) {

    }
    Callback<Response<ArrayList<Hoadon>>> getListHoadon1 = new Callback<Response<ArrayList<Hoadon>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Hoadon>>> call, retrofit2.Response<Response<ArrayList<Hoadon>>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200) {
                    ArrayList<Hoadon> list = response.body().getData();
                    getData(list);
//                    Toast.makeText(HomeActivity.this, "hihi"+dsFruits, Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Hoadon>>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private void getData(ArrayList<Hoadon> list) {
        studentAdapter = new HoadonAdapter(this, list, this);
        rcvDistributor.setLayoutManager(new LinearLayoutManager(this));
        rcvDistributor.setAdapter(studentAdapter);

    }
}