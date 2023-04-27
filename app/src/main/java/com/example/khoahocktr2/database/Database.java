package com.example.khoahocktr2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.khoahocktr2.Model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="khoahoc.db";
    private final static int DATABSE_VERSION=1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table khoahoc(" +
                "id integer primary key autoincrement," +
                "ten text," +
                "hocphi text," +
                "ngayhoanthanh text," +
                "chuyennganh integer," +
                "kichhoat text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertKH(KhoaHoc khoaHoc) {
        String sql = "INSERT INTO khoahoc(ten,hocphi,ngayhoanthanh,chuyennganh,kichhoat)"+
                "VALUES(?,?,?,?,?)";
        String[] args = { khoaHoc.getTen(), khoaHoc.getHocphi(),  khoaHoc.getNgaybd(),
                String.valueOf( khoaHoc.getChuyennganh()), String.valueOf(khoaHoc.getKichhoat())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }

    public List<KhoaHoc> getKhoaHocs(){
        List<KhoaHoc> list=new ArrayList<>();
        String sql="select * " +
                "from khoahoc";
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.rawQuery(sql,null);
        while(rs!=null && rs.moveToNext()){
            int ma = rs.getInt(0);
            String ten = rs.getString(1);
            String hocphi = rs.getString(2);
            String ngay = rs.getString(3);
            int chuyennganh = rs.getInt(4);
            boolean kichhoat = false;
            if (rs.getString(5).equalsIgnoreCase("true")) {
                kichhoat = true;
            }
            KhoaHoc khoaHoc = new KhoaHoc(ma, ten,hocphi, ngay, chuyennganh, kichhoat);

            list.add(khoaHoc);
        }
        rs.close();
        return list;
    }

    public int updateKH(KhoaHoc khoaHoc){
        ContentValues values=new ContentValues();
        values.put("ten",khoaHoc.getTen());
        values.put("hocphi",khoaHoc.getHocphi());
        values.put("ngayhoanthanh",khoaHoc.getNgaybd());
        values.put("chuyennganh",khoaHoc.getChuyennganh());
        values.put("kichhoat",String.valueOf(khoaHoc.getKichhoat()));
        String where="id=?";
        String[] agrs={Integer.toString(khoaHoc.getId())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("khoahoc",values,where,agrs);
    }

    public int deleteCV(int ma){
        String where="id=?";
        String[] agrs={Integer.toString(ma)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("khoahoc",where,agrs);
    }



}
