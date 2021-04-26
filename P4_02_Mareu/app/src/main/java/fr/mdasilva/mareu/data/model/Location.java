package fr.mdasilva.mareu.data.model;

import android.text.TextUtils;

import androidx.annotation.ColorRes;

public class Location {

    @ColorRes
    private int color;
    private String name;

    /**
     * Constructor
     * @param color
     * @param name
     */
    public Location(@ColorRes int color, String name){
        this.color = color;
        this.name = name;
    }

    @ColorRes
    public int getColor() {
        return  color;
    }

    public void setColor(@ColorRes int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
