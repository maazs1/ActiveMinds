package com.activeminds.activemindsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

/**
 * This is an adapter for displaying notifications in NotificationActivity
 **/
public class followAdapter extends ArrayAdapter<follow> {

    private static final String TAG = "For Testing";
    private ArrayList<follow> followList;
    private Context context;

    public followAdapter(ArrayList<follow> followList, Context context) {
        super(context, 0, followList);
        this.followList = followList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.follow_adapter, parent, false);
        }

        follow follow = followList.get(position);
        //select TextViews
        ImageView userImage = view.findViewById(R.id.user_image);
        TextView author = view.findViewById(R.id.req_author);

        //setText of TextViews
        //emoticon.setImageResource(new Emoticon(moodEvent.getEmotionalState(), 1).getImageLink());
        author.setText(follow.getUsername());

        return view;
    }
}
