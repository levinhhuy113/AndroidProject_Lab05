package com.example.lab05;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtEmployeeID, edtEmployeeName, edtEmployeePhone;
    Button btnAdd;
    ListView lvEmployees;

    ArrayList<String> employeeList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ các View trong layout
        edtEmployeeID = findViewById(R.id.edtEmployeeID);
        edtEmployeeName = findViewById(R.id.edtEmployeeName);
        edtEmployeePhone = findViewById(R.id.edtEmployeePhone);
        btnAdd = findViewById(R.id.btnAdd);
        lvEmployees = findViewById(R.id.lvEmployees);

        // 2. Khởi tạo danh sách và adapter
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        lvEmployees.setAdapter(adapter);

        // 3. Sự kiện Thêm nhân viên
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtEmployeeID.getText().toString();
                String name = edtEmployeeName.getText().toString();
                String phone = edtEmployeePhone.getText().toString();

                if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String employee = id + " - " + name + " - " + phone;
                    employeeList.add(employee);
                    adapter.notifyDataSetChanged(); // Cập nhật danh sách
                    clearFields();
                }
            }
        });

        // 4. Sự kiện Click vào nhân viên (hiển thị chi tiết)
        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedEmployee = employeeList.get(position);
                String[] parts = selectedEmployee.split(" - ");
                edtEmployeeID.setText(parts[0]);
                edtEmployeeName.setText(parts[1]);
                edtEmployeePhone.setText(parts[2]);
            }
        });

        // 5. Sự kiện Long Click (xóa nhân viên)
        lvEmployees.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                employeeList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    // Hàm xóa dữ liệu nhập sau khi thêm
    private void clearFields() {
        edtEmployeeID.setText("");
        edtEmployeeName.setText("");
        edtEmployeePhone.setText("");
        edtEmployeeID.requestFocus();
    }
}
