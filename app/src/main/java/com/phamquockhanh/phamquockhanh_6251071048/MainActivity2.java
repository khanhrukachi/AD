package com.phamquockhanh.phamquockhanh_6251071048;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

import com.phamquockhanh.phamquockhanh_6251071048.databinding.ActivityMain2Binding;
import com.phamquockhanh.phamquockhanh_6251071048.Adapter.ProductAdapter;
import com.phamquockhanh.phamquockhanh_6251071048.Database.ProductDAO;
import com.phamquockhanh.phamquockhanh_6251071048.model.Product;

import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    private ProductDAO productDAO;
    private ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productDAO = new ProductDAO(this);
        loadProductList();

        binding.productListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = adapter.getItem(position);
                if (product != null) {
                    showProductDetailDialog(product);
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

    private void showProductDetailDialog(Product product) {
        ProductDetailDialog dialog = new ProductDetailDialog(this, product, productDAO, adapter);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productDAO.close();
    }
}