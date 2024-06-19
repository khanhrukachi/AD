package com.phamquockhanh.phamquockhanh_6251071048;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityMain05Binding;
import java.util.Random;

public class MainActivity05 extends AppCompatActivity {

    private ActivityMain05Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain05Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnDrawView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = binding.inputNumber.getText().toString();
                if (!inputText.isEmpty()) {
                    try {
                        int value = Integer.parseInt(inputText);
                        if (value > 0) {
                            generateViews(value);
                        } else {
                            binding.viewContainer.removeAllViews();
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập số lớn hơn 0", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        binding.viewContainer.removeAllViews();
                        Toast.makeText(getApplicationContext(), "Vui lòng nhập một số hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    binding.viewContainer.removeAllViews();
                }
            }
        });
    }

    private void generateViews(int count) {
        binding.viewContainer.removeAllViews();
        Random random = new Random();

        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                final int rowIndex = i;
                handler.post(() -> {
                    LinearLayout row = new LinearLayout(MainActivity05.this);
                    row.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    row.setOrientation(LinearLayout.HORIZONTAL);
                    row.setGravity(Gravity.CENTER_VERTICAL);  // Center vertically

                    // First TextView (2/3 of the width)
                    TextView textView1 = new TextView(MainActivity05.this);
                    int value1 = random.nextInt(10);
                    textView1.setText(String.valueOf(value1));
                    textView1.setBackgroundColor(value1 % 2 == 0 ? Color.BLUE : Color.GRAY);
                    textView1.setTextColor(Color.WHITE);
                    textView1.setTextSize(26);
                    textView1.setGravity(Gravity.CENTER);  // Center text
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
                    params1.setMargins(10, 10, 10, 10);  // Add margins
                    textView1.setLayoutParams(params1);

                    // Second TextView (1/3 of the width)
                    TextView textView2 = new TextView(MainActivity05.this);
                    int value2 = random.nextInt(10);
                    textView2.setText(String.valueOf(value2));
                    textView2.setBackgroundColor(value2 % 2 == 0 ? Color.BLUE : Color.GRAY);
                    textView2.setTextColor(Color.WHITE);
                    textView2.setTextSize(26);
                    textView2.setGravity(Gravity.CENTER);  // Center text
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                    params2.setMargins(10, 10, 10, 10);  // Add margins
                    textView2.setLayoutParams(params2);

                    if (rowIndex % 2 == 0) {
                        row.addView(textView1);
                        row.addView(textView2);
                    } else {
                        row.addView(textView2);
                        row.addView(textView1);
                    }

                    binding.viewContainer.addView(row);
                });
            }
        }).start();
    }
}
