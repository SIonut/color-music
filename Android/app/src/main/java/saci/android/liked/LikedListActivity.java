package saci.android.liked;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import saci.android.R;
import saci.android.dtos.Song;
import saci.android.liked.adapter.LikedListAdapter;

/**
 * Created by corina on 21.06.2017.
 */
public class LikedListActivity extends AppCompatActivity {

    private List<Song> likedList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_list);

        likedList = new ArrayList<>();

    }

    private void createListAdapter() {
        ArrayAdapter adapter = new LikedListAdapter(this, likedList);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

    }
}
