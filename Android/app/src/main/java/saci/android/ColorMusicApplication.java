package saci.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Ion, 6/27/2017.
 */

public class ColorMusicApplication extends Application {

    private static Context context;
    private static PusherService pusherService;

    public void onCreate() {
        super.onCreate();
        ColorMusicApplication.context = getApplicationContext();
        pusherService = new PusherService(context.getApplicationContext());
    }

    public static Context getAppContext() {
        return ColorMusicApplication.context;
    }

    public static SharedPreferences getSharedPreferences() {
        ColorMusicApplication.getAppContext().getSharedPreferences("saci.android", MODE_PRIVATE);
        return ColorMusicApplication.context.getSharedPreferences("saci.android", MODE_PRIVATE);
    }
}
