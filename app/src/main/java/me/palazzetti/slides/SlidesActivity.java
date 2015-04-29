package me.palazzetti.slides;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.thalmic.myo.Hub;
import com.thalmic.myo.scanner.ScanActivity;

import me.palazzetti.slides.Constants.Actions;
import me.palazzetti.slides.controller.Reveal;
import me.palazzetti.slides.myo.DeviceListener;
import me.palazzetti.slides.myo.GestureRecognition;

public class SlidesActivity extends AppCompatActivity implements GestureRecognition {
    private WebView mReveal;
    private Hub mHub;

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

        // it's the cool stuff
        myoInitialization();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_slides, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_pairing:
                myoPairing();
                break;
            case R.id.action_overview:
                mReveal.evaluateJavascript(Reveal.overview(), null);
                break;
            case R.id.action_previous:
                mReveal.evaluateJavascript(Reveal.previousSlide(), null);
                break;
            case R.id.action_next:
                mReveal.evaluateJavascript(Reveal.nextSlide(), null);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPoseRecognition(Actions action) {
        switch (action) {
            case PREVIOUS:
                mReveal.evaluateJavascript(Reveal.previousSlide(), null);
            case NEXT:
                mReveal.evaluateJavascript(Reveal.nextSlide(), null);
        }
    }

    private void myoInitialization() {
        // Hub initialization (manages Myo instances)
        mHub = Hub.getInstance();
        boolean status = mHub.init(this);

        // checks Hub initialization (fails if the system doesn't support Bluetooth Low Energy)
        if (!status) {
            Toast.makeText(this, "Bluetooth Low Energy not available. Aborting.", Toast.LENGTH_LONG).show();
        } else {
            // prevents statistics data to be sent
            mHub.setSendUsageData(false);
            mHub.addListener(new DeviceListener(this));
        }
    }

    private void myoPairing() {
        // uses the ScanActivity class to pair the Myo device
        Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
        startActivity(intent);
    }
}
