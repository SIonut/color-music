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

import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.playlists.adapter.PlaylistListAdapter;

/**
 * Created by corina on 21.06.2017.
 */
public class PlaylistsListActivity extends AppCompatActivity {

    private List<PlaylistDto> playlistsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists_list);

        playlists();
        createListAdapter();
    }

    private void playlists() {
        playlistsList = new ArrayList<>();

        // TODO interrogate database

    }

    private void createListAdapter() {
        ArrayAdapter adapter = new PlaylistListAdapter(this, playlistsList);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

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