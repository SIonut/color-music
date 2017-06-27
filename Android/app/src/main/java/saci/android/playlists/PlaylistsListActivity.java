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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.network.PlaylistApi;
import saci.android.network.RestClient;
import saci.android.network.SongsApi;
import saci.android.playlists.adapter.PlaylistListAdapter;

/**
 * Created by corina on 21.06.2017.
 */
public class PlaylistsListActivity extends AppCompatActivity {

    private List<PlaylistDto> playlistsList;
    private Boolean addToPlaylist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists_list);

        playlistsList = new ArrayList<>();
        addToPlaylist = getIntent().hasExtra("addToLiked") ? true : false;

        playlists();
    }

    private void playlists() {
        SharedPreferences preferences = this.getApplicationContext().getSharedPreferences("saci.android", MODE_PRIVATE);
        String userId = preferences.getString("userId", new String());

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

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

        if (addToPlaylist) {
            listView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlaylistApi playlistApi = RestClient.getClient().create(PlaylistApi.class);

                    // TODO database interrogation
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