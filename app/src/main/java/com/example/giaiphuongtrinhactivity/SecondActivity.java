package com.example.giaiphuongtrinhactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private EditText etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etResult = findViewById(R.id.etResult);
        Button btnBack = findViewById(R.id.btnBack);

        // Nhận dữ liệu từ Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            double a = bundle.getDouble("a");
            double b = bundle.getDouble("b");
            double c = bundle.getDouble("c");

            // Giải phương trình và hiển thị kết quả
            String result = solveQuadraticEquation(a, b, c);
            etResult.setText(result);
        }

        // Nhấn nút "BACK" trở về MainActivity
        btnBack.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    // Phương thức giải phương trình bậc 2
    private String solveQuadraticEquation(double a, double b, double c) {
        // Tính delta
        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            // Trường hợp 1: Delta < 0, phương trình vô nghiệm
            return "Phương trình vô nghiệm";
        } else if (delta == 0) {
            // Trường hợp 2: Delta = 0, phương trình có nghiệm kép
            double x = -b / (2 * a);
            return "Phương trình có nghiệm kép: \n x = " + formatResult(x);
        } else {
            // Trường hợp 3: Delta > 0, phương trình có 2 nghiệm phân biệt
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Phương trình có 2 nghiệm phân biệt:\nx₁ = " + formatResult(x1) + "\nx₂ = " + formatResult(x2);
        }
    }

    private String formatResult(double value) {
        // Kiểm tra xem kết quả có phải là số nguyên
        if (value == (int) value) {
            return String.valueOf((int) value);
        } else {
            return String.valueOf(value);
        }
    }
}
