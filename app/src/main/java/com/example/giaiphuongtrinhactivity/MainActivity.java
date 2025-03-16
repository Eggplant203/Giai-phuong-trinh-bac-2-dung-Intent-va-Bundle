package com.example.giaiphuongtrinhactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etInputA, etInputB, etInputC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo
        etInputA = findViewById(R.id.etInputA);
        etInputB = findViewById(R.id.etInputB);
        etInputC = findViewById(R.id.etInputC);
        Button btnSolve = findViewById(R.id.btnSolve);

        // Nhấn nút "Giải phương trình"
        btnSolve.setOnClickListener(v -> {
            // Kiểm tra xem người dùng đã nhập đủ dữ liệu chưa
            if (etInputA.getText().toString().isEmpty() ||
                    etInputB.getText().toString().isEmpty() ||
                    etInputC.getText().toString().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ các hệ số a, b, c", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy giá trị từ các ô nhập
            double a, b, c;
            try {
                a = Double.parseDouble(etInputA.getText().toString());
                b = Double.parseDouble(etInputB.getText().toString());
                c = Double.parseDouble(etInputC.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập các số hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra trường hợp a = 0 (không phải phương trình bậc 2)
            if (a == 0) {
                Toast.makeText(this, "Hệ số a phải khác 0 để là phương trình bậc 2", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo Intent và Bundle để chuyển dữ liệu
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("a", a);
            bundle.putDouble("b", b);
            bundle.putDouble("c", c);
            intent.putExtras(bundle);

            // Chuyển sang SecondActivity
            startActivityForResult(intent, 1);
        });
    }

    // Nhận kết quả từ SecondActivity khi quay lại
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
        }
    }
}
