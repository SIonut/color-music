package saci.android.following.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import saci.android.R;
import saci.android.dtos.Playlist;
import saci.android.following.FollowingListActivity;

/**
 * Created by corina on 21.06.2017.
 */
public class FollowingListAdapter extends ArrayAdapter<Playlist> {

    public FollowingListAdapter(FollowingListActivity context, List<Playlist> followedPlaylists) {
        super(context, 0, followedPlaylists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Playlist item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }



        return convertView;
    }
}
