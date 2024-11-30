package com.example.asm_trungdtdph36621.DTO;

import com.google.gson.annotations.SerializedName;

public class Hoadon {
    @SerializedName("_id")
    String _id;
    String name, trangthai;
    int price, soluong;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoLuong() {
        return soluong;
    }

    public void setSoLuong(int soLuong) {
        this.soluong = soLuong;
    }

    public Hoadon(String _id, String name, String trangthai, int price, int soluong) {
        this._id = _id;
        this.name = name;
        this.trangthai = trangthai;
        this.price = price;
        this.soluong = soluong;
    }
    public String getTrangThaiText() {
        if (trangthai.equals("0")) {
            return "Chưa đặt";
        } else {
            return "Đã đặt";
        }
    }

    public Hoadon() {
    }
    public int getTotalPrice() {
        return price * soluong;
    }
}
