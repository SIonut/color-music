package saci.android.playlists.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import saci.android.R;
import saci.android.dtos.PlaylistDto;
import saci.android.playlists.PlaylistsListActivity;

/**
 * Created by corina on 21.06.2017.
 */
public class PlaylistListAdapter extends ArrayAdapter<PlaylistDto> {

    public PlaylistListAdapter(PlaylistsListActivity playlistsListActivity, List<PlaylistDto> playlistsList) {
        super(playlistsListActivity, 0, playlistsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PlaylistDto item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }



        return convertView;
    }
}
