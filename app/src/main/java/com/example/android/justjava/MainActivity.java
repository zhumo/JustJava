package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee
 */
public class MainActivity extends AppCompatActivity {

    private int orderQuantity = 1;
    private static final double UNIT_PRICE = 5.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method gets called when the order button is clicked
     */
    public void submitOrder(View view) {
        orderQuantity = 1;
        redraw();
    }

    public void incrementQuantity(View view) {
        orderQuantity ++;
        redraw();
    }

    public void decrementQuantity(View view) {
        if (orderQuantity > 1) {
            orderQuantity--;
            redraw();
        }
    }

    private String price() {
        double orderPrice = orderQuantity * UNIT_PRICE;
        return NumberFormat.getCurrencyInstance().format(orderPrice);
    }

    private void redraw() {
        redrawQuantity();
        redrawPrice();
    }

    private void redrawQuantity() {
        quantityTextView().setText("" + orderQuantity);
    }

    private TextView quantityTextView() {
        return (TextView) findViewById(R.id.quantity_text_view);
    }

    private void redrawPrice() {
        priceTextView().setText(price());
    }

    private TextView priceTextView() {
        return (TextView) findViewById(R.id.price_text_view);
    }
}
