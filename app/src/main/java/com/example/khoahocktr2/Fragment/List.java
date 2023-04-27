package com.example.khoahocktr2.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.khoahocktr2.Adapter.KhoaHocAdapter;
import com.example.khoahocktr2.Form_Activity;
import com.example.khoahocktr2.Model.KhoaHoc;
import com.example.khoahocktr2.R;
import com.example.khoahocktr2.database.Database;


public class List extends Fragment  implements  KhoaHocAdapter.KhoaHocItemListener {
    ListView listView;
    Database db;
    KhoaHocAdapter adapter;
    java.util.List<KhoaHoc> khoaHocs;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listView = v.findViewById(R.id.listView);
        db = new Database(getContext());

        khoaHocs = db.getKhoaHocs();
        adapter = new KhoaHocAdapter(getContext(),khoaHocs);
        adapter.setKhoaHocsItemListener(this);
        listView.setAdapter(adapter);

        return v;
    }

   // @Override
  public void onItemClicked(View v, int position) {
        Intent intent = new Intent(getContext(), Form_Activity.class);
        KhoaHoc khoaHoc = khoaHocs.get(position);
        intent.putExtra("khoahoc", khoaHoc);
        startActivity(intent);
  }

    //Load lại fragment sau khi add
    @Override
    public void onResume() {
        super.onResume();
       khoaHocs.clear();
       khoaHocs.addAll(db.getKhoaHocs());
        adapter.notifyDataSetChanged();
    }
}
