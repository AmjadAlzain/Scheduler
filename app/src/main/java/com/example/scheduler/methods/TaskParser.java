package com.example.scheduler.methods;

import android.content.Context;

import java.io.*;
import java.util.*;

public class TaskParser {
    public static Task[] parseTasks(Context context, String filename) {
        Task[] tasks = new Task[22];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                tasks[index] = new Task(parts[0], parts[1], parts[2]);
                index++;
            }
            return tasks;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
