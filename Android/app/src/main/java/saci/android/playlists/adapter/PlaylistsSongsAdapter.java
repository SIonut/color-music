package saci.android.playlists.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import saci.android.R;
import saci.android.dtos.Song;

/**
 * Created by corina on 22.06.2017.
 */

public class PlaylistsSongsAdapter extends ArrayAdapter<Song> {

    public PlaylistsSongsAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Song item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

//        TextView songTitle = (TextView) convertView.findViewById(R.id.song_title);
//        TextView songAuthor = (TextView) convertView.findViewById(R.id.song_author);
//
//        songTitle.setText(item.getTitle());
//        songAuthor.setText(item.getAuthor());

        return convertView;
    }
}
