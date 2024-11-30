package com.example.asm_trungdtdph36621;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asm_trungdtdph36621.Adapter.sanphamadapter;
import com.example.asm_trungdtdph36621.DTO.Hoadon;
import com.example.asm_trungdtdph36621.DTO.Response;
import com.example.asm_trungdtdph36621.DTO.Sanpham;
import com.example.asm_trungdtdph36621.handles.Item_Hoadon_Handle;
import com.example.asm_trungdtdph36621.handles.Item_Sanpham_Handle;
import com.example.asm_trungdtdph36621.sevices.HttpRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class favourite extends AppCompatActivity  implements Item_Hoadon_Handle{
    ImageView imgProfile;
    TextView txtTen, txtGia, txtSL;
    private HttpRequest httpRequest;

    sanphamadapter sanphamadapter;
    Context context;
    Button btnThem;
    String trangthai = "0";
    private Item_Sanpham_Handle handle;
    private ArrayList<Sanpham> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        imgProfile = findViewById(R.id.igqua);
        txtGia = findViewById(R.id.txtGiaqua);
        txtTen = findViewById(R.id.TenQua);
        txtSL = findViewById(R.id.txtTrongLuong);
        btnThem = findViewById(R.id.btnThem);
        ImageButton igCong = findViewById(R.id.igCong);
        ImageButton igTru = findViewById(R.id.igTru);
        TextView txtSL = findViewById(R.id.txtSL);
        list = new ArrayList<>();
        httpRequest = new HttpRequest();
        httpRequest.CallApi().getListStudent().enqueue(getListsanpham);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _SL = txtSL.getText().toString().trim();
                String _Gia = txtGia.getText().toString().trim();
                String _Ten = txtTen.getText().toString().trim();
                String _trangthai = trangthai.toString().trim();

                Hoadon student = new Hoadon();
                student.setName(_Ten);
                student.setPrice(Integer.parseInt(_Gia));
                student.setSoLuong(Integer.parseInt(_SL));
                student.setTrangthai(_trangthai);
                httpRequest.CallApi().addHoadon(student).enqueue(responseDistributorAPI);

                Toast.makeText(favourite.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(favourite.this, Giohang.class);
                startActivity(intent);
            }


        });


        igCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(txtSL.getText().toString());
                txtSL.setText(String.valueOf(currentValue + 1));
            }
        });


        igTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(txtSL.getText().toString());
                if (currentValue > 0) {
                    txtSL.setText(String.valueOf(currentValue - 1));
                }
            }
        });

    }

    Callback<Response<ArrayList<Sanpham>>> getListsanpham = new Callback<Response<ArrayList<Sanpham>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Sanpham>>> call, retrofit2.Response<Response<ArrayList<Sanpham>>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    ArrayList<Sanpham> list = response.body().getData();
                    getData(list);

                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Sanpham>>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private void getData(ArrayList<Sanpham> list) {
        if (list != null && list.size() > 0) {
            Sanpham sanpham = list.get(0);
            txtTen.setText(sanpham.getName());
            txtGia.setText(String.valueOf(sanpham.getPrice()));
            Glide.with(favourite.this) //
                    .load(sanpham.getImage())
                    .into(imgProfile);
        }

    }
    Callback<Response<Hoadon>> responseDistributorAPI = new Callback<Response<Hoadon>>() {
        @Override
        public void onResponse(Call<Response<Hoadon>> call, retrofit2.Response<Response<Hoadon>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    httpRequest.CallApi().getListStudent().enqueue(getListsanpham);
                }
            }
        }

        @Override
        public void onFailure(Call<Response<Hoadon>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    @Override
    public void Delete(String id) {
        httpRequest.CallApi().deleteStudentById(id).enqueue(responseDistributorAPI);
    }

    @Override
    public void Update(String id, Hoadon sampham) {

    }


}