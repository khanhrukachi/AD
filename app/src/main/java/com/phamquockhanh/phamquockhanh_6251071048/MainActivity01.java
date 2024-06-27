package com.phamquockhanh.phamquockhanh_6251071048;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityMain01Binding;

import java.util.Random;

public class MainActivity01 extends AppCompatActivity {

    private ActivityMain01Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain01Binding.inflate(getLayoutInflater());
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
                final int index = i;
                handler.post(() -> {
                    final int randNumb;
                    randNumb = random.nextInt(100);
                    int screenWidth = binding.getRoot().getWidth();

                    int textViewWidth = (int) (screenWidth * 0.5- 42);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(textViewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(15, 20, 15, 20);

                    TextView textView = new TextView(MainActivity01.this);
                    textView.setLayoutParams(params);
                    textView.setText(String.valueOf(randNumb));
                    textView.setTextSize(26);
                    textView.setTextColor(Color.WHITE);
                    textView.setGravity(Gravity.CENTER);

                    params.weight = 5;
                    if (index % 2 != 0) {
                        params.gravity = Gravity.END;
                    } else {
                        params.gravity = Gravity.START;
                    }
                    if (randNumb % 2 != 0) {
                        textView.setBackgroundColor(Color.rgb(255, 87, 34));
                    } else {
                        textView.setBackgroundColor(Color.rgb(0, 150, 136));
                    }

                    binding.viewContainer.addView(textView);

                });
            }
        }).start();
    }
}
