package saci.android.network;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import saci.android.music.ColorMusicResultActivity;

/**
 * Created by corina on 17.06.2017.
 */
public class DownloadTask extends AsyncTask<String, Void, String> {

    private final Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    /**
     * Cancel background network operation if we do not have network connectivity.
     */
    @Override
    protected void onPreExecute() {
    }

    /**
     * Defines work to perform on the background thread.
     */
    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        if (!isCancelled() && urls != null && urls.length > 0) {
            String urlString = urls[0];
            try {
                URL url = new URL(urlString);
                String resultString = downloadUrl(url);
                if (resultString != null) {
                    result = resultString;
                } else {
                    throw new IOException("No response received.");
                }
            } catch(Exception e) {
                result = e.toString();
            }
        }
        return result;
    }

    /**
     * Updates the DownloadCallback with the result.
     */
    @Override
    protected void onPostExecute(String result) {
        if (result != "") {
            Intent findIntent = new Intent(context, ColorMusicResultActivity.class);
            findIntent.putExtra("songs", result);
            context.startActivity(findIntent);
        }
    }

    /**
     * Override to add special behavior for cancelled AsyncTask.
     */
    @Override
    protected void onCancelled(String result) {
    }

    private String downloadUrl(URL url) throws IOException {
        String result = null;
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in, 5000);
        } finally {
            urlConnection.disconnect();
        }

        return result;
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    private String readStream(InputStream stream, int maxLength) throws IOException {

        String result = null;
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[maxLength];

        int numChars = 0;
        int readSize = 0;

        while (numChars < maxLength && readSize != -1) {
            numChars += readSize;
            readSize = reader.read(buffer, numChars, buffer.length - numChars);
        }
        if (numChars != -1) {
            numChars = Math.min(numChars, maxLength);
            result = new String(buffer, 0, numChars);
        }

        return result;
    }
}
