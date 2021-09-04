package com.example.android.justjava;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    private String createOrderSummary(String getname, int price, boolean addWhippedCream, boolean addchocolate) {
        String priceMessage = "Name:" + getname;
        priceMessage += "\n Add whipped cream? " + addWhippedCream;
        priceMessage += "\n Add Chocolate? " + addchocolate;
        priceMessage += "\n Quantity:" + quantity;
        priceMessage += "\nTotal:$" + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        Log.v("MainActivity", "Has Whipped Cream" + hasWhippedCream);
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for" + name);
        intent.putExtra(Intent.EXTRA_SUBJECT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

        /**

         This method is called when the minus button is clicked.
         */

        /**

         This method displays the given quantity value on the screen.
         */
        private void displayQuantity ( int num){
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setText("" + num);
        }

        /**
         * Calculates the price of the order.
         *
         is the number of cups of coffee ordered
         @return total price
         */

        private int calculatePrice ( boolean cr, boolean ch){
            int baseprice = 5;
            if (cr)
                baseprice = baseprice + 1;
            else if (ch)
                baseprice += 2;
            return (quantity * baseprice);
        }
        public void increment (View view)
        {
            if (quantity == 100) {
                Context context = getApplicationContext();
                CharSequence text = "Only 100 Cups allowed";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
        /**

         This method is called when the minus button is clicked.
         */
        public void decrement (View view)
        {
            if (quantity == 1) {
                Context context = getApplicationContext();
                CharSequence text = "Minumum 1 cups ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

