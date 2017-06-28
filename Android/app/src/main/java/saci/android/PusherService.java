package saci.android;

import android.content.Context;
import android.widget.Toast;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;

import com.pusher.client.channel.SubscriptionEventListener;

/**
 * Created by corina on 28.06.2017.
 */
public class PusherService {

    public PusherService(final Context appContext) {
        PusherOptions options = new PusherOptions();
        options.setCluster("eu");
        Pusher pusher = new Pusher("3d9ffe05647a7cbf2063", options);

        Channel channel = pusher.subscribe(CustomPreferences.USER_ID);

        channel.bind("like", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                Toast.makeText(appContext, data, Toast.LENGTH_LONG).show();
                System.out.println(data);
            }
        });

        channel.bind("follow", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                Toast.makeText(appContext, data, Toast.LENGTH_LONG).show();
                System.out.println(data);
            }
        });

        pusher.connect();
    }

}
