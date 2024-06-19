package com.phamquockhanh.phamquockhanh_6251071048;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;


import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityProductDetailDialogBinding;
import com.phamquockhanh.phamquockhanh_6251071048.model.Product;
import com.phamquockhanh.phamquockhanh_6251071048.Adapter.ProductAdapter;
import com.phamquockhanh.phamquockhanh_6251071048.Database.ProductDAO;

public class ProductDetailDialog extends Dialog {

    private ActivityProductDetailDialogBinding binding;
    private Product product;
    private ProductDAO productDAO;
    private ProductAdapter adapter;

    public ProductDetailDialog(Context context, Product product, ProductDAO productDAO, ArrayAdapter<Product> adapter) {
        super(context);
        this.product = product;
        this.productDAO = productDAO;
        this.adapter = (ProductAdapter) adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.codeTextView.setText(product.getCode());
        binding.nameTextView.setText(product.getName());
        binding.priceTextView.setText(String.valueOf(product.getPrice()));

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDAO.deleteProduct(product.getId());
                adapter.remove(product);
                adapter.notifyDataSetChanged();
                dismiss();
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

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