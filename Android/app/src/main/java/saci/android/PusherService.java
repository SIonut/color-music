package saci.android;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;

import com.pusher.client.channel.SubscriptionEventListener;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by corina on 28.06.2017.
 */
public class PusherService {

    private Pusher pusher;
    private Channel channel;
    private Context context;

    public PusherService(final Context appContext) {
        context = appContext;
        PusherOptions options = new PusherOptions();
        options.setCluster("eu");
        pusher = new Pusher("3d9ffe05647a7cbf2063", options);

        channel = pusher.subscribe(ColorMusicApplication.getSharedPreferences().getString(CustomPreferences
                .USER_ID, ""));

        channel.bind("like", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                Looper.prepare();
                showNotification("New follower", data);
                System.out.println(data);
            }
        });

        channel.bind("follow", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                Looper.prepare();
                showNotification("New song in playlist", data);
                System.out.println(data);
            }
        });

        pusher.connect();
    }

    private void showNotification(String event, String data) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.abc_seekbar_tick_mark_material)
                .setContentTitle(event)
                .setContentText(data);
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1, mBuilder.build());
    }

}
