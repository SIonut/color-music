package saci.android.liked;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import saci.android.R;
import saci.android.dtos.SongDto;
import saci.android.liked.adapter.LikedListAdapter;

/**
 * Created by corina on 21.06.2017.
 */
public class LikedListActivity extends AppCompatActivity {

    private List<SongDto> likedList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_list);

        liked();
        createListAdapter();
    }

    private void liked() {
        likedList = new ArrayList<>();

        // TODO interrogate database
    }

    private void createListAdapter() {
        ArrayAdapter adapter = new LikedListAdapter(this, likedList);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

    }
}
