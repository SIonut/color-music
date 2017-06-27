package saci.android.playlists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saci.android.CustomPreferences;
import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.dtos.SongDto;
import saci.android.network.PlaylistApi;
import saci.android.network.RestClient;

/**
 * Created by corina on 27.06.2017.
 */
public class NewPlaylist extends AppCompatActivity {

    private EditText playlistName;
    private Button createPlaylistButton;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_playlist);

        playlistName = (EditText) findViewById(R.id.edit_playlist_name);
        createPlaylistButton = (Button) findViewById(R.id.create_playlist);
        userId = this.getApplicationContext().getSharedPreferences("saci.android", MODE_PRIVATE)
                .getString(CustomPreferences.USER_ID, "");

        createPlaylist();
    }

    private void createPlaylist() {

        createPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaylistApi playlistApi = RestClient.getClient().create(PlaylistApi.class);
                PlaylistDto playlistDto = new PlaylistDto();
                playlistDto.setName(String.valueOf(playlistName.getText()));
                playlistDto.setUserId(userId);
                playlistDto.setSongs(new ArrayList<SongDto>());

                playlistApi.createPlaylist(playlistDto).enqueue(new Callback<PlaylistDto>() {
                    @Override
                    public void onResponse(Call<PlaylistDto> call, Response<PlaylistDto> response) {

                    }

                    @Override
                    public void onFailure(Call<PlaylistDto> call, Throwable t) {
                        Toast.makeText(NewPlaylist.this, "Could not create playlist!", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });

    }

}
