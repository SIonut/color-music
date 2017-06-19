package saci.android.lists.colorMusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import saci.android.R;
import saci.android.colors.ColorsActivity;
import saci.android.lists.ResultDetails;
import saci.android.lists.SearchMoodsController;
import saci.android.lists.colorMusic.adapter.SearchResultListAdapter;

/**
 * Created by Corina on 5/25/2017.
 */
public class ColorMusicResultActivity extends AppCompatActivity {

    private SearchMoodsController searchMoodsController;

    private String moods;
    private ArrayList<String> moodsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_list);

        moods = getIntent().getStringExtra("moods");
        System.out.print("RESULT TO UI " + moods + "\n");

        moodsList = new ArrayList<>();
        moodsList.add("happy");
        moodsList.add("sad");
        searchMoodsController = new SearchMoodsController(moodsList);

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
        ArrayAdapter adapter = new SearchResultListAdapter(this, moodsList);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(ColorMusicResultActivity.this, ResultDetails.class);
                startActivity(detailsIntent);
            }
        });
    }

}
