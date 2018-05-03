package com.codecool.klondike;

/**
 * Created by zsolt on 2018.05.03..
 */
public enum Suit{
    HEARTS(1), DIAMONDS(2), SPADES(3), CLUBS(4);

    private int value;

    Suit(int value){
        this.value=value;
    }
    public int getValue(){
        return this.value;
    }
}
