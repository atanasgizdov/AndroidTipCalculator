package com.example.nasko.assignment1;

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


    // open phone app and call Bentley

        public void callPhone () {
            try {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7818912000"));
                startActivity(callIntent);
            }
            catch (SecurityException e) {
                Toast.makeText(this, "Opps - you don't have permissions to do that", Toast.LENGTH_SHORT).show();
            }
    }

    public void bentleyMap () {
        String uri = String.format(Locale.ENGLISH, "https://www.google.com/maps/place/Bentley+University/@42.3856989,-71.2238687,17z/data=!3m1!4b1!4m5!3m4!1s0x89e39d36c9ba1a47:0x861ff9c80e2690aa!8m2!3d42.3856989!4d-71.22168");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        try
        {
            startActivity(intent);
        }
        catch(ActivityNotFoundException e)
        {
            try
            {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            }
            catch(ActivityNotFoundException innerEx)
            {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
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

