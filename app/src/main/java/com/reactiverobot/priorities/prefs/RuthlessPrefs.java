package com.reactiverobot.priorities.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.roboguice.shaded.goole.common.collect.Lists;

import java.util.List;

public class RuthlessPrefs {

    private static final String RUTHLESS_PREFS_FILE = "ruthless_prefs_file";

    private static final String TOP_PRIORITIES_COUNT_PREF = "top_priorities_count_pref";
    private static final String NOT_PRIORITIES_COUNT_PREF = "not_priorities_count_pref";
    private static final String TOP_PRIORITIES = "top_priorities";
    private static final String NOT_PRIORITIES = "not_priorities";
    private static final String IS_FIRST_STARTUP = "is_first_startup";

    private final Context context;

    public RuthlessPrefs(Context context) {
        this.context = context;
    }

    public static RuthlessPrefs fromContext(Context context) {
        return new RuthlessPrefs(context);
    }

    private SharedPreferences getRuthlessPrefs() {
        return context.getSharedPreferences(RUTHLESS_PREFS_FILE, Context.MODE_PRIVATE);
    }

    public int getTopPrioritiesCount() {
        return getRuthlessPrefs().getInt(TOP_PRIORITIES_COUNT_PREF, 3);
    }

    public void setTopPrioritiesCount(int topPrioritiesCount) {
        getRuthlessPrefs().edit().putInt(TOP_PRIORITIES_COUNT_PREF, topPrioritiesCount).apply();
    }

    public int getNotPrioritiesCount() {
        return getRuthlessPrefs().getInt(NOT_PRIORITIES_COUNT_PREF, 1);
    }

    public void setNotPrioritiesCount(int notPrioritiesCount) {
        getRuthlessPrefs().edit().putInt(NOT_PRIORITIES_COUNT_PREF, notPrioritiesCount).apply();
    }

    public List<String> getTopPriorities() {
        String topPrioritiesJson = getRuthlessPrefs().getString(TOP_PRIORITIES, "");
        return parseJsonListOfString(topPrioritiesJson);
    }

    public void setTopPriorities(List<String> topPriorities) {
        Gson gson = new Gson();
        getRuthlessPrefs().edit().putString(TOP_PRIORITIES, gson.toJson(topPriorities)).apply();
    }

    public List<String> getNotPriorities() {
        String notPrioritiesJson = getRuthlessPrefs().getString(NOT_PRIORITIES, "");
        return parseJsonListOfString(notPrioritiesJson);
    }

    public void setNotPriorities(List<String> notPriorities) {
        Gson gson = new Gson();
        getRuthlessPrefs().edit().putString(NOT_PRIORITIES, gson.toJson(notPriorities)).apply();
    }

    private List<String> parseJsonListOfString(String json) {
        if (json.isEmpty()) {
            return Lists.newArrayList();
        } else {
            Gson gson = new Gson();
            return gson.fromJson(json, new TypeToken<List<String>>(){}.getType());
        }
    }

    public boolean isFirstStartUp() {
        return getRuthlessPrefs().getBoolean(IS_FIRST_STARTUP, true);
    }

    public void hasStartedUp() {
        getRuthlessPrefs().edit().putBoolean(IS_FIRST_STARTUP, false).apply();
    }
}
