package com.example.asm_trungdtdph36621.Adapter;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_trungdtdph36621.DTO.Hoadon;
import com.example.asm_trungdtdph36621.DTO.Response;
import com.example.asm_trungdtdph36621.DTO.Sanpham;
import com.example.asm_trungdtdph36621.R;
import com.example.asm_trungdtdph36621.favourite;
import com.example.asm_trungdtdph36621.handles.Item_Hoadon_Handle;
import com.example.asm_trungdtdph36621.handles.Item_Sanpham_Handle;
import com.example.asm_trungdtdph36621.sevices.HttpRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class HoadonAdapter  extends RecyclerView.Adapter<HoadonAdapter.View>  {
     Context context;
    private final ArrayList<Hoadon> list;

    private Item_Hoadon_Handle handle;
    private HttpRequest httpRequest;

    public HoadonAdapter(Context context, ArrayList<Hoadon> list, Item_Hoadon_Handle handle) {
        this.context = context;
        this.list = list;
        this.handle = handle;
    }

    @NonNull
    @Override
    public View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent, false);
        return new View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View holder, int position) {
        httpRequest = new HttpRequest();
        Hoadon sp = list.get(position);
        holder.txtSTT.setText(String.valueOf(position+1));
        holder.txtTen.setText("Tên sản phẩm  : "+sp.getName());
        holder.txtSoLuong.setText("Số lượng : "+String.valueOf(sp.getSoLuong()));
        holder.txtPrice.setText("Giá sản phẩm : "+String.valueOf(sp.getPrice()));
        holder.txtGia.setText("Thành tiền : " +String.valueOf(sp.getTotalPrice())+ "$"); // Đảm bảo giá trị là một chuỗi
        holder.txtTrangThai.setText("Trạng thái đơn hàng : "+sp.getTrangThaiText());



        holder.img_Delete.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắc chắn muốn xóa không?")
                        .setCancelable(false)
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id = list.get(holder.getAdapterPosition()).get_id();  // Lấy ID hóa đơn

                                // Gửi API để xóa
                                httpRequest.CallApi().deleteStudentById(id).enqueue(new Callback<Response<Hoadon>>() {
                                    @Override
                                    public void onResponse(Call<Response<Hoadon>> call, retrofit2.Response<Response<Hoadon>> response) {
                                        if (response.isSuccessful() && response.body() != null) {
                                            if (response.body().getStatus() == 200) {
                                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                                // Xóa mục khỏi danh sách RecyclerView
                                                removeItemById(id);
                                            } else {
                                                Toast.makeText(context, "Xóa thất bại: " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(context, "Lỗi: Không thể xóa", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Response<Hoadon>> call, Throwable t) {
                                        Log.e(TAG, "API Delete Error: " + t.getMessage());
                                        Toast.makeText(context, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });

//
        holder.btnAdd.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                handle.Update(sp.get_id(), sp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class View extends RecyclerView.ViewHolder {
        ImageView imgSp, img_Delete;
        TextView txtTen,txtGia, txtSoLuong, txtTrangThai, txtSTT, txtPrice;

        Button btnAdd, btnUpadate;
        public View(@NonNull android.view.View itemView) {
            super(itemView);
            imgSp = itemView.findViewById(R.id.imgSp);
            txtSTT = itemView.findViewById(R.id.tv_stt);
            txtPrice = itemView.findViewById(R.id.tv_Price);
            txtTen = itemView.findViewById(R.id.tv_tenqua);
            txtSoLuong = itemView.findViewById(R.id.tv_soLuong);
            txtGia = itemView.findViewById(R.id.tv_giaTien);
            txtTrangThai = itemView.findViewById(R.id.tv_TrangThai);
btnAdd = itemView.findViewById(R.id.btnMua);
            img_Delete = itemView.findViewById(R.id.img_delete);

        }
    }
    private void removeItemById(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).get_id().equals(id)) {
                list.remove(i);
                // Xóa hóa đơn khỏi danh sách
                notifyItemRemoved(i); // Cập nhật RecyclerView
                notifyItemRangeChanged(i, list.size());
                break;
            }
        }
    }

}
