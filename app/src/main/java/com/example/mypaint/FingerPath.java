package com.example.mypaint;

import android.graphics.Path;   // Can auto import the Path class via Preferences/Settings > Editor
                                // or hover over the class name and use the Ask popup
                                // https://www.dev2qa.com/how-to-auto-import-all-class-in-android-studio/

public class FingerPath {

    public int color;
    public boolean emboss;
    public boolean blue;
    public int strokeWidth;
    public Path path;

    // Class constructor generated via Code menu > Generate then select properties to use
    public FingerPath(int color, boolean emboss, boolean blue, int strokeWidth, Path path) {
        this.color = color;
        this.emboss = emboss;
        this.blue = blue;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}
