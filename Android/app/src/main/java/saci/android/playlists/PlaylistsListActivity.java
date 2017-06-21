package saci.android.playlists;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import saci.android.R;
import saci.android.dtos.Playlist;
import saci.android.playlists.adapter.PlaylistListAdapter;

/**
 * Created by corina on 21.06.2017.
 */
public class PlaylistsListActivity extends AppCompatActivity {

    private List<Playlist> playlistsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlists_list);

        playlistsList = new ArrayList<>();
    }

    private void createListAdapter() {
        ArrayAdapter adapter = new PlaylistListAdapter(this, playlistsList);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

    }

}