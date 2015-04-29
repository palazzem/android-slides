package me.palazzetti.slides;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SlidesActivity extends AppCompatActivity {
    private WebView mReveal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides);

        mReveal = (WebView) findViewById(R.id.reveal);

        // enable Javascript
        WebSettings webSettings = mReveal.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // load the slides
        mReveal.setWebViewClient(new WebViewClient());
        mReveal.loadUrl("http://lab.hakim.se/reveal-js");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_slides, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
