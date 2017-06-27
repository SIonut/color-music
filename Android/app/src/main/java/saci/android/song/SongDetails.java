package saci.android.song;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saci.android.ColorMusicApplication;
import saci.android.CustomPreferences;
import saci.android.R;
import saci.android.dtos.SongDto;
import saci.android.network.RestClient;
import saci.android.network.SongsApi;
import saci.android.playlists.NewPlaylist;
import saci.android.playlists.PlaylistsListActivity;

/**
 * Created by Corina on 5/27/2017.
 */
public class SongDetails extends AppCompatActivity {

    private SongDto song;

    private CheckBox mLikeCheckBox;
    private Button mAddToPlaylistButton;
    private Button mNewPlaylistButton;
    private WebView mWebView;

    private SongsApi songsApi;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_detailed);

        songsApi = RestClient.getClient().create(SongsApi.class);

        song = (SongDto) getIntent().getSerializableExtra("song");

        SharedPreferences preferences = ColorMusicApplication.getSharedPreferences();
        userId = preferences.getString(CustomPreferences.USER_ID, "");

        likeSongRadioButton();
        addToPlaylistButton();
        createNewPlaylistButton();
        songWebFrame();

    }

    private void likeSongRadioButton() {
        mLikeCheckBox = (CheckBox) findViewById(R.id.like_radio);

        songsApi.isLiked(userId, song.getId()).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                mLikeCheckBox.setChecked(response.body().booleanValue());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

        mLikeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!mLikeCheckBox.isChecked()) {
                    songsApi.deleteFromLikes(userId, song.getId()).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    });
                } else {
                    songsApi.addToLikes(userId, song.getId()).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private void addToPlaylistButton() {
        mAddToPlaylistButton = (Button) findViewById(R.id.add_to_palylist);

        mAddToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlistIntent = new Intent(SongDetails.this, PlaylistsListActivity.class);
                playlistIntent.putExtra("addToPlaylist", true);
                playlistIntent.putExtra("song", song);
                startActivity(playlistIntent);
            }
        });
    }

    private void createNewPlaylistButton() {
        mNewPlaylistButton = (Button) findViewById(R.id.create_new_playlist);

        mNewPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPlaylistIntent = new Intent(SongDetails.this, NewPlaylist.class);
                startActivity(newPlaylistIntent);
            }
        });
    }

    private void songWebFrame() {
        song = (SongDto) getIntent().getSerializableExtra("song");


    String frameVideo =
            "<html>" +
                    "<body>" +
                    "<iframe width=\"100%\" height=\"100%\" src=\"" + song.getLink() + "?autoplay=1&vq=small\" " +
                    "frameborder=\"0\" allowfullscreen>" +
                    "</iframe>" +
                    "</body>" +
                    "</html>";

        mWebView = (WebView) findViewById(R.id.videoView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadData(frameVideo, "text/html", "utf-8");
        mWebView.setWebChromeClient(new WebChromeClient());

    }
}