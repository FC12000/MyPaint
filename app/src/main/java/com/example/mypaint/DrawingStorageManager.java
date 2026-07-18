package com.example.mypaint;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DrawingStorageManager {
    private static final String FILE_NAME = "drawing_data.json";

    // Save the list of FingerPaths to JSON
    public static void saveDrawing(Context context, ArrayList<FingerPath> paths) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(paths);

        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the list of FingerPaths from JSON
    public static ArrayList<FingerPath> loadDrawing(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<FingerPath>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
