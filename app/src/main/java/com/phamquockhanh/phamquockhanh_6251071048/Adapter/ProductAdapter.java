package com.phamquockhanh.phamquockhanh_6251071048.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phamquockhanh.phamquockhanh_6251071048.R;
import com.phamquockhanh.phamquockhanh_6251071048.model.Product;

import java.util.List;

/*
public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(product.getName());

        return convertView;
    }
}
*/

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
        this.context = context;
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        Product product = products.get(position);

        TextView codeTextView = convertView.findViewById(R.id.codeTextView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView priceTextView = convertView.findViewById(R.id.priceTextView);

        codeTextView.setText(product.getCode());
        nameTextView.setText(product.getName());
        priceTextView.setText(String.valueOf(product.getPrice()));

        return convertView;
    }
}
