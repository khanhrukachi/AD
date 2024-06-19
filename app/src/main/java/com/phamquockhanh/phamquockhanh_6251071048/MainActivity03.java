package com.phamquockhanh.phamquockhanh_6251071048;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityMain03Binding;

public class MainActivity03 extends AppCompatActivity {

    private ActivityMain03Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain03Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        generateViews();
    }

    private void generateViews() {
        binding.viewContainer.removeAllViews();
        Handler handler = new Handler(Looper.getMainLooper());

        String[] buttonLabels = {
                "1", "2", "3",
                "4", "5", "6",
                "7", "8", "9",
                "*", "0", "#"
        };

        int rows = 4; // 4 hàng
        int columns = 3; // 3 cột

        for (int i = 0; i < rows; i++) {
            final int rowIndex = i;

            handler.post(() -> {
                LinearLayout rowLayout = new LinearLayout(MainActivity03.this);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                rowLayout.setLayoutParams(rowParams);

                for (int j = 0; j < columns; j++) {
                    int index = rowIndex * columns + j;
                    if (index < buttonLabels.length) {
                        Button button = createButton(buttonLabels[index]);
                        rowLayout.addView(button);
                    }
                }

                binding.viewContainer.addView(rowLayout);
            });
        }
    }

    private Button createButton(String label) {
        Button button = new Button(MainActivity03.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        params.gravity = Gravity.CENTER;
        params.setMargins(8, 8, 8, 8);
        button.setLayoutParams(params);
        button.setGravity(Gravity.CENTER);
        button.setText(label);

        button.setBackgroundColor(Color.GRAY);
        button.setTextColor(Color.WHITE);
        button.setTextSize(24);

        button.setOnClickListener(view -> {
            EditText inputNumber = binding.inputNumber;
            int start = inputNumber.getSelectionStart();
            inputNumber.getText().insert(start, label);
        });

        return button;
    }
}
