package com.blogspot.smallshipsinvest.luckpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    public final int CONGRATS_FLASH_DURATION = 300;
    public final int CONGRATS_FLASH_TIMES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_finish);
        Typeface typeFaceSketch = Typeface.createFromAsset(getAssets(),"sketch.ttf");
        ((TextView) findViewById(R.id.finishMessage)).setTypeface(typeFaceSketch);

        switch (Hero.level) {

            case 0:
                Helper.sound(this, R.raw.bankrupt);
                ((ImageButton)findViewById(R.id.finishCoin)).setImageResource(R.drawable
                        .lose_button);
                Helper.flash(findViewById(R.id.finishMessage), "It looks like...\nit isn\'t your" +
                        " day" +
                                " today...", CONGRATS_FLASH_TIMES, CONGRATS_FLASH_DURATION, false);
                break;

            case 1:
                Helper.sound(this, R.raw.yes);
                ((ImageButton)findViewById(R.id.finishCoin)).setImageResource(R.drawable
                        .fifty_button);
                Helper.flash(findViewById(R.id.finishMessage), "You have a chance today!",
                        CONGRATS_FLASH_TIMES, CONGRATS_FLASH_DURATION, false);
                break;

            case 2:
                Helper.sound(this, R.raw.yes);
                ((ImageButton)findViewById(R.id.finishCoin)).setImageResource(R.drawable
                        .fair_button);
                Helper.flash(findViewById(R.id.finishMessage), "You are\nfairly lucky\ntoday!!",
                        CONGRATS_FLASH_TIMES, CONGRATS_FLASH_DURATION, false);
                break;

            case 3:
                Helper.sound(this, R.raw.yes);
                ((ImageButton)findViewById(R.id.finishCoin)).setImageResource(R.drawable
                        .exceptional_button);
                Helper.flash(findViewById(R.id.finishMessage), "You are\nEXCEPTIONALLY\nlucky " +
                                "today!!!", CONGRATS_FLASH_TIMES, CONGRATS_FLASH_DURATION, false);
                break;

            case 4:
                Helper.sound(this, R.raw.yes);
                ((ImageButton)findViewById(R.id.finishCoin)).setImageResource(R.drawable
                        .incredible_button);
                Helper.flash(findViewById(R.id.finishMessage), "You are\nINCREDIBLY\nlucky " +
                                "today!!!!", CONGRATS_FLASH_TIMES, CONGRATS_FLASH_DURATION, false);
                break;

            case 5:
                Helper.sound(this, R.raw.yes);
                ((ImageButton)findViewById(R.id.finishCoin)).setImageResource(R.drawable
                        .unbelievable_button);
                Helper.flash(findViewById(R.id.finishMessage), "You are\nUNBELIEVABLY\nlucky " +
                                "today!!!!!", CONGRATS_FLASH_TIMES, CONGRATS_FLASH_DURATION, false);
                break;

            default:
                break;

        }

        Hero.capital = Hero.INITIAL_CAP;
        Hero.level = Hero.INITIAL_LEVEL;

    }

    public void backToPlay(View view) {
        Helper.sound(this, R.raw.move);
        Intent openPlayActivity = new Intent(this, PlayActivity.class);
        openPlayActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openPlayActivity);
    }

}
