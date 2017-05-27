package saci.android.colors;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import saci.android.ChangeActivity;
import saci.android.R;
import saci.android.search.SearchMoodsResultActivity;

/**
 * Created by Corina on 5/25/2017.
 */

public class ColorsActivity extends AppCompatActivity implements ChangeActivity {

    private TextView selectedMoodsView;
    private Button findButton;
    private List<Button> buttons = new ArrayList<>();

    private ArrayList<String> selectedMoods = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colors);

        selectedMoodsView = (TextView) findViewById(R.id.selected_moods);
        findButton = (Button) findViewById(R.id.find);

        buttons.add((Button) findViewById(R.id.energized));
        buttons.add((Button) findViewById(R.id.proud));
        buttons.add((Button) findViewById(R.id.angry));
        buttons.add((Button) findViewById(R.id.sad));
        buttons.add((Button) findViewById(R.id.furious));
        buttons.add((Button) findViewById(R.id.pessimistic));
        buttons.add((Button) findViewById(R.id.hurt));
        buttons.add((Button) findViewById(R.id.optimistic));
        buttons.add((Button) findViewById(R.id.excited));
        buttons.add((Button) findViewById(R.id.loved));
        buttons.add((Button) findViewById(R.id.exhausted));
        buttons.add((Button) findViewById(R.id.fabulous));
        buttons.add((Button) findViewById(R.id.happy));
        buttons.add((Button) findViewById(R.id.lost));
        buttons.add((Button) findViewById(R.id.emotional));
        buttons.add((Button) findViewById(R.id.strong));
        buttons.add((Button) findViewById(R.id.joyful));
        buttons.add((Button) findViewById(R.id.crazy));
        buttons.add((Button) findViewById(R.id.lazy));
        buttons.add((Button) findViewById(R.id.chill));

        selectMoods();
        changeActivity();
    }

    private void selectMoods() {
        for (final Button b: buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!selectedMoods.contains(b.getText())) {
                        if (!selectedMoods.isEmpty()) {
                            selectedMoodsView.append(" & " + b.getText());
                            selectedMoods.add((String) b.getText());
                        } else {
                            selectedMoodsView.append(b.getText());
                            selectedMoods.add((String) b.getText());
                        }
                    }
                }
            });
        }
    }

    @Override
    public void changeActivity() {
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findIntent = new Intent(ColorsActivity.this, SearchMoodsResultActivity.class);
                findIntent.putExtra("moods", selectedMoods);
                startActivity(findIntent);
            }
        });
    }
}
