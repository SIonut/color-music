package saci.android.song;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.dtos.SongDto;
import saci.android.network.SongsApi;

/**
 * Created by Corina on 5/27/2017.
 */
public class SongDetails extends AppCompatActivity {

    private SongDto song;

    private CheckBox mLikeCheckBox;
    private Button mAddToPlaylistButton;
    private WebView mWebView;

    private Retrofit retrofit;
    private SongsApi songsApi;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_detailed);

        retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_link))
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        songsApi = retrofit.create(SongsApi.class);

        song = (SongDto) getIntent().getSerializableExtra("song");

        SharedPreferences preferences = this.getApplicationContext().getSharedPreferences("saci.android", Context.MODE_PRIVATE);
        userId = preferences.getString("userId", new String());

        likeSongRadioButton();
        addToPlaylistButton();
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
                LayoutInflater popupLayoutInflater = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = popupLayoutInflater.inflate(R.layout.choose_playlist_popup, null);
                final PopupWindow choosePlaylistPopup = new PopupWindow(popupView,
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                // TODO interrogate database for user's playlists
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
