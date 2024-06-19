package com.phamquockhanh.phamquockhanh_6251071048.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.phamquockhanh.phamquockhanh_6251071048.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("code", product.getCode());
        values.put("name", product.getName());
        values.put("price", product.getPrice());

        // Inserting Row
        long id = db.insert("products", null, values);
        // Closing database connection
        return id;
    }

    @SuppressLint("Range")
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Cursor cursor = db.query("products", null, null, null, null, null, "name");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex("id")));
                product.setCode(cursor.getString(cursor.getColumnIndex("code")));
                product.setName(cursor.getString(cursor.getColumnIndex("name")));
                product.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                products.add(product);
            }
            cursor.close();
        }
        return products;
    }
    @SuppressLint("Range")
    public Product getProductByName(String name) {
        Product product = null;
        Cursor cursor = db.query("products", null, "name = ?", new String[]{name}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            product = new Product();
            product.setId(cursor.getInt(cursor.getColumnIndex("id")));
            product.setCode(cursor.getString(cursor.getColumnIndex("code")));
            product.setName(cursor.getString(cursor.getColumnIndex("name")));
            product.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            cursor.close();
        }
        return product;
    }


    public void deleteProduct(int id) {
        db.delete("products", "id = ?", new String[]{String.valueOf(id)});
    }

    public void close() {
        dbHelper.close();
    }
}
