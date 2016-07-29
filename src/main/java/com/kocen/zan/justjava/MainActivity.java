package com.kocen.zan.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean whipped = false;
    boolean chocholate = false;
    String name;
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int calculatePrice(){
        int oneCoffee = 5;
        if(whipped){
            oneCoffee++;
        }
        if (chocholate){
            oneCoffee = oneCoffee + 2;
        }
        return quantity * oneCoffee;
    }

    public void submitOrder(View view){
        price = calculatePrice();
        edit_name(view);
        String priceMessage = createOrderSummary();
        displayMessage(priceMessage);
    }

    public void sendOrder(View view){
        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_SUBJECT, "Order for " + name);
        email.putExtra(Intent.EXTRA_TEXT, createOrderSummary());
        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }
    }

    private String createOrderSummary() {

        return("ORDER SUMMARY\n" +
                "\nName: " + name +
                "\nAdd whipped cream? " + whipped +
                "\nAdd chocolate? " + chocholate +
                "\nQuantity: " + quantity +
                "\nTotal: " + price +
                "\nThank you!");
    }

    public void onWhipClicked(View View){
        CheckBox checkBox = (CheckBox)findViewById(R.id.whipped_checkbox);
        if(checkBox.isChecked()){
            whipped = true;
        } else {
            whipped = false;
        }
    }

    public void onChocolateClicked(View view){
        CheckBox checkBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        if(checkBox.isChecked()){
            chocholate = true;
        } else {
            chocholate = false;
        }
    }
    public void edit_name(View view) {
        EditText inputTxt = (EditText) findViewById(R.id.edit_name);
        name = inputTxt.getText().toString();
    }

    private void displayMessage(String message) {
        TextView orderSumTextView = (TextView) findViewById(R.id.order_summary);
        orderSumTextView.setText(message);
    }

    public void increment(View view){
        if (quantity == 100){
            Toast.makeText(this, "You cannot order more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQantity(quantity);
    }

    public void decrement(View view){
        if (quantity == 0) {
            Toast.makeText(this, "You cannot have less than 0 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQantity(quantity);
    }

    private void displayQantity(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}
