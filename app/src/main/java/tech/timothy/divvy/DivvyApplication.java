package tech.timothy.divvy;

import android.app.Application;
import android.content.Context;

/**
 * Created by timtan on 8/13/17.
 */

public class DivvyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        if (context == null) {
            throw new RuntimeException("DivvyApplication was never initialized. Context was null.");
        }

        return context;
    }
}
