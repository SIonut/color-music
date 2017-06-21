package saci.android.liked.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import saci.android.R;
import saci.android.dtos.Song;
import saci.android.liked.LikedListActivity;

/**
 * Created by corina on 21.06.2017.
 */
public class LikedListAdapter extends ArrayAdapter<Song> {

    public LikedListAdapter(LikedListActivity likedListActivity, List<Song> likedList) {
        super(likedListActivity, 0, likedList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Song item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        return convertView;
    }
}
