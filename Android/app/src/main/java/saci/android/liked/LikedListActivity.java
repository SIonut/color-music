package saci.android.liked;

import android.content.Context;
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
import saci.android.liked.adapter.LikedListAdapter;
import saci.android.network.PlaylistApi;
import saci.android.network.RestClient;
import saci.android.network.SongsApi;
import saci.android.song.SongDetails;

/**
 * Created by corina on 21.06.2017.
 */
public class LikedListActivity extends AppCompatActivity {

    private List<SongDto> likedList;
    private SharedPreferences preferences;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_list);

        preferences = ColorMusicApplication.getSharedPreferences();
        userId = preferences.getString(CustomPreferences.USER_ID, new String());

        liked();
    }

    private void liked() {
        likedList = new ArrayList<>();

        PlaylistApi playlistApi = RestClient.getClient().create(PlaylistApi.class);
        playlistApi.getLikes(userId).enqueue(new Callback<PlaylistDto>() {
            @Override
            public void onResponse(Call<PlaylistDto> call, Response<PlaylistDto> response) {
                likedList = response.body().getSongs();
                createListAdapter();

            }

            @Override
            public void onFailure(Call<PlaylistDto> call, Throwable t) {

            }
        });
    }

    private void createListAdapter() {
        ArrayAdapter adapter = new LikedListAdapter(this, likedList);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SongsApi songsApi = RestClient.getClient().create(SongsApi.class);
                songsApi.getById(likedList.get(position).getId()).enqueue(new Callback<SongDto>() {
                    @Override
                    public void onResponse(Call<SongDto> call, Response<SongDto> response) {
                        if (response.code() == 200) {
                            Intent detailsIntent = new Intent(LikedListActivity.this, SongDetails.class);
                            detailsIntent.putExtra("song", response.body());
                            startActivity(detailsIntent);
                        } else {
                            Toast.makeText(LikedListActivity.this, "Cannot find song!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SongDto> call, Throwable t) {
                        Toast.makeText(LikedListActivity.this, "Cannot find song!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
