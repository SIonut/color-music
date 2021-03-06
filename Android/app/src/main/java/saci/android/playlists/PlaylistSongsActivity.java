package saci.android.playlists;

import android.content.Intent;
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
import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.dtos.SongDto;
import saci.android.network.PlaylistApi;
import saci.android.network.RestClient;
import saci.android.playlists.adapter.PlaylistsSongsAdapter;
import saci.android.song.SongDetails;

/**
 * Created by corina on 22.06.2017.
 */
public class PlaylistSongsActivity extends AppCompatActivity {

    private List<SongDto> playlistSongList;
    private PlaylistDto playlistDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists_list);

        playlistDto = (PlaylistDto) getIntent().getSerializableExtra("playlist");
        playlistSongList = playlistDto.getSongs();

        createListAdapter();
    }

    private void createListAdapter() {
        ArrayAdapter adapter = new PlaylistsSongsAdapter(this, playlistSongList);

        ListView listView = (ListView) findViewById(R.id.playlists);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(PlaylistSongsActivity.this, SongDetails.class);
                detailsIntent.putExtra("song", playlistSongList.get(position));
                startActivity(detailsIntent);
            }
        });

    }

}
