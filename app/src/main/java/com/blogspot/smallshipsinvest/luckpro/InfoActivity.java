package com.blogspot.smallshipsinvest.luckpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_info);
        Typeface typeFaceKelly = Typeface.createFromAsset(getAssets(),"sketch.ttf");
        ((TextView) findViewById(R.id.info)).setTypeface(typeFaceKelly);
    }

    public void backToPlay(View view) {
        Helper.sound(this, R.raw.move);
        Intent openPlayActivity = new Intent(this, PlayActivity.class);
        openPlayActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openPlayActivity);
    }

}
