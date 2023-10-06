package com.example.foodorderbasic.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderbasic.AdapterAdmin.DoanhthuAdapter;
import com.example.foodorderbasic.Model.BillModel;
import com.example.foodorderbasic.R;
import com.example.foodorderbasic.RoomDatabase.BillDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DoanhthuActivity extends AppCompatActivity {
    EditText edtdatebatdau , edtdateketthuc ;
    ImageView imgdatebatdau , imgdateketthuc;
    RecyclerView rclthongke ;
    String datebatdau = "";
    String dateketthuc = "";

    TextView txtdoanhthu;

    double tongtienupdate= 0;

    Button kiemtra ;
    ArrayList<BillModel> arrayList ;

    DoanhthuAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanhthu);

        edtdatebatdau = findViewById(R.id.edtngaybatdau);
        edtdateketthuc  =findViewById(R.id.edtngayketthuc);

        imgdatebatdau = findViewById(R.id.imgngaybatdau);
        imgdateketthuc = findViewById(R.id.imgngayketthuc);
        rclthongke = findViewById(R.id.rcldoanhthuadmin);
        kiemtra = findViewById(R.id.btnkiemtra);
        txtdoanhthu = findViewById(R.id.tvdoanhthuadmin);

        imgdatebatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ngày hiện tại
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

// Tạo DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DoanhthuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
                        String formattedDate1 = dateFormat1.format(new Date(i - 1900, i1, i2));
                        edtdatebatdau.setText(formattedDate1);
                    }
                }, year, month, dayOfMonth);
// Mở hộp thoại chọn ngày
                datePickerDialog.show();
            }
        });


        imgdateketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ngày hiện tại
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

// Tạo DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DoanhthuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
                        String formattedDate1 = dateFormat1.format(new Date(i - 1900, i1, i2));
                        edtdateketthuc.setText(formattedDate1);
                    }
                }, year, month, dayOfMonth);
// Mở hộp thoại chọn ngày
                datePickerDialog.show();
            }

        });

kiemtra.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        arrayList = new ArrayList<>() ;

        Toast.makeText(DoanhthuActivity.this, edtdatebatdau.getText().toString().trim()+"", Toast.LENGTH_SHORT).show();

        LinearLayoutManager linearLayoutManagerc = new LinearLayoutManager(DoanhthuActivity.this);

        arrayList = (ArrayList<BillModel>) BillDatabase.getInstance(DoanhthuActivity.this).billDAO().getProductsByDateRange(edtdatebatdau.getText().toString().trim()  , edtdateketthuc.getText().toString().trim());
        Toast.makeText(DoanhthuActivity.this, arrayList.size()+"", Toast.LENGTH_SHORT).show();
        if(arrayList.size()==0){
            tongtienupdate = 0;
        }
            adapter = new DoanhthuAdapter(arrayList, DoanhthuActivity.this, new DoanhthuAdapter.tongtienbilladmin() {
                @Override
                public void tongtiendoanhthuadmin(double tongtien) {
                    tongtienupdate = tongtien ;
                    txtdoanhthu.setText("Doanh thu là : " +tongtienupdate+ "VNĐ");
                }
            });


        txtdoanhthu.setText("Doanh thu là : " +tongtienupdate+ "VNĐ");

        rclthongke.setLayoutManager(linearLayoutManagerc);
        rclthongke.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tongtienupdate = 0;
    }
});


    }
}