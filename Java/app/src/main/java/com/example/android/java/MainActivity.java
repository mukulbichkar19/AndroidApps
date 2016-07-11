package com.example.android.java;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 10;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * This method gets called when Order button is pressed.
     */
    public void submitOrder(View view) {

        boolean isWhippedCreamSelected = ((CheckBox) findViewById(R.id.toppings_check_box)).isChecked();

        boolean isChocolateSelected = ((CheckBox) findViewById(R.id.chocolate_check_box)).isChecked();

        String userInputedText = ((TextView) findViewById(R.id.album_description_view)).getText().toString();




        display(quantity);
        //displayPrice(quantity * price);

        //displayMessage(createOrder(calculatePrice(isChocolateSelected,isWhippedCreamSelected), isWhippedCreamSelected, isChocolateSelected,userInputedText));
        //price=0;
        String data = createOrder(calculatePrice(isChocolateSelected,isWhippedCreamSelected), isWhippedCreamSelected, isChocolateSelected,userInputedText);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "JavaOrder for "+userInputedText);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method gets called when plus is pressed.
     */
    public void increment(View view) {
        quantity++;
        display(quantity);
    }


    /**
     * This method gets called when minus is pressed.
     */
    public void decrement(View view) {

        if (quantity > 1) {
            quantity--;
        }
        display(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + number);
    }

    // Display the price
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    // Set the text inside price text view to the given message.
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    private int calculatePrice(boolean chocolate,boolean whippedcream) {
        int totalPrice;
        int withtop=price;
        if(chocolate && !whippedcream){
            withtop+=2;

        }
        else if(whippedcream && !chocolate){
            withtop+=1;
        }
        else if(whippedcream && chocolate){
            withtop+=3;
        }

        totalPrice = quantity * withtop;

        return totalPrice;

    }

    private String createOrder(int price, boolean topping, boolean chocolate,String username) {

        String str = "";
        str += "Name: " +username+ "\nAdd "+getText(R.string.whipped_cream) +" ?: " + topping + "\nAdd Chocolate?: " + chocolate + "\nQuantity: " + quantity + "\nTotal: $" + price +"\n"+ getString(R.string.thank_you);
        return str;
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.java/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.java/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
