package saci.android.playlists;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saci.android.ColorMusicApplication;
import saci.android.CustomPreferences;
import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.dtos.SongDto;
import saci.android.network.PlaylistApi;
import saci.android.network.RestClient;
import saci.android.playlists.adapter.PlaylistListAdapter;

/**
 * Created by corina on 21.06.2017.
 */
public class PlaylistsListActivity extends AppCompatActivity {

    private List<PlaylistDto> playlistsList;
    private Boolean addToPlaylist;
    private String userId;
    private SongDto songDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists_list);

        SharedPreferences preferences = ColorMusicApplication.getSharedPreferences();

        userId = preferences.getString(CustomPreferences.USER_ID, new String());
        playlistsList = new ArrayList<>();
        addToPlaylist = getIntent().hasExtra("addToPlaylist");
        songDto = (SongDto) getIntent().getSerializableExtra("song");

        playlists();
    }

    private void playlists() {
        final PlaylistApi playlistApi = RestClient.getClient().create(PlaylistApi.class);
        playlistApi.getPlaylists(userId).enqueue(new Callback<List<PlaylistDto>>() {
            @Override
            public void onResponse(Call<List<PlaylistDto>> call, Response<List<PlaylistDto>> response) {
                playlistsList = response.body();

                createListAdapter();
            }

            @Override
            public void onFailure(Call<List<PlaylistDto>> call, Throwable t) {

            }
        });

    }

    private void createListAdapter() {
        ArrayAdapter adapter = new PlaylistListAdapter(this, playlistsList);

        final ListView listView = (ListView) findViewById(R.id.playlists);
        listView.setAdapter(adapter);

        if (addToPlaylist) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    PlaylistDto playlistDto = playlistsList.get(position);
                    playlistDto.getSongs().add(songDto);

                    PlaylistApi playlistApi = RestClient.getClient().create(PlaylistApi.class);

                    playlistApi.updatePlaylist(playlistDto).enqueue(new Callback<PlaylistDto>() {
                        @Override
                        public void onResponse(Call<PlaylistDto> call, Response<PlaylistDto> response) {

                        }

                        @Override
                        public void onFailure(Call<PlaylistDto> call, Throwable t) {
                            Toast.makeText(PlaylistsListActivity.this, "Could not add song to playlist", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();
                }
            });
        } else {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent playlistSongsIntent = new Intent(PlaylistsListActivity.this, PlaylistSongsActivity.class);
                    playlistSongsIntent.putExtra("playlist", playlistsList.get(position));
                    startActivity(playlistSongsIntent);
                }
            });
        }
    }

}