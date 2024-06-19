package com.phamquockhanh.phamquockhanh_6251071048;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityMain02Binding;
import java.util.Random;

public class MainActivity02 extends AppCompatActivity {

    private ActivityMain02Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain02Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    try {
                        int value = Integer.parseInt(s.toString());
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

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });
    }

    private void generateViews(int count) {
        binding.viewContainer.removeAllViews();
        Random random = new Random();

        Handler handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                int value = random.nextInt(101);
                final int index = i;

                handler.post(() -> {
                    if (index % 2 == 0) {
                        Button button = new Button(MainActivity02.this);
                        button.setText(String.valueOf(value));
                        binding.viewContainer.addView(button);
                    } else {
                        EditText editText = new EditText(MainActivity02.this);
                        editText.setText(String.valueOf(value));
                        binding.viewContainer.addView(editText);
                    }
                });
            }
        }).start();
    }
}
