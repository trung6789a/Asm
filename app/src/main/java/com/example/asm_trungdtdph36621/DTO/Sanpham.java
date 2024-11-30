package com.example.asm_trungdtdph36621.DTO;
import com.google.gson.annotations.SerializedName;


public class Sanpham {
    private  String image, name;
    @SerializedName("_id")
     // Đổi tên trường thành _id để phù hợp với tên trường trong JSON
    private String _id;
    private  int price , status;

    public Sanpham() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateAT() {
        return createAT;
    }

    public void setCreateAT(String createAT) {
        this.createAT = createAT;
    }

    public String getUpdateAT() {
        return updateAT;
    }

    public void setUpdateAT(String updateAT) {
        this.updateAT = updateAT;
    }

    public Sanpham(String image, String name, String _id, int price, int status, String createAT, String updateAT) {
        this.image = image;
        this.name = name;
        this._id = _id;
        this.price = price;
        this.status = status;
        this.createAT = createAT;
        this.updateAT = updateAT;
    }

    private String createAT, updateAT;


}
