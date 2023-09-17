package com.blogspot.smallshipsinvest.luckpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    public final int MESSAGE_FLASH_DURATION = 300;
    public final int MESSAGE_FLASH_TIMES = 3;
    public final int FINISH_MESSAGE_FLASH_TIMES = 6;
    public final boolean MESSAGE_OFF = false;
    public final int LOSSWIN_FLASH_DURATION = 650;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_play);

        Typeface typeFaceDollar = Typeface.createFromAsset(getAssets(),"5dollar.ttf");
        ((TextView) findViewById(R.id.capital)).setTypeface(typeFaceDollar);
        ((TextView) findViewById(R.id.loss_win)).setTypeface(typeFaceDollar);

        Typeface typeFaceSketch = Typeface.createFromAsset(getAssets(),"sketch.ttf");
        ((TextView) findViewById(R.id.levelMessage)).setTypeface(typeFaceSketch);

        Helper.showCapital(findViewById(R.id.capital));
        showReachedLevels();
        showLevelMessage(Hero.level);
    }

    public void playRoulette(View view) {
//        Helper.sound(this, R.raw.move);
        if (Math.random() < Hero.rouletteChance) {
            Hero.capital += Hero.rouletteProfit;
            Helper.showCapital(findViewById(R.id.capital));

            if (calcLevel() > Hero.level) {
                Hero.level = calcLevel();
                showLevel(Hero.level);
                flashLevelMessage(Hero.level);
                Helper.sound(this, R.raw.win);
            }

        } else {
            Hero.capital -= Hero.rouletteLoss;
            Helper.showCapital(findViewById(R.id.capital));
            ((TextView) findViewById(R.id.loss_win)).setTextColor(ContextCompat.getColor(this, R
                    .color.colorRed));
            Helper.flash(findViewById(R.id.loss_win), "-" + Hero.rouletteLoss + " ",
                    LOSSWIN_FLASH_DURATION);
            if (Hero.capital > 0) Helper.sound(this, R.raw.loss);
            if (Hero.capital <= 0) startActivity(new Intent(this, FinishActivity.class));
        }
    }

    public void playPoker(View view) {
//        Helper.sound(this,R.raw.move);
        if (Math.random() < 1 - Hero.pokerChance) {
            Hero.capital -= Hero.pokerLoss;
            Helper.showCapital(findViewById(R.id.capital));
            if (Hero.capital <= 0) startActivity(new Intent(this, FinishActivity.class));
        } else {
            Hero.capital += Hero.pokerProfit;
            Helper.showCapital(findViewById(R.id.capital));
            ((TextView) findViewById(R.id.loss_win)).setTextColor(ContextCompat.getColor(this, R
                    .color.colorMoney));
            Helper.flash(findViewById(R.id.loss_win), "+" + Hero.pokerProfit + " ",
                    LOSSWIN_FLASH_DURATION);
            if (Hero.capital < Hero.UNBELIEVABLE_CAP) Helper.sound(this, R.raw.profit);

            if (calcLevel() > Hero.level) {
                Hero.level = calcLevel();
                showLevel(Hero.level);
                flashLevelMessage(Hero.level);
                Helper.sound(this, R.raw.win);
            }

        }
    }

    public void callInfo(View view) {
        Helper.sound(this, R.raw.move);
        startActivity(new Intent(this, InfoActivity.class));
    }

    public void callFinish(View view) {
        Helper.sound(this, R.raw.move);
        startActivity(new Intent(this, FinishActivity.class));
    }

    public void showReachedLevels() {
        if (Hero.level > 0) showLevel(1);
        if (Hero.level > 1) showLevel(2);
        if (Hero.level > 2) showLevel(3);
        if (Hero.level > 3) showLevel(4);
        if (Hero.level > 4) showLevel(5);
    }

    public void showLevel(int level) {
        if (level == 1) ((ImageView) findViewById(R.id.fifty)).setImageResource(R
                .drawable.fifty_normal);
        if (level == 2) ((ImageView) findViewById(R.id.fair)).setImageResource(R
                .drawable.fair_button);
        if (level == 3) ((ImageView) findViewById(R.id.exceptional)).setImageResource(R
                .drawable.exceptional_normal);
        if (level == 4) ((ImageView) findViewById(R.id.incredible)).setImageResource(R
                .drawable.incredible_normal);
        if (level == 5) ((ImageView) findViewById(R.id.unbelievable)).setImageResource(R
                .drawable.unbelievable_normal);
    }

    public int calcLevel() {
        int level = 0;
        if (Hero.capital >= Hero.FIFTY_CAP) level = 1;
        if (Hero.capital >= Hero.FAIR_CAP) level = 2;
        if (Hero.capital >= Hero.EXCEPTIONAL_CAP) level = 3;
        if (Hero.capital >= Hero.INCREDIBLE_CAP) level = 4;
        if (Hero.capital >= Hero.UNBELIEVABLE_CAP) level = 5;
        return level;
    }

    public void flashLevelMessage(int level) {
        if (level == 1) {
            Helper.flash(findViewById(R.id.levelMessage), "You have a chance today!",
                    MESSAGE_FLASH_TIMES, MESSAGE_FLASH_DURATION, MESSAGE_OFF);
        }
        if (level == 2) {
            Helper.flash(findViewById(R.id.levelMessage), "You are\nfairly lucky\ntoday!!",
                    MESSAGE_FLASH_TIMES, MESSAGE_FLASH_DURATION, MESSAGE_OFF);
        }
        if (level == 3) {
            Helper.flash(findViewById(R.id.levelMessage), "You are\nEXCEPTIONALLY\nlucky today!!!",
                    MESSAGE_FLASH_TIMES, MESSAGE_FLASH_DURATION, MESSAGE_OFF);
        }
        if (level == 4) {
            Helper.flash(findViewById(R.id.levelMessage), "You are\nINCREDIBLY\nlucky " +
                            "today!!!!",
                    MESSAGE_FLASH_TIMES, MESSAGE_FLASH_DURATION, MESSAGE_OFF);
        }
        if (level == 5) {
            Helper.flash(findViewById(R.id.levelMessage), "You " +
                            "are\n" + "UNBELIEVABLY\nlucky today!!!!!",
                    MESSAGE_FLASH_TIMES, MESSAGE_FLASH_DURATION, MESSAGE_OFF);
            Helper.flash(findViewById(R.id.pressFinish), "Press FINISH to start over",
                    FINISH_MESSAGE_FLASH_TIMES, MESSAGE_FLASH_DURATION, MESSAGE_OFF);
        }
    }

    public void showLevelMessage(int level) {
        if (level == 1) {
            ((TextView)findViewById(R.id.levelMessage)).setText("You have a chance today!");
        }
        if (level == 2) {
            ((TextView)findViewById(R.id.levelMessage)).setText("You are\nfairly lucky\ntoday!!");
        }
        if (level == 3) {
            ((TextView)findViewById(R.id.levelMessage)).setText("You are\nEXCEPTIONALLY\nlucky " +
                    "today!!!");
        }
        if (level == 4) {
            ((TextView)findViewById(R.id.levelMessage)).setText("You are\nINCREDIBLY\nlucky " +
                    "today!!!!");
        }
        if (level == 5) {
            ((TextView)findViewById(R.id.levelMessage)).setText("You are\nUNBELIEVABLY\nlucky " +
                    "today!!!!!");
            ((TextView)findViewById(R.id.pressFinish)).setText("Press FINISH to start over");
        }
    }

}
