package saci.android;

import android.app.Application;

import saci.android.network.NetworkFragment;

/**
 * Created by corina on 25.06.2017.
 */
public class ColorMusic extends Application {

    private NetworkFragment networkFragment;

    @Override
    public void onCreate() {
        System.out.print("LALALA\n");

        super.onCreate();
    }
}
