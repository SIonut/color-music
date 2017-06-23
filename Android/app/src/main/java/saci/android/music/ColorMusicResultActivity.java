package saci.android.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import saci.android.R;
import saci.android.colors.ColorsActivity;
import saci.android.dtos.Song;
import saci.android.dtos.builder.SongBuilder;
import saci.android.music.adapter.SearchResultListAdapter;
import saci.android.song.SongDetails;

/**
 * Created by Corina on 5/25/2017.
 */
public class ColorMusicResultActivity extends AppCompatActivity {

    private String songsResponse;
    private ArrayList<Song> songsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_list);

        songsList = new ArrayList<>();
        songsResponse = getIntent().getStringExtra("songs");

        try {
            songsList = new ObjectMapper().readValue(songsResponse, new TypeReference<List<Song>>(){});
            createListAdapter();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent colorsIntent = new Intent(this, ColorsActivity.class);
        startActivity(colorsIntent);
        finish();
    }

    private void createListAdapter() {
        ArrayAdapter adapter = new SearchResultListAdapter(this, songsList);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(ColorMusicResultActivity.this, SongDetails.class);
                detailsIntent.putExtra("song", songsList.get(position));
                startActivity(detailsIntent);
            }
        });

    }

}
