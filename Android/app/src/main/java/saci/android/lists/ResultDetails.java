package saci.android.lists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.VideoView;

import saci.android.R;

/**
 * Created by Corina on 5/27/2017.
 */
public class ResultDetails extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_detailed);

        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/" + "PIhIc_ehSdU" + "?autoplay=1&vq=small\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        WebView mWebView = (WebView) findViewById(R.id.videoView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadData(frameVideo, "text/html", "utf-8");
        mWebView.setWebChromeClient(new WebChromeClient());

    }
}
