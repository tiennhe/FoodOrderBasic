package com.example.foodorderbasic.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bill_list_productts")
public class BillModel {

    @PrimaryKey(autoGenerate = true)
    private int idbill;
    private String phuongthucthanhtoan ;
    private String hoten ;
    private String sodienthoai ;
    private String adressgiaohang ;
    private double tongtien;
    private int status_Bill;
    private String listsanpham ;

    private int statusbill_admin;

    public BillModel(String phuongthucthanhtoan, String hoten, String sodienthoai, String adressgiaohang, double tongtien, int status_Bill, String listsanpham, int statusbill_admin, String date) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.adressgiaohang = adressgiaohang;
        this.tongtien = tongtien;
        this.status_Bill = status_Bill;
        this.listsanpham = listsanpham;
        this.statusbill_admin = statusbill_admin;
        this.date = date;
    }

    public BillModel(int idbill, String phuongthucthanhtoan, String hoten, String sodienthoai, String adressgiaohang, double tongtien, int status_Bill, String listsanpham, int statusbill_admin, String date) {
        this.idbill = idbill;
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.adressgiaohang = adressgiaohang;
        this.tongtien = tongtien;
        this.status_Bill = status_Bill;
        this.listsanpham = listsanpham;
        this.statusbill_admin = statusbill_admin;
        this.date = date;
    }

    public String getListsanpham() {
        return listsanpham;
    }

    public void setListsanpham(String listsanpham) {
        this.listsanpham = listsanpham;
    }

    private String date;
    public BillModel(int idbill, String phuongthucthanhtoan, String hoten, String sodienthoai, String adressgiaohang, double tongtien, int status_Bill, int statusbill_admin) {
        this.idbill = idbill;
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.adressgiaohang = adressgiaohang;
        this.tongtien = tongtien;
        this.status_Bill = status_Bill;
        this.statusbill_admin = statusbill_admin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BillModel(String phuongthucthanhtoan, String hoten, String sodienthoai, String adressgiaohang, double tongtien, int status_Bill, int statusbill_admin, String date) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.adressgiaohang = adressgiaohang;
        this.tongtien = tongtien;
        this.status_Bill = status_Bill;
        this.statusbill_admin = statusbill_admin;
        this.date = date;
    }

    public BillModel(String phuongthucthanhtoan, String hoten, String sodienthoai, String adressgiaohang, double tongtien, int status_Bill, int statusbill_admin) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.adressgiaohang = adressgiaohang;
        this.tongtien = tongtien;
        this.status_Bill = status_Bill;
        this.statusbill_admin = statusbill_admin;
    }

    public int getIdbill() {
        return idbill;
    }

    public void setIdbill(int idbill) {
        this.idbill = idbill;
    }

    public String getPhuongthucthanhtoan() {
        return phuongthucthanhtoan;
    }

    public void setPhuongthucthanhtoan(String phuongthucthanhtoan) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getAdressgiaohang() {
        return adressgiaohang;
    }

    public void setAdressgiaohang(String adressgiaohang) {
        this.adressgiaohang = adressgiaohang;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public int getStatus_Bill() {
        return status_Bill;
    }

    public void setStatus_Bill(int status_Bill) {
        this.status_Bill = status_Bill;
    }

    public int getStatusbill_admin() {
        return statusbill_admin;
    }

    public void setStatusbill_admin(int statusbill_admin) {
        this.statusbill_admin = statusbill_admin;
    }

    public BillModel() {
    }
}
