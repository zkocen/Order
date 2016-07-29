package com.kocen.zan.justjava;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean whipped = false;
    boolean chocholate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int calculatePrice(){
        return(quantity * 5);
    }

    public void submitOrder(View view){
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }

    private String createOrderSummary(int price) {

        EditText inputTxt = (EditText)findViewById(R.id.edit_name);
        String name = inputTxt.getText().toString();

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


    private void displayMessage(String message) {
        TextView orderSumTextView = (TextView) findViewById(R.id.order_summary);
        orderSumTextView.setText(message);
    }

    public void increment(View view){
        quantity++;
        displayQantity(quantity);
    }

    public void decrement(View view){
        quantity--;
        displayQantity(quantity);
    }

    private void displayQantity(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}
