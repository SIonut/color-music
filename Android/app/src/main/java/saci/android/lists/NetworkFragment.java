package saci.android.lists;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Corina on 17.06.2017.
 */
public class NetworkFragment extends Fragment {

//    private static DownloadCallback mCallback;
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

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        int i = 0;
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        // Host Activity will handle callbacks from task.
////        mCallback = (DownloadCallback) context;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        // Clear reference to host Activity to avoid memory leak.
//        mCallback = null;
//    }
//
//    @Override
//    public void onDestroy() {
//        // Cancel task when Fragment is destroyed.
//        cancelDownload();
//        super.onDestroy();
//    }

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
