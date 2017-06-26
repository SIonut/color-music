package saci.android.register;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import saci.android.R;
import saci.android.colors.ColorsActivity;
import saci.android.network.NetworkFragment;

/**
 * Created by Corina on 5/25/2017.
 */
public class RegisterAccountController {

    private NetworkFragment mNetworkFragment;
    private final Context mContext;
    private JSONObject mCredentials;

    public RegisterAccountController(Context context, JSONObject credentials) {
        this.mContext = context;
        this.mCredentials = credentials;
    }

    public boolean createAccount() {

        String registerLink = R.string.base_link + "api/users/register/" + mCredentials.toString();
        mNetworkFragment = NetworkFragment.getInstance(registerLink, mContext, ColorsActivity.class);
        mNetworkFragment.startDownload();

        String link = R.string.base_link + "api/playlists/";
        JSONObject likePlaylist = new JSONObject();

        try {
            likePlaylist.put("userId", "");
            likePlaylist.put("name", "Liked");
            likePlaylist.put("songs", new JSONArray());

            String playlistLink = link + likePlaylist.toString();
            mNetworkFragment.changeUrl(playlistLink);
            mNetworkFragment.startDownload();

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
