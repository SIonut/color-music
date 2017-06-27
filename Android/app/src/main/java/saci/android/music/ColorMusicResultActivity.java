package saci.android.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import saci.android.R;
import saci.android.colors.ColorsActivity;
import saci.android.dtos.SongDto;
import saci.android.login.LoginActivity;
import saci.android.music.adapter.SearchResultListAdapter;
import saci.android.network.RestClient;
import saci.android.network.SongsApi;
import saci.android.song.SongDetails;

/**
 * Created by Corina on 5/25/2017.
 */
public class ColorMusicResultActivity extends AppCompatActivity {

    private ArrayList<SongDto> songsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_list);

        songsList = (ArrayList<SongDto>) getIntent().getSerializableExtra("songs");
        createListAdapter();

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
                SongsApi songsApi = RestClient.getClient().create(SongsApi.class);
                songsApi.getById(songsList.get(position).getId()).enqueue(new Callback<SongDto>() {
                    @Override
                    public void onResponse(Call<SongDto> call, Response<SongDto> response) {
                        if (response.code() == 200) {
                            Intent detailsIntent = new Intent(ColorMusicResultActivity.this, SongDetails.class);
                            SongDto songDto = response.body();
                            detailsIntent.putExtra("song", response.body());
                            startActivity(detailsIntent);
                        } else {
                            Toast.makeText(ColorMusicResultActivity.this, "Cannot find song!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SongDto> call, Throwable t) {
                        Toast.makeText(ColorMusicResultActivity.this, "Cannot find song!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

}
