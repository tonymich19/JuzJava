package com.example.android.juzjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int quantity = 2;
    private int totalPrice;
    private int coffeePrice = 5;
    private boolean hasWhippedCream;
    private boolean hasChocolate;
    private int whippedCreamPrice = 2;
    private int chocolatePrice = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 10) {
            Context context = getApplicationContext();
            CharSequence text = "Sorry! You can't have more than 10 coffees.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        quantity = quantity + 1;
        display(quantity);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quatity_text_view);
        quantityTextView.setText("" + number);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            Context context = getApplicationContext();
            CharSequence text = "You can't have 0 coffes.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    public void submitOrder(View view) {
        //displayPrice(createOrderSummary());

        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java" + getName());
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"});
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void displayPrice(String orderSummary) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        setToppingTotalPrice();

        priceTextView.setText(orderSummary);
    }

    public int setToppingTotalPrice() {
        int toppingTotalPrice = 0;
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = whippedCream.isChecked();

        if (hasWhippedCream == true) {
            toppingTotalPrice = +whippedCreamPrice;
        }
        if (hasChocolate == true) {
            toppingTotalPrice = +chocolatePrice;
        }
        return toppingTotalPrice;
    }

    private String getName() {
        EditText nameTextview = (EditText) findViewById(R.id.name_view);
        String name = nameTextview.getText().toString();
        return name;
    }

    private String createOrderSummary() {
        String orderMessage = "Name: " + getName();
        orderMessage += "\nQuantity: " + quantity;
        orderMessage += "\nHave Whipped Cream: " + hasWhippedCream;
        orderMessage += "\nHave Chocolate: " + hasChocolate;
        orderMessage += "\nTotal: " + setTotalPrice();
        orderMessage += "\nThank you! =D";

        return orderMessage;
    }

    public int setTotalPrice() {
        totalPrice = (coffeePrice * quantity) + setToppingTotalPrice();
        return totalPrice;
    }


}