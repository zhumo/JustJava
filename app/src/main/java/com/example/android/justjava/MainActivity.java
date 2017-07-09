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
        orderSummaryTextView().setText(orderSummaryText());
        orderSummaryLabel().setVisibility(View.VISIBLE);
        orderSummaryTextView().setVisibility(View.VISIBLE);
        orderButton().setVisibility(View.GONE);
    }

    public void incrementQuantity(View view) {
        orderQuantity ++;
        redrawQuantity();
    }

    public void decrementQuantity(View view) {
        if (orderQuantity > 1) {
            orderQuantity--;
            redrawQuantity();
        }
    }

    private String price() {
        double orderPrice = orderQuantity * UNIT_PRICE;
        return NumberFormat.getCurrencyInstance().format(orderPrice);
    }

    private void redrawQuantity() {
        quantityTextView().setText("" + orderQuantity);
    }

    private TextView orderButton() { return (TextView) findViewById(R.id.order_button); }
    private TextView quantityTextView() {
        return (TextView) findViewById(R.id.quantity_text_view);
    }
    private TextView orderSummaryLabel() { return (TextView) findViewById(R.id.order_summary_label); }
    private TextView orderSummaryTextView() { return (TextView) findViewById(R.id.order_summary_text_view); }

    private String orderSummaryText() {
        String text = "Name: Mo Zhu";
        text += "\nQuantity: " + orderQuantity;
        text += "\nTotal: " + price();
        text += "\nThank you!";
        return text;
    }
}
