package com.example.asm_trungdtdph36621;



import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.asm_trungdtdph36621.Adapter.sanphamadapter;
import com.example.asm_trungdtdph36621.DTO.Response;
import com.example.asm_trungdtdph36621.DTO.Sanpham;
import com.example.asm_trungdtdph36621.handles.Item_Sanpham_Handle;
import com.example.asm_trungdtdph36621.sevices.HttpRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements Item_Sanpham_Handle  {
    ImageView imgProfile;
    private RecyclerView rcySanPham;
    private HttpRequest httpRequest;
    Button btnAdd;
    sanphamadapter sanphamadapter;
    BottomNavigationView bottom_navigation;
    private Item_Sanpham_Handle handle;
    private ArrayList<Sanpham> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgProfile = findViewById(R.id.imgProfile);
        rcySanPham = findViewById(R.id.rcySanPham);
        bottom_navigation = findViewById(R.id.bottom_navigation);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rcySanPham.setLayoutManager(gridLayoutManager);

        list = new ArrayList<>();
        httpRequest = new HttpRequest();
        httpRequest.CallApi().getListStudent().enqueue(getListsanpham);
//        rcySanPham.setAdapter(adapter);


        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    return true;
                } else if (item.getItemId()==R.id.navigation_cart) {
                    startActivity(new Intent(getApplicationContext(), Giohang.class));

                    return true;
                }
                else if (item.getItemId()==R.id.navigation_menu) {
                    startActivity(new Intent(getApplicationContext(), LichSu.class));

                    return true;
                }
                else if (item.getItemId()==R.id.navigation_love) {
                    startActivity(new Intent(getApplicationContext(), favourite.class));
                    return true;
                }

                return false;

            }
        });
    }

    @Override
    public void Delete(String id) {

    }

    @Override
    public void Update(String id, Sanpham sampham) {

    }


    Callback<Response<ArrayList<Sanpham>>> getListsanpham = new Callback<Response<ArrayList<Sanpham>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Sanpham>>> call, retrofit2.Response<Response<ArrayList<Sanpham>>> response) {
            if(response.isSuccessful()){
                if (response.body().getStatus() == 200) {
                    ArrayList<Sanpham> list = response.body().getData();
                    getData(list);
//                    Toast.makeText(HomeActivity.this, "hihi"+dsFruits, Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Sanpham>>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private void getData(ArrayList<Sanpham> list) {
        sanphamadapter = new sanphamadapter(this, list, this);
        rcySanPham.setLayoutManager(new GridLayoutManager(this, 2));
        rcySanPham.setAdapter(sanphamadapter);

    }
}
