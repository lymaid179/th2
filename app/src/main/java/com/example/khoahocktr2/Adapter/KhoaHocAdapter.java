package com.example.khoahocktr2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khoahocktr2.Model.KhoaHoc;
import com.example.khoahocktr2.R;

import java.util.List;

public class KhoaHocAdapter extends ArrayAdapter {
    Context context;
    List<KhoaHoc> khoaHocs;
    private KhoaHocItemListener congViecItemListener;

    public KhoaHocAdapter(@NonNull Context context, @NonNull List khoaHocs) {
        super(context, R.layout.item, khoaHocs);

        this.context = context;
        this.khoaHocs = khoaHocs;
    }

    public void setKhoaHocsItemListener(KhoaHocItemListener congViecItemListener) {
        this.congViecItemListener = congViecItemListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, null, true);
        TextView txtTen = v.findViewById(R.id.textViewTen);
        TextView txtChuyennganh = v.findViewById(R.id.textViewCN);
        TextView txtNgaybd = v.findViewById(R.id.textViewNgay);
        TextView txtKichhoat = v.findViewById(R.id.textViewTinhTrang);
        TextView txtHocphi = v.findViewById(R.id.textViewHocphi);
        KhoaHoc khoaHoc = khoaHocs.get(position);
        txtTen.setText(khoaHoc.getTen());
        switch (khoaHoc.getChuyennganh()) {
            case 0:
                txtChuyennganh.setText("CNTT");
                break;
            case 1:
                txtChuyennganh.setText("Kế toán");
                break;
            case 2:
                txtChuyennganh.setText("Thiết kế đồ họa");
                break;
            case 3:
                txtChuyennganh.setText("Quản trị kinh doanh");
                break;

        }
        txtNgaybd.setText(khoaHoc.getNgaybd());
        txtKichhoat.setText(""+khoaHoc.getKichhoat());
        txtHocphi.setText(khoaHoc.getHocphi());

        if (khoaHoc.getKichhoat()) {
            txtKichhoat.setText("Đã kích hoạt");
        } else {
            txtKichhoat.setText("Chưa kích hoạt");
        }




        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                congViecItemListener.onItemClicked(v, position);
            }
        });

        return v;
    }
    public interface KhoaHocItemListener {
        void onItemClicked(View v, int position);
    }
}
