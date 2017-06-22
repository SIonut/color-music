package saci.android.song;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import saci.android.R;
import saci.android.dtos.Playlist;

/**
 * Created by corina on 22.06.2017.
 */
public class ChoosePlaylistPopUpAdapter extends ArrayAdapter<Playlist> {

    public ChoosePlaylistPopUpAdapter(View view, List<Playlist> resource) {
        super(view.getContext(), 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Playlist item = getItem(position);

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
