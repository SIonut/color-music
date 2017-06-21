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
import saci.android.music.ColorMusicResultActivity;

/**
 * Created by Corina on 5/26/2017.
 */
public class SearchResultListAdapter extends ArrayAdapter<String> {

    public SearchResultListAdapter(ColorMusicResultActivity colorMusicResultActivity, ArrayList<String> songsList) {
        super(colorMusicResultActivity, 0, songsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        return convertView;
    }
}
