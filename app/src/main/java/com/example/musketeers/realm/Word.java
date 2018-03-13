package com.example.musketeers.realm;

/**
 * Created by RajPrabhakar on 13-03-2018.
 */

public class Word {
    private String wAppliance;
    private boolean wRun;
    private int wImage = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String appliance, boolean run, int image) {
        wAppliance = appliance;
        wRun = run;
        wImage = image;
    }

    public String getwAppliance() {
        return wAppliance;
    }

    public boolean getwRun() {
        return wRun;
    }

    public int getwImage() {
        return wImage;
    }
}
