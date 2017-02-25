package com.example.nasko.assignment1;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    final HashMap<String, Double> map = new HashMap<String, Double>();
    public  static double totalBill = 0;
    public  static double totalTip = 0;
    public  static double totalPerPerson = 0;
    public  static double tipPerPerson = 0;
    public static double grandtotalpp = 0;
    public static double checkamount = 0;
    public static int numberofPeople = 0;
    public static double tipPercentage = 0;
    boolean validvalues = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // autoset Tip % to 15%
        EditText editText = (EditText)findViewById(R.id.tip);
        editText.setText("15", TextView.BufferType.EDITABLE);
    }

            // calcuate everything and start intent method

        public void calculateTip (View view) {

            // Grab check, number of people and tip % amount and parse
        try {
            validvalues = true; // reset to true in case an exception is caught first time around
            EditText editText1 = (EditText) findViewById(R.id.check_amount);
            checkamount = Double.parseDouble(editText1.getText().toString());
            EditText editText2 = (EditText) findViewById(R.id.number_of_people);
            numberofPeople = Integer.parseInt(editText2.getText().toString());
            EditText editText3 = (EditText) findViewById(R.id.tip);
            tipPercentage = Double.parseDouble(editText3.getText().toString());
        }
        catch (RuntimeException e){
            validvalues = false;
        }

                Intent intent = new Intent(this, DisplayMessageActivity.class);

                try {

                    totalBill = round(checkamount);
                    totalTip = checkamount * ((double) tipPercentage / 100);
                    totalPerPerson = checkamount / numberofPeople;
                    //totalPerPerson = Math.round(totalPerPerson * 100) / 100;
                    tipPerPerson = totalTip/numberofPeople;
                    //tipPerPerson = Math.round(tipPerPerson * 100) / 100;
                    grandtotalpp = totalPerPerson + tipPerPerson;
                    if (totalPerPerson == Double.POSITIVE_INFINITY || tipPerPerson == Double.POSITIVE_INFINITY || grandtotalpp == Double.POSITIVE_INFINITY){
                        throw new IllegalArgumentException();
                    }
                }
                catch (IllegalArgumentException e){
                    validvalues = false;
                }

            if (validvalues) {
                // map values to map

                map.put("totalBill", totalBill);
                map.put("totalTip", totalTip);
                map.put("totalPerPerson", totalPerPerson);
                map.put("tipPerPerson", tipPerPerson);
                map.put("grantotalpp", grandtotalpp);

                // start intent and send values in map

                intent.putExtra("hashMap", map);
                startActivity(intent);
            }
            else
                Toast.makeText(this, "Opps - you may have forgotten to enter a value or entered a zero somewhere", Toast.LENGTH_SHORT).show();
            }


            // call phone

        public void callPhone (View view) {

            try {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:7818912000"));
                startActivity(intent);
            }
            catch (SecurityException e) {
                Toast.makeText(this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
            }
        }


        // open the map of bentley



        public void showMap (View view) {
            String uri = String.format(Locale.ENGLISH, "geo:0,0?q=175+Forest+St+Waltham+MA");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }

    // open web with activity result request
    public void openWeb (View view) {

        Intent intent = new Intent(this, webviewActivity.class);
        startActivityForResult(intent, 1);

           }

    // grab the result of the openWeb activityforresult and show toast
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                Toast.makeText(this, "Welcome back from browsing " +result, Toast.LENGTH_SHORT).show();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Did you have trouble accessing the web?", Toast.LENGTH_SHORT).show();
            }
        }
    }

        // add menu options

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }

