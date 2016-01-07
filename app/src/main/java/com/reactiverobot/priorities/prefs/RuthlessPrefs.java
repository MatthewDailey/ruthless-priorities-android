package com.reactiverobot.priorities.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class RuthlessPrefs {

    private static final String RUTHLESS_PREFS_FILE = "ruthless_prefs_file";
    private static final int DEFAULT_TOP_PRIORITIES_COUNT = 3;
    private static final String TOP_PRIORITIES_COUNT_PREF = "top_priorities_count_pref";
    private static final int DEFAULT_NOT_PRIORITIES_COUNT = 1;
    private static final String NOT_PRIORITIES_COUNT_PREF = "not_priorities_count_pref";

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
        return getRuthlessPrefs().getInt(TOP_PRIORITIES_COUNT_PREF,
                DEFAULT_TOP_PRIORITIES_COUNT);
    }

    public void setTopPrioritiesCount(int topPrioritiesCount) {
        getRuthlessPrefs().edit().putInt(TOP_PRIORITIES_COUNT_PREF, topPrioritiesCount).apply();
    }

    public int getNotPrioritiesCount() {
        return getRuthlessPrefs().getInt(NOT_PRIORITIES_COUNT_PREF,
                DEFAULT_NOT_PRIORITIES_COUNT);
    }

    public void setNotPrioritiesCount(int notPrioritiesCount) {
        getRuthlessPrefs().edit().putInt(NOT_PRIORITIES_COUNT_PREF, notPrioritiesCount).apply();
    }
}
