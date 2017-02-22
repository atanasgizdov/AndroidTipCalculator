package com.example.nasko.assignment1;

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

import java.io.Serializable;
import java.util.HashMap;
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
    int numberofPeople;


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
    
        // Grab values from UI and Calculate tip, then start new activity

        public void calculateTip (View view) {
            // start intent
            Intent intent = new Intent(this, DisplayMessageActivity.class);

            // Grab check amount and parse to int
            EditText editText1 = (EditText) findViewById(R.id.check_amount);
            int checkamount = Integer.parseInt(editText1.getText().toString());

            // Grab # of people and parse to int

                EditText editText2 = (EditText) findViewById(R.id.number_of_people);
                int numberofPeople = Integer.parseInt(editText2.getText().toString());



            // Grab tip and parse to int
            EditText editText3 = (EditText) findViewById(R.id.tip);
            int tipPercentage = Integer.parseInt(editText3.getText().toString());

            // calculate everything

            totalBill = round(checkamount);
            totalTip = checkamount * ((double)tipPercentage/100);
            totalPerPerson = checkamount / numberofPeople;
            tipPerPerson = totalTip / numberofPeople;
            grandtotalpp = totalPerPerson + tipPerPerson;

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

    // open phone app and call Bentley

        public void callPhone () {
            try {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7818912000"));
                startActivity(callIntent);
            }
            catch (SecurityException e) {

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

