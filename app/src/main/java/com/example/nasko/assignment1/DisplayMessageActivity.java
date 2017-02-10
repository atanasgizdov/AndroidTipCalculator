package com.example.nasko.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // grab hashmap from previous activity
        Intent intent = getIntent();
        HashMap<String, Double> hashMap = (HashMap<String, Double>) intent.getSerializableExtra("hashMap");

        // show values on page in two decimal format

        DecimalFormat f = new DecimalFormat("##.00");

        TextView totalBill = (TextView) findViewById(R.id.totalBill);
        totalBill.setText("$" + String.valueOf(f.format(hashMap.get("totalBill"))));

        TextView totalTip = (TextView) findViewById(R.id.totalTip);
        totalTip.setText("$" + String.valueOf(f.format(hashMap.get("totalTip"))));

        TextView totalPerPerson = (TextView) findViewById(R.id.totalPerPerson);
        totalPerPerson.setText("$" + String.valueOf(f.format(hashMap.get("totalPerPerson"))));

        TextView tipPerPerson = (TextView) findViewById(R.id.tipPerPerson);
        tipPerPerson.setText("$" + String.valueOf(f.format(hashMap.get("tipPerPerson"))));

        TextView grantotalpp = (TextView) findViewById(R.id.grantotalpp);
        grantotalpp.setText("$" + String.valueOf(f.format(hashMap.get("grantotalpp"))));

        // button to reset to main activity so user can do another calculation
    }
    public void reset (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // add menu options

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
