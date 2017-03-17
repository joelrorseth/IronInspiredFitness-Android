package com.joelrorseth.ironinspiredfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Exercise> mDataSource;

    // ==============================================
    // ==============================================
    public ExerciseAdapter(Context context, ArrayList<Exercise> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    // MARK: Required Adapter Methods
    // ==============================================
    // ==============================================
    @Override public int getCount() {
        return mDataSource.size();
    }

    // ==============================================
    // ==============================================
    @Override public Object getItem(int position) {
        return mDataSource.get(position);
    }


    // ==============================================
    // ==============================================
    @Override public long getItemId(int position) {
        return position;
    }

    // ==============================================
    // ==============================================
    @Override public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        // Important: convertView holds recycled views to help with efficiency
        if (convertView == null) {

            // Inflate custom row layout
            convertView = mInflater.inflate(R.layout.list_item_exercise, parent, false);

            // Create ViewHolder with references
            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.exercise_list_icon);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.exercise_list_title);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.exercise_list_subtitle);
            holder.detailTextView = (TextView) convertView.findViewById(R.id.exercise_list_detail);

            // Hand onto this holder for future recycling!
            convertView.setTag(holder);

        } else {

            // If we already set a tag for this holder, skip the above steps
            holder = (ViewHolder) convertView.getTag();
        }

        // Simply obtain references to UI elements using holder
        TextView titleTextView = holder.titleTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        TextView detailTextView = holder.detailTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;


        // Get exercise object from data source for this row
        Exercise exercise = (Exercise) getItem(position);

        // Use ViewHolder references to populate each ref with data
        titleTextView.setText(exercise.name);
        subtitleTextView.setText(exercise.difficulty);
        detailTextView.setText(exercise.motion);

        // TODO: Use Picasso open source lib to asynchronously load image
        //Picasso.with(mContext).load(exercise.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        return convertView;
    }


    // ==============================================
    // ViewHolder efficiently ref's UI elements
    // ==============================================
    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }
}
