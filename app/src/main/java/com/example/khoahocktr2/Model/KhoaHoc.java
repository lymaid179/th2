package com.example.khoahocktr2.Model;

import java.io.Serializable;

public class KhoaHoc implements Serializable {

    private int id;
    private String ten;
    private String hocphi;
    private String  ngaybd;

    private int chuyennganh;
    private Boolean kichhoat;

    public KhoaHoc(int id, String ten, String hocphi, String ngaybd, int chuyennganh, Boolean kichhoat) {
        this.id = id;
        this.ten = ten;
        this.hocphi = hocphi;
        this.ngaybd = ngaybd;
        this.chuyennganh = chuyennganh;
        this.kichhoat = kichhoat;
    }

    public KhoaHoc( String ten, String hocphi, String ngaybd, int chuyennganh, Boolean kichhoat) {
        this.ten = ten;
        this.hocphi = hocphi;
        this.ngaybd = ngaybd;
        this.chuyennganh = chuyennganh;
        this.kichhoat = kichhoat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHocphi() {
        return hocphi;
    }

    public void setHocphi(String hocphi) {
        this.hocphi = hocphi;
    }

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public int getChuyennganh() {
        return chuyennganh;
    }

    public void setChuyennganh(int chuyennganh) {
        this.chuyennganh = chuyennganh;
    }

    public Boolean getKichhoat() {
        return kichhoat;
    }

    public void setKichhoat(Boolean kichhoat) {
        this.kichhoat = kichhoat;
    }
}
