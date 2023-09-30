package com.example.foodorderbasic.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserModel {

    @PrimaryKey
    @NonNull
    private  String Uid ;
    private String fullname ;
    private String sdt ;
    private String gioitinh ;
    private String ngaysinh ;
    private String email ;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel(String uid, String fullname, String sdt, String gioitinh, String ngaysinh, String email) {
        Uid = uid;
        this.fullname = fullname;
        this.sdt = sdt;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.email = email;
    }

    public UserModel() {
    }
}
