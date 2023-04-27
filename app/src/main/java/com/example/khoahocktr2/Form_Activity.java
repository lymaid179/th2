package com.example.khoahocktr2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.khoahocktr2.Model.KhoaHoc;
import com.example.khoahocktr2.database.Database;

import java.util.Calendar;

public class Form_Activity extends AppCompatActivity {
    Button addButton, cancelButton, dateButton, updateButton, removeButton;
    EditText txtTen, txtHocphi;
    TextView txtNgay;
    RadioGroup chuyenNganhRadioGroup;
    CheckBox kichhoat;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        txtTen = findViewById(R.id.editTextTenCV);
        txtHocphi = findViewById(R.id.edittextHP);
        txtNgay = findViewById(R.id.textViewNgay);
        dateButton = findViewById(R.id.dateButton);
        chuyenNganhRadioGroup = findViewById(R.id.radioGroup);
        kichhoat = findViewById(R.id.checkBox);
        updateButton = findViewById(R.id.updateButton);
        removeButton = findViewById(R.id.removeButton);

          Database db = new Database(this);

        //Intent
        Intent intent = getIntent();
        KhoaHoc khoaHoc = (KhoaHoc) intent.getSerializableExtra("khoahoc");

        if (khoaHoc == null) {
            updateButton.setVisibility(View.GONE);
            removeButton.setVisibility(View.GONE);
        } else {
            txtTen.setText(khoaHoc.getTen());
            txtHocphi.setText(khoaHoc.getHocphi());
            txtNgay.setText(khoaHoc.getNgaybd());
            switch (khoaHoc.getChuyennganh()) {
                case 0:
                    chuyenNganhRadioGroup.check(R.id.radioButton);
                    break;
                case 1:
                    chuyenNganhRadioGroup.check(R.id.radioButton1);
                    break;
                case 2:
                    chuyenNganhRadioGroup.check(R.id.radioButton2);
                    break;
                case 3:
                    chuyenNganhRadioGroup.check(R.id.radioButton3);
                    break;
            }
            kichhoat.setChecked(khoaHoc.getKichhoat());

            addButton.setVisibility(View.GONE);
        }

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int cy = c.get(Calendar.YEAR);
                int cm = c.get(Calendar.MONTH);
                int cd = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Form_Activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                                txtNgay.setText(d + "/" + m + "/" + y);
                            }
                        }, cy, cm, cd);
                dialog.show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = txtTen.getText().toString();
                String hocphi = txtHocphi.getText().toString();
                String ngay = txtNgay.getText().toString();
                int chuyennganh = 0;
                boolean khoat = kichhoat.isChecked();

                switch (chuyenNganhRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton:
                        chuyennganh = 0;
                        break;
                    case R.id.radioButton1:
                        chuyennganh = 1;
                        break;
                    case R.id.radioButton2:
                        chuyennganh = 2;
                        break;

                    case R.id.radioButton3:
                        chuyennganh = 3;
                        break;
                }

                if (ten.isEmpty()  || ngay.isEmpty() || String.valueOf(hocphi).isEmpty()) {
                    Toast.makeText(Form_Activity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {


                    KhoaHoc KH1 = new KhoaHoc(ten, hocphi, ngay, chuyennganh, khoat);

                       db.insertKH(KH1);
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = txtTen.getText().toString();
                String hocphi = txtHocphi.getText().toString();
                String ngay = txtNgay.getText().toString();
                int chuyennganh = 0;
                boolean khoat = kichhoat.isChecked();

                switch (chuyenNganhRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton:
                        chuyennganh = 0;
                        break;
                    case R.id.radioButton1:
                        chuyennganh = 1;
                        break;
                    case R.id.radioButton2:
                        chuyennganh = 2;
                        break;

                    case R.id.radioButton3:
                        chuyennganh = 3;
                        break;
                }

                if (ten.isEmpty() || hocphi.isEmpty() || ngay.isEmpty()) {
                    Toast.makeText(Form_Activity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {



                    khoaHoc.setTen(ten);
                    khoaHoc.setHocphi(hocphi);
                    khoaHoc.setNgaybd(ngay);
                    khoaHoc.setChuyennganh(chuyennganh);
                    khoaHoc.setKichhoat(khoat);
                   db.updateKH(khoaHoc);
                    finish();
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteCV(khoaHoc.getId());
                finish();
            }
        });

    }
}
