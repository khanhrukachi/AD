package com.phamquockhanh.phamquockhanh_6251071048.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "code TEXT, " +
                "name TEXT, " +
                "price INTEGER)";
        db.execSQL(createTable);

        // Insert 6 sample data entries
        db.execSQL("INSERT INTO products (code, name, price) VALUES ('SP-001', 'Vertu Constellation', 10000)");
        db.execSQL("INSERT INTO products (code, name, price) VALUES ('SP-002', 'iPhone 5S', 5000)");
        db.execSQL("INSERT INTO products (code, name, price) VALUES ('SP-003', 'Nokia Lumia 925', 3000)");
        db.execSQL("INSERT INTO products (code, name, price) VALUES ('SP-004', 'Samsung Galaxy S4', 4000)");
        db.execSQL("INSERT INTO products (code, name, price) VALUES ('SP-005', 'HTC Desire 600', 3500)");
        db.execSQL("INSERT INTO products (code, name, price) VALUES ('SP-006', 'HKPhone Revo LEAD', 4500)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }
}
