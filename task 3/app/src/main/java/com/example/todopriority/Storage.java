package com.example.todopriority;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String PREFS = "todo_prefs";
    private static final String KEY_TASKS = "tasks";

    private final SharedPreferences prefs;
    private final Gson gson = new Gson();

    public Storage(Context context) {
        prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public List<Task> load() {
        String json = prefs.getString(KEY_TASKS, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<ArrayList<Task>>(){}.getType();
        List<Task> list = gson.fromJson(json, type);
        return list != null ? list : new ArrayList<>();
    }

    public void save(List<Task> tasks) {
        prefs.edit().putString(KEY_TASKS, gson.toJson(tasks)).apply();
    }
}
