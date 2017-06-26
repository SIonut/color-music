package saci.android.music.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import saci.android.R;
import saci.android.dtos.SongDto;
import saci.android.music.ColorMusicResultActivity;

/**
 * Created by Corina on 5/26/2017.
 */
public class SearchResultListAdapter extends ArrayAdapter<SongDto> {

    public SearchResultListAdapter(ColorMusicResultActivity colorMusicResultActivity, ArrayList<SongDto> songsList) {
        super(colorMusicResultActivity, 0, songsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        SongDto item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView songTitle = (TextView) convertView.findViewById(R.id.song_title);
        TextView songAuthor = (TextView) convertView.findViewById(R.id.song_author);

        songTitle.setText(item.getTitle());
        songAuthor.setText(item.getAuthor());

        return convertView;
}
}
