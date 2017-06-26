package saci.android.following;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.following.adapter.FollowingListAdapter;

/**
 * Created by corina on 21.06.2017.
 */
public class FollowingListActivity extends AppCompatActivity {

    private List<PlaylistDto> followedPlaylists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following_list);

        following();
        createListAdapter();
    }

    private void following() {
        followedPlaylists = new ArrayList<>();
    }

    private void createListAdapter() {
        ArrayAdapter adapter = new FollowingListAdapter(this, followedPlaylists);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

    }

}
