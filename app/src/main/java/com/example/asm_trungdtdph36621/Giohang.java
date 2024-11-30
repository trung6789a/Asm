package com.example.asm_trungdtdph36621;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
import com.example.asm_trungdtdph36621.DTO.Sanpham;
import com.example.asm_trungdtdph36621.handles.Item_Hoadon_Handle;
import com.example.asm_trungdtdph36621.sevices.HttpRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Giohang extends AppCompatActivity implements Item_Hoadon_Handle {
    private HttpRequest httpRequest;
    private HoadonAdapter hoadonAdapter;
    private RecyclerView rcvDistributor;
    private  ArrayList<Hoadon> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        rcvDistributor = findViewById(R.id.rcv_distributor);
        httpRequest = new HttpRequest();

       httpRequest.CallApi().getListHoadon().enqueue(getListHoadon);
        }

    @Override
    public void Delete(String id) {

    }

    @Override
    public void Update(String id, Hoadon hoadon) {
        String trangthaiMoi = "1";
        id = hoadon.get_id();
        Hoadon hoadon1 = new Hoadon();
        hoadon1.set_id(id);
        hoadon1.setTrangthai(trangthaiMoi);
        httpRequest.CallApi().updateHoadon(id,hoadon1).enqueue(UpdateDistributor);


        // Gửi API cập nhật

    }
    // Callback để cập nhật nhà phân phối
    Callback<Response<Hoadon>> UpdateHoadon = new Callback<Response<Hoadon>>() {
        @Override
        public void onResponse(Call<Response<Hoadon>> call, retrofit2.Response<Response<Hoadon>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {

                    Toast.makeText(getApplicationContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                    // Bạn có thể gọi lại onResume() hoặc lấy lại danh sách nhà phân phối sau khi update
                    onResume(); // Hoặc gọi lại API để lấy lại dữ liệu
                }
            } else {
                // Xử lý khi cập nhật thất bại
                Log.e(TAG, "Cập nhật thất bại");
            }
        }

        @Override
        public void onFailure(Call<Response<Hoadon>> call, Throwable t) {
            // Xử lý khi có lỗi kết nối hoặc lỗi API
            Log.e(TAG, "API Update Error: " + t.getMessage());
        }
    };
    Callback<Response<Hoadon>> UpdateDistributor = new Callback<Response<Hoadon>>() {
        @Override
        public void onResponse(Call<Response<Hoadon>> call, retrofit2.Response<Response<Hoadon>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus()==200){
                    Toast.makeText(getApplicationContext(),"Update thanh cong",Toast.LENGTH_SHORT).show();
                    onResume();

                }
            }
        }

        @Override
        public void onFailure(Call<Response<Hoadon>> call, Throwable t) {
            Log.i(TAG, "onFailure: Error"+t.getMessage());
        }
    };


    Callback<Response<ArrayList<Hoadon>>> getListHoadon = new Callback<Response<ArrayList<Hoadon>>>() {
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

            hoadonAdapter = new HoadonAdapter(this, list, this);
            rcvDistributor.setLayoutManager(new LinearLayoutManager(this));
            rcvDistributor.setAdapter(hoadonAdapter);


    }
    @Override
    protected void onResume() {
        super.onResume();
        // Gọi lại API để lấy danh sách hóa đơn mới nhất
        httpRequest.CallApi().getListHoadon().enqueue(getListHoadon);
    }
    public void updateData(ArrayList<Hoadon> list) {
        this.list = list;
        hoadonAdapter.notifyDataSetChanged();  // Cập nhật lại dữ liệu cho RecyclerView
    }


}
