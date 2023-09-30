package com.example.foodorderbasic.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "food_orders_basic")
public class FoodModel implements Serializable {
     @PrimaryKey(autoGenerate = true)
    private int id;
    private int quantity ;
    private String name ;
    private double gia ;
    private  double discount ;
    private String description ;
    private String Image ;

    private int  idbill;

    private String Uid ;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public int getIdbill() {
        return idbill;
    }

    public void setIdbill(int idbill) {
        this.idbill = idbill;
    }

    private double giadiscount ;

    public double getGiadiscount() {
        return giadiscount;
    }

    public void setGiadiscount(double giadiscount) {
        this.giadiscount = giadiscount;
    }

    private double giacartproductitem;

    public double getGiacartproductitem() {
        return giacartproductitem;
    }

    public void setGiacartproductitem(double giacartproductitem) {
        this.giacartproductitem = giacartproductitem;
    }


    public FoodModel(int quantity, String name, String image, double giacartproductitem) {
        this.quantity = quantity;
        this.name = name;
        Image = image;
        this.giacartproductitem = giacartproductitem;
    }

    public FoodModel(int quantity, String name, double gia, String image, double giacartproductitem) {
        this.quantity = quantity;
        this.name = name;
        this.gia = gia;
        Image = image;
        this.giacartproductitem = giacartproductitem;
    }

    public FoodModel(int quantity, String name, double gia, String image, double giadiscount, double giacartproductitem) {
        this.quantity = quantity;
        this.name = name;
        this.gia = gia;
        Image = image;
        this.giadiscount = giadiscount;
        this.giacartproductitem = giacartproductitem;
    }

    public FoodModel(int quantity, String name, double gia, String image, String uid, double giadiscount, double giacartproductitem) {
        this.quantity = quantity;
        this.name = name;
        this.gia = gia;
        Image = image;
        Uid = uid;
        this.giadiscount = giadiscount;
        this.giacartproductitem = giacartproductitem;
    }

    public FoodModel(int id, int quantity, String name, double gia, double discount, String description, String image) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.gia = gia;
        this.discount = discount;
        this.description = description;
        Image = image;
    }

    public FoodModel(int quantity, String name, double gia, String image) {
        this.quantity = quantity;
        this.name = name;
        this.gia = gia;
        Image = image;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FoodModel(int id, String name, double gia, double discount, String description, String image) {
        this.id = id;
        this.name = name;
        this.gia = gia;
        this.discount = discount;
        this.description = description;
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public FoodModel() {
    }

    @Override
    public String toString() {
        return "-" +"" + name + "(" +
                "" + quantity*giadiscount +" VNĐ)"+
                "-Số lượng:"+quantity+"\n";
    }
}
