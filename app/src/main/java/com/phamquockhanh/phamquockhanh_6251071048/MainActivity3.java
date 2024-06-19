package com.phamquockhanh.phamquockhanh_6251071048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.phamquockhanh.phamquockhanh_6251071048.Adapter.ProductAdapter;
import com.phamquockhanh.phamquockhanh_6251071048.Database.ProductDAO;
import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityMain3Binding;
import com.phamquockhanh.phamquockhanh_6251071048.model.Product;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    private ProductDAO productDAO;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productDAO = new ProductDAO(this);
        loadProductList();


        binding.productListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = adapter.getItem(position);
                if (product != null) {
                    showAddProductDialog();
                }
                return true;
            }
        });

        binding.productListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = adapter.getItem(position);
                if (product != null) {
                    showAddProductDialog();
                }
                return true;
            }
        });
    }

    private void loadProductList() {
        List<Product> products = productDAO.getAllProducts();
        adapter = new ProductAdapter(this, products);
        binding.productListView.setAdapter(adapter);
    }

    private void showAddProductDialog() {
        AddProductDialog dialog = new AddProductDialog(MainActivity3.this, productDAO, adapter);
        dialog.show();
    }

    private void showProductDetailDialog(Product product) {
        ProductDetailDialog dialog = new ProductDetailDialog(MainActivity3.this, product, productDAO, adapter);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productDAO.close();
    }
}
