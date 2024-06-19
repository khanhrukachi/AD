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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityMain04Binding;
import java.util.Random;

public class MainActivity04 extends AppCompatActivity {

    private ActivityMain04Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain04Binding.inflate(getLayoutInflater());
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

    private void generateViews(int numberOfItems) {
        binding.viewContainer.removeAllViews();
        Random random = new Random();

        Handler handler = new Handler(Looper.getMainLooper());

        int rows = (int) Math.ceil((double) numberOfItems / 3);

        for (int i = 0; i < rows; i++) {
            final int rowIndex = i;

            handler.post(() -> {
                LinearLayout rowLayout = new LinearLayout(MainActivity04.this);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                rowLayout.setLayoutParams(rowParams);

                int itemIndex1 = rowIndex * 3;
                if (itemIndex1 < numberOfItems) {
                    TextView textView1 = createTextView(random.nextInt(10));
                    rowLayout.addView(textView1);
                }

                int itemIndex2 = rowIndex * 3 + 1;
                if (itemIndex2 < numberOfItems) {
                    TextView textView2 = createTextView(random.nextInt(10));
                    rowLayout.addView(textView2);
                }

                int itemIndex3 = rowIndex * 3 + 2;
                if (itemIndex3 < numberOfItems) {
                    TextView textView3 = createTextView(random.nextInt(10));
                    rowLayout.addView(textView3);
                }

                binding.viewContainer.addView(rowLayout);
            });
        }
    }

    private TextView createTextView(int value) {
        TextView textView = new TextView(MainActivity04.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        params.gravity = Gravity.CENTER;
        params.setMargins(8, 8, 8, 8);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText(String.valueOf(value));

        int backgroundColor = (value % 2 == 0) ? Color.BLUE : Color.GRAY;
        textView.setBackgroundColor(backgroundColor);

        textView.setTextColor(Color.WHITE);
        textView.setTextSize(24);

        return textView;
    }
}
