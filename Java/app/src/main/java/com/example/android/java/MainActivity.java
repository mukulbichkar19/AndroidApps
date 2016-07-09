package com.example.android.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method gets called when Order button is pressed.
     */
    public void submitOrder(View view){

        String message = "Total: $"+(quantity*price);
        message += "\nThank You!";
        display(quantity);
        displayPrice(quantity*price);
        displayMessage(message);
    }

    /**
     * This method gets called when plus is pressed.
     */
    public void increment(View view){
        quantity++;
        display(quantity);
    }


    /**
     * This method gets called when minus is pressed.
     */
    public void decrement(View view){

        if(quantity>1){
            quantity--;
        }
        display(quantity);
    }




    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + number);
    }

    // Display the price
    private void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    // Set the text inside price text view to the given message.
    private void displayMessage(String message){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);

    }





}
