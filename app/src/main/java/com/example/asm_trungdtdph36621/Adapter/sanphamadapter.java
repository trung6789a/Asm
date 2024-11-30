package com.example.asm_trungdtdph36621.Adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.asm_trungdtdph36621.DTO.Sanpham;
import com.example.asm_trungdtdph36621.R;
import com.example.asm_trungdtdph36621.favourite;
import com.example.asm_trungdtdph36621.handles.Item_Sanpham_Handle;
import com.example.asm_trungdtdph36621.sevices.HttpRequest;

import java.util.ArrayList;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class sanphamadapter extends RecyclerView.Adapter<sanphamadapter.View> {
    private final Context context;
    private final ArrayList<Sanpham> list;

    private Item_Sanpham_Handle handle;
    private HttpRequest httpRequest;

    public sanphamadapter(Context context, ArrayList<Sanpham> list, Item_Sanpham_Handle handle) {
        this.context = context;
        this.list = list;
        this.handle = handle;
    }

    @NonNull
    @Override
    public View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyview, parent, false);
        return new View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View holder, int position) {
        httpRequest = new HttpRequest();
        Sanpham sp = list.get(position);

        holder.txtTen.setText(sp.getName());
        holder.txtGia.setText(String.valueOf(sp.getPrice())+ "$"); // Đảm bảo giá trị là một chuỗi

        Glide.with(context)
                .load(sp.getImage())
//                .placeholder(R.drawable.cam)
                .into(holder.imgSp);
        holder.btnAdd.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(context, favourite.class);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new android.view.View.OnLongClickListener() {
            @Override
            public boolean onLongClick(android.view.View v) {
                handle.Update(sp.get_id(), sp);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class View extends RecyclerView.ViewHolder {
        ImageView imgSp;
        TextView txtTen,txtGia, txtGam;

        Button btnAdd;
        public View(@NonNull android.view.View itemView) {
            super(itemView);
            imgSp = itemView.findViewById(R.id.imgSp);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtGia = itemView.findViewById(R.id.txtsoLuong);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }


    }


