package com.example.nasko.assignment1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Window;

import static android.R.attr.description;
import static com.example.nasko.assignment1.R.id.theweb;
import static com.example.nasko.assignment1.R.layout.activity_webview;

public class webviewActivity extends AppCompatActivity {

        // grab URL from the freeform field and send user to it

    public void showPage (View view){

        // grab value from field
        EditText editText1 = (EditText) findViewById(R.id.webaddress);
        String url = (editText1.getText()).toString();

        WebView webview = (WebView)findViewById(R.id.theweb);
        webview.loadUrl("http://" + url);

    }

    // since the only way to go back to the main page is the back button, override onbackpressed to set a status code

    public void onBackPressed() {

        EditText editText1 = (EditText) findViewById(R.id.webaddress);
        String url = (editText1.getText()).toString();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", url);

        // set to not ok if the user didn't enter a URL into the bar and just navigated back
        if ((url != null) && !url.isEmpty()) {
            setResult(Activity.RESULT_OK, returnIntent);
        }
        else
            setResult(Activity.RESULT_CANCELED, returnIntent);

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_webview);
    }
}
