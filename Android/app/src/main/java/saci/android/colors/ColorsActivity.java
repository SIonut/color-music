package saci.android.colors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saci.android.ChangeActivity;
import saci.android.R;
import saci.android.dtos.SongDto;
import saci.android.liked.LikedListActivity;
import saci.android.music.ColorMusicResultActivity;
import saci.android.network.RestClient;
import saci.android.network.SongsApi;
import saci.android.playlists.PlaylistsListActivity;

/**
 * Created by Corina on 5/25/2017.
 */
public class ColorsActivity extends AppCompatActivity implements ChangeActivity {

    private TextView drawerPlaylists;
    private TextView drawerLiked;

    private TextView selectedMoodsView;
    private Button findButton;
    private List<Button> buttons = new ArrayList<>();
    private List<Button> selectedButtons = new ArrayList<>();

    private ArrayList<String> selectedMoods = new ArrayList<>();

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colors);
        context = this;

        selectedButtons = new ArrayList<>();

        drawerPlaylists = (TextView) findViewById(R.id.playlist_navigator);
//        drawerFollowing = (TextView) findViewById(R.id.following_navigator);
        drawerLiked = (TextView) findViewById(R.id.liked_navigator);

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

        drawer();
    }

    private void selectMoods() {
        for (final Button b: buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!selectedMoods.contains(b.getHint())) {
                        if (selectedButtons.size() < 3) {
                            selectedMoodsView.append(selectedMoods.isEmpty() ? "" : " ");
                            selectedMoodsView.append(b.getText());
                            selectedMoods.add((String) b.getHint());
                            selectedButtons.add(b);
                        }
                    } else {
                        selectedButtons.remove(b);
                        selectedMoods.remove(b.getHint());
                        selectedMoodsView.setText("");
                        for (Button b : selectedButtons) {
                            selectedMoodsView.append(selectedMoods.isEmpty() ? "" : " ");
                            selectedMoodsView.append(b.getText());
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

                String colors = "";

                for (int i=0; i < selectedMoods.size()-1; i++) {
                    colors += selectedMoods.get(i) + ",";
                }
                colors += selectedMoods.get(selectedMoods.size()-1);

                SongsApi songsApi = RestClient.getClient().create(SongsApi.class);
                songsApi.getByColor(colors).enqueue(new Callback<List<SongDto>>() {
                    @Override
                    public void onResponse(Call<List<SongDto>> call, Response<List<SongDto>> response) {
                        Intent findIntent = new Intent(context, ColorMusicResultActivity.class);
                        ArrayList<SongDto> resp = (ArrayList<SongDto>) response.body();
                        findIntent.putExtra("songs", resp);
                        startActivity(findIntent);
                    }

                    @Override
                    public void onFailure(Call<List<SongDto>> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void drawer() {
        drawerPlaylists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playlistIntent = new Intent(ColorsActivity.this, PlaylistsListActivity.class);
                startActivity(playlistIntent);
            }
        });
        drawerLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent likedIntent = new Intent(ColorsActivity.this, LikedListActivity.class);
                startActivity(likedIntent);
            }
        });
    }

}
