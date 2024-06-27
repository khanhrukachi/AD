package com.phamquockhanh.phamquockhanh_6251071048;


import android.os.Bundle;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.phamquockhanh.phamquockhanh_6251071048.Adapter.ProductAdapter;
import com.phamquockhanh.phamquockhanh_6251071048.Database.ProductDAO;
import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityAddProductDialogBinding;
import com.phamquockhanh.phamquockhanh_6251071048.model.Product;


public class AddProductDialog extends Dialog {

    private ActivityAddProductDialogBinding binding;
    private final ProductDAO productDAO;
    private final ProductAdapter adapter;

    public AddProductDialog(Context context, ProductDAO productDAO, ProductAdapter adapter) {
        super(context);
        this.productDAO = productDAO;
        this.adapter = adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = binding.codeEditText.getText().toString().trim();
                String name = binding.nameEditText.getText().toString().trim();
                String priceStr = binding.priceEditText.getText().toString().trim();

                if (TextUtils.isEmpty(code) || TextUtils.isEmpty(name) || TextUtils.isEmpty(priceStr)) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                double price;
                try {
                    price = Double.parseDouble(priceStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Giá không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create new Product object
                Product newProduct = new Product();
                newProduct.setCode(code);
                newProduct.setName(name);
                newProduct.setPrice((int) price);

                // Add product to database
                long id = productDAO.addProduct(newProduct);
                if (id != -1) {
                    newProduct.setId((int) id);
                    adapter.insert(newProduct, 0);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Thêm sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        // Set dialog properties
        Window window = getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.windowAnimations = android.R.style.Animation_Dialog;
            window.setAttributes(layoutParams);
        }
    }
}

