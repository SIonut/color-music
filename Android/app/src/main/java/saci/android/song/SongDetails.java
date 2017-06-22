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
import saci.android.dtos.Playlist;
import saci.android.dtos.Song;

/**
 * Created by Corina on 5/27/2017.
 */
public class SongDetails extends AppCompatActivity {

    private Song song;

    private RadioButton likeRadioButton;
    private RadioButton followRadioButton;
    private Button addToPlaylistButton;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_detailed);

        likeRadioButton = (RadioButton) findViewById(R.id.like_radio);
        followRadioButton = (RadioButton) findViewById(R.id.follow_radio);
        addToPlaylistButton = (Button) findViewById(R.id.add_to_palylist);

        initializeRadioButtons();

        song = (Song) getIntent().getSerializableExtra("song");
        // TODO interrogate database

        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420px\" height=\"315px\" " +
                "src=\" " + song.getLink() + "?autoplay=1&vq=small\" " +
                "frameborder=\"0\" allowfullscreen></iframe></body></html>";

        mWebView = (WebView) findViewById(R.id.videoView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadData(frameVideo, "text/html", "utf-8");
        mWebView.setWebChromeClient(new WebChromeClient());

    }

    private void initializeRadioButtons() {
        // TODO interrogate database

        likeRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (likeRadioButton.isChecked()) {
                    likeRadioButton.setChecked(false);
                    // TODO database update
                } else {
                    likeRadioButton.setChecked(true);
                    // TODO database update
                }
            }
        });

        followRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (followRadioButton.isChecked()) {
                    followRadioButton.setChecked(false);
                    // TODO database update
                } else {
                    followRadioButton.setChecked(true);
                    // TODO database update
                }
            }
        });

        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater popupLayoutInflater = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = popupLayoutInflater.inflate(R.layout.choose_playlist_popup, null);
                final PopupWindow choosePlaylistPopup = new PopupWindow(popupView,
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                // TOT interrogate database for user's playlists
                List<Playlist> userPlaylists = new ArrayList<>();

                ArrayAdapter adapter = new ChoosePlaylistPopUpAdapter(popupView, userPlaylists);
                ListView playlistsList = (ListView) popupView.findViewById(R.id.popup_playlists);
                playlistsList.setAdapter(adapter);

                choosePlaylistPopup.showAsDropDown(mWebView);
            }
        });
    }
}
