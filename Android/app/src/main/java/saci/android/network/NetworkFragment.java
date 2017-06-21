package saci.android.network;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Corina on 17.06.2017.
 */
public class NetworkFragment extends Fragment {

    private DownloadTask mDownloadTask;
    private static Context mContext;
    private static String mUrl;

    /**
     * Static initializer for NetworkFragment that sets the URL of the host it will be downloading
     * from.
     */
    public static NetworkFragment getInstance(String url, Context context) {
        NetworkFragment networkFragment = new NetworkFragment();
        mContext = context;
        mUrl = url;
        return networkFragment;
    }

    /**
     * Start non-blocking execution of DownloadTask.
     */
    public void startDownload() {
        cancelDownload();
        mDownloadTask = new DownloadTask(mContext);
        mDownloadTask.execute(mUrl);
    }

    /**
     * Cancel (and interrupt if necessary) any ongoing DownloadTask execution.
     */
    public void cancelDownload() {
        if (mDownloadTask != null) {
            mDownloadTask.cancel(true);
        }
    }

}
