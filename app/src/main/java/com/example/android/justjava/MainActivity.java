package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee
 */
public class MainActivity extends AppCompatActivity {

    private int orderQuantity = 1;
    private static final double UNIT_PRICE = 5.0;
    private static final double WHIPPED_CREAM_PRICE = 1.0;
    private static final double CHOCOLATE_PRICE = 1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method gets called when the order button is clicked
     */
    public void submitOrder(View view) {
        Intent intent = orderSummaryEmail();
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Oops, something went wrong.", Toast.LENGTH_SHORT).show();
        }
    }

    public void incrementQuantity(View view) {
        if (orderQuantity < 10) {
            orderQuantity++;
            redrawQuantity();
        } else {
            Toast.makeText(this, "Cannot have more than 10 coffees", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrementQuantity(View view) {
        if (orderQuantity > 1) {
            orderQuantity--;
            redrawQuantity();
        } else {
            Toast.makeText(this, "Cannot have fewer than 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }

    public void resetOrder(View view) {
        orderQuantity = 1;
        whippedCreamCheckBox().setChecked(false);
        chocolateCheckBox().setChecked(false);
        nameField().setText("");
        redrawQuantity();
    }

    private String price() {
        double orderPrice = orderQuantity * UNIT_PRICE;
        if (hasWhippedCream()) {
            orderPrice += orderQuantity * WHIPPED_CREAM_PRICE;
        }
        if (hasChocolate()) {
            orderPrice += orderQuantity * CHOCOLATE_PRICE;
        }
        return NumberFormat.getCurrencyInstance().format(orderPrice);
    }

    private void redrawQuantity() {
        quantityTextView().setText("" + orderQuantity);
    }
    private TextView quantityTextView() {
        return (TextView) findViewById(R.id.quantity_text_view);
    }
    private CheckBox whippedCreamCheckBox() { return (CheckBox) findViewById(R.id.whipped_cream_checkbox); }
    private boolean hasWhippedCream() { return whippedCreamCheckBox().isChecked(); }
    private CheckBox chocolateCheckBox() { return (CheckBox) findViewById(R.id.chocolate_checkbox); }
    private boolean hasChocolate() { return chocolateCheckBox().isChecked(); }
    private EditText nameField() { return (EditText) findViewById(R.id.name_field); }
    private String name() { return nameField().getText().toString(); }
    private Intent orderSummaryEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, orderSummaryText());
        return intent;
    }

    private String orderSummaryText() {
        String text = "Name: " + name();
        text += "\nAdd whipped cream? " + hasWhippedCream();
        text += "\nAdd chocolate? " + hasChocolate();
        text += "\nQuantity: " + orderQuantity;
        text += "\nTotal: " + price();
        text += "\nThank you!";
        return text;
    }
}
