package saci.android.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import saci.android.R;

/**
 * Created by Corina on 5/26/2017.
 */

public class SearchResultListAdapter extends ArrayAdapter<String> {

    public SearchResultListAdapter(Context context, ArrayList<String> listItems) {
        super(context, 0, listItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String  item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.song_title);
        TextView author = (TextView) convertView.findViewById(R.id.song_author);
        TextView playlist = (TextView) convertView.findViewById(R.id.playlist);
        TextView user = (TextView) convertView.findViewById(R.id.user);

        return convertView;
    }
}
