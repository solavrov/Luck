package com.blogspot.smallshipsinvest.luckpro;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Helper {

    public static void sound(Context context, int sound) {
        final MediaPlayer mp = MediaPlayer.create(context, sound);
        mp.start();
        Handler handler = new Handler();
        Runnable run = new Runnable() {
            public void run() {
                mp.release();
            }
        };
        handler.postDelayed(run, mp.getDuration());
    }

    public static void flash(View where, String what, int period) {
        final TextView textView = (TextView) where;
        textView.setText(what);
        Handler handler = new Handler();
        Runnable run = new Runnable() {
            public void run() {
                textView.setText("");
            }
        };
        handler.postDelayed(run, period);
    }

    public static void flash(View where, final String what, int times, int period, boolean off) {
        final TextView textView = (TextView) where;
        Runnable runOn = new Runnable() {
            public void run() {
                textView.setText(what);
            }
        };
        Runnable runOff = new Runnable() {
            public void run() {
                textView.setText("");
            }
        };
        Handler handler = new Handler();
        for (int i = 0; i < times; i++) {
            handler.postDelayed(runOn, 2 * i * period);
            if (i < times - 1 || off) handler.postDelayed(runOff, (2 * i + 1) * period);
        }
    }

    public static void showCapital(View where) {
        DecimalFormat currency = new DecimalFormat("###,###");
        TextView textView = (TextView) where;
        textView.setText(currency.format(Hero.capital));
    }

}
