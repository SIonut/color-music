package saci.android.song;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;
import java.util.List;

import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.dtos.SongDto;
import saci.android.network.NetworkFragment;

/**
 * Created by Corina on 5/27/2017.
 */
public class SongDetails extends AppCompatActivity {

    private SongDto song;

    private NetworkFragment mNetworkFragment;

    private RadioButton mLikeRadioButton;
    private RadioButton mFollowRadioButton;
    private Button mAddToPlaylistButton;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_detailed);

        likeSongRadioButton();
        followPlaylistRadioButton();
        addToPlaylistButton();
        songWebFrame();

        mNetworkFragment = NetworkFragment.getInstance(R.string.base_link + "api/", this, null);

    }

    private void likeSongRadioButton() {
        mLikeRadioButton = (RadioButton) findViewById(R.id.like_radio);

        // TODO interrogate database
        String likePlaylistLink = R.string.base_link + "api/playlists/";

        mNetworkFragment.changeUrl(likePlaylistLink);
        mNetworkFragment.startDownload();

        mLikeRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLikeRadioButton.isChecked()) {
                    mLikeRadioButton.setChecked(false);
                    // TODO database update
                } else {
                    mLikeRadioButton.setChecked(true);
                    // TODO database update
                }
            }
        });
    }

    private void followPlaylistRadioButton() {
        mFollowRadioButton = (RadioButton) findViewById(R.id.follow_radio);

        // TODO interrogate database

        mFollowRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFollowRadioButton.isChecked()) {
                    mFollowRadioButton.setChecked(false);
                    // TODO database update
                } else {
                    mFollowRadioButton.setChecked(true);
                    // TODO database update
                }
            }
        });
    }

    private void addToPlaylistButton() {
        mAddToPlaylistButton = (Button) findViewById(R.id.add_to_palylist);

        mAddToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater popupLayoutInflater = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = popupLayoutInflater.inflate(R.layout.choose_playlist_popup, null);
                final PopupWindow choosePlaylistPopup = new PopupWindow(popupView,
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                // TOT interrogate database for user's playlists
                List<PlaylistDto> userPlaylists = new ArrayList<>();

                ArrayAdapter adapter = new ChoosePlaylistPopUpAdapter(popupView, userPlaylists);
                ListView playlistsList = (ListView) popupView.findViewById(R.id.popup_playlists);
                playlistsList.setAdapter(adapter);

                choosePlaylistPopup.showAsDropDown(mWebView);
            }
        });
    }

    private void songWebFrame() {
        song = (SongDto) getIntent().getSerializableExtra("song");
        // TODO interrogate database

        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420px\" height=\"315px\" " +
                "src=\" " + song.getLink() + "?autoplay=1&vq=small\" " +
                "frameborder=\"0\" allowfullscreen></iframe></body></html>";

        mWebView = (WebView) findViewById(R.id.videoView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadData(frameVideo, "text/html", "utf-8");
        mWebView.setWebChromeClient(new WebChromeClient());

    }
}
