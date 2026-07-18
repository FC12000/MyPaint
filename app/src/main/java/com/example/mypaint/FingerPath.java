package com.example.mypaint;

import android.graphics.Path;   // Can auto import the Path class via Preferences/Settings > Editor
                                // or hover over the class name and use the Ask popup
                                // https://www.dev2qa.com/how-to-auto-import-all-class-in-android-studio/
import android.graphics.PointF; // For saving points to redraw the paths
import java.util.ArrayList;     // For saving points in an ArrayList (the lines of the drawing)
import java.util.List;          // For saving the List of points ArrayLists (the drawing)


public class FingerPath {

    public int color;
    public boolean emboss;
    public boolean blur;
    public int strokeWidth;

    // Coordinate points list for re-tracing the path when viewing a drawing
    public List<PointF> points = new ArrayList<>();

    // Skipping Path serialization since the points list is already saved
    // Gson should ignore the Path object
    public transient Path path;

    // Class constructor generated via Code menu > Generate then select properties to use
    public FingerPath(int color, boolean emboss, boolean blur, int strokeWidth, Path path) {
        this.color = color;
        this.emboss = emboss;
        this.blur = blur;
        this.strokeWidth = strokeWidth;
        this.path = path;
        //this.points = new ArrayList<>();    // Initialize the points list moved to a public variable
    }
}
