package saci.android.playlists.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saci.android.CustomPreferences;
import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.network.PlaylistApi;
import saci.android.network.RestClient;
import saci.android.playlists.TopPlaylistsActivity;

/**
 * Created by corina on 28.06.2017.
 */

public class TopPlaylistAdapter extends ArrayAdapter<PlaylistDto> {


    private PlaylistApi playlistApi;

    public TopPlaylistAdapter(TopPlaylistsActivity topPlaylists, List<PlaylistDto> topPlaylistsList) {
        super(topPlaylists, 0, topPlaylistsList);

        playlistApi = RestClient.getClient().create(PlaylistApi.class);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final PlaylistDto item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.top_playlists_item, parent, false);
        }

        TextView playlistName = (TextView) convertView.findViewById(R.id.playlist_name);
        TextView playlistLikesNo = (TextView) convertView.findViewById(R.id.playlist_likes);
        final CheckBox likedCheckBox = (CheckBox) convertView.findViewById(R.id.liked);

        if (item.getFollowing() != null) {
            playlistName.setText(item.getName());
            playlistLikesNo.setText(item.getFollowing().size() + "");

            for (int i=0; i < item.getFollowing().size(); i++) {
                if (getContext().getApplicationContext().getSharedPreferences("saci.android", Context.MODE_PRIVATE)
                        .getString(CustomPreferences.USER_ID, "").equals(item.getUserId())) {
                    likedCheckBox.setChecked(true);
                }
            }

            likedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (likedCheckBox.isChecked()) {
                        item.getFollowing().add(item.getUserId());
                    } else {
                        item.getFollowing().remove(item.getUserId());
                    }

                    playlistApi.updatePlaylist(item).enqueue(new Callback<PlaylistDto>() {
                        @Override
                        public void onResponse(Call<PlaylistDto> call, Response<PlaylistDto> response) {

                        }

                        @Override
                        public void onFailure(Call<PlaylistDto> call, Throwable t) {

                        }
                    });
                }
            });
        }

        return convertView;
    }
}
