package saci.backend.pusher;

import com.pusher.rest.Pusher;
import org.springframework.stereotype.Service;

/**
 * @author Stănilă Ioan, 6/28/2017.
 */
@Service
public class PusherService {

    public static final String EVENT_LIKE = "like";
    public static final String EVENT_FOLLOW = "follow";

    private static final String APP_ID = "359870";
    private static final String KEY = "3d9ffe05647a7cbf2063";
    private static final String SECRET = "bc3dc004e669835e8d55";
    private static final String CLUSTER = "eu";

    private final Pusher pusher;

    public PusherService() {
        pusher = new Pusher(APP_ID, KEY, SECRET);
        pusher.setCluster(CLUSTER);
        pusher.setEncrypted(true);
    }

    public void emit(String channel, String event, String data) {
        pusher.trigger(channel, event, data);
    }
}
