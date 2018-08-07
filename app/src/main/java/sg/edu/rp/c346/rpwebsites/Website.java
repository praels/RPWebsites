package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 16020552 on 7/8/2018.
 */

public class Website extends AppCompatActivity {

    WebView wbWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        wbWeb = findViewById(R.id.webViewWeb);

        Intent intentR = getIntent();
        String url = intentR.getStringExtra("Url");
        Log.i("Url", url + "");
        wbWeb.setWebViewClient(new WebViewClient());
        wbWeb.getSettings().setJavaScriptEnabled(true);
        wbWeb.getSettings().setBuiltInZoomControls(true);
        wbWeb.loadUrl(url);
    }
}