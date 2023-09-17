package com.blogspot.smallshipsinvest.luckpro;

public class Hero {

    public static final int INITIAL_CAP = 100;
    public static final int INITIAL_LEVEL = 0;

    public static final int FIFTY_CAP = 200;
    public static final int FAIR_CAP = 300;
    public static final int EXCEPTIONAL_CAP = 500;
    public static final int INCREDIBLE_CAP = 700;
    public static final int UNBELIEVABLE_CAP = 1000;

    public static final double INIT_ROULETTE_CHANCE = 0.9d;
    public static final int INIT_ROULETTE_PROFIT = 10;
    public static final int INIT_ROULETTE_LOSS = 110;

    public static final double INIT_POKER_CHANCE = 0.1d;
    public static final int INIT_POKER_PROFIT = 80;
    public static final int INIT_POKER_LOSS = 10;

    public static double rouletteChance = INIT_ROULETTE_CHANCE;
    public static int rouletteProfit = INIT_ROULETTE_PROFIT;
    public static int rouletteLoss = INIT_ROULETTE_LOSS;

    public static double pokerChance = INIT_POKER_CHANCE;
    public static int pokerProfit = INIT_POKER_PROFIT;
    public static int pokerLoss = INIT_POKER_LOSS;

    public static double capital = INITIAL_CAP;
    public static int level = INITIAL_LEVEL;

}
