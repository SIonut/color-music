package saci.android.playlists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import saci.android.R;
import saci.android.network.PlaylistApi;
import saci.android.network.RestClient;

/**
 * Created by corina on 27.06.2017.
 */
public class NewPlaylist extends AppCompatActivity {

    private EditText playlistName;
    private Button createPlaylistButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_playlist);

        playlistName = (EditText) findViewById(R.id.edit_playlist_name);
        createPlaylistButton = (Button) findViewById(R.id.create_playlist);

        createPlaylist();
    }

    private void createPlaylist() {

        createPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaylistApi playlistApi = RestClient.getClient().create(PlaylistApi.class);

                // TODO database interrogation
                finish();
            }
        });



    }

}
