package saci.android.search;

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
import saci.android.search.adapter.SearchResultListAdapter;

/**
 * Created by Corina on 5/25/2017.
 */

public class SearchMoodsResultActivity extends AppCompatActivity {

    private SearchMoodsController searchMoodsController;

    private ArrayList<String> moods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_list);

        moods = getIntent().getStringArrayListExtra("moods");
        searchMoodsController = new SearchMoodsController(moods);

        createListAdapter();
    }

    private void createListAdapter() {
        ArrayAdapter adapter = new SearchResultListAdapter(this, moods);

        ListView listView = (ListView) findViewById(R.id.y);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(SearchMoodsResultActivity.this, ResultDetails.class);
                startActivity(detailsIntent);
            }
        });
    }
}
