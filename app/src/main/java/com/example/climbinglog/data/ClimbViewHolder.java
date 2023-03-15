package com.example.climbinglog.data;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.climbinglog.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.climbinglog.data.Climb;

public class ClimbViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitleTextView;
    private TextView mNameTextView;
    private TextView mDateTextView;
    private TextView mDifficultyTextView;
    private TextView mNotesTextView;
    private Climb mClimb;
    private TextView mCountTextView;
    public TextView climbNameTextView;
    public ImageView difficultyEmoji;

    public ClimbViewHolder(View itemView) {
        super(itemView);

        mNameTextView = itemView.findViewById(R.id.climb_name);
        mDateTextView = itemView.findViewById(R.id.climb_date);
        //mDifficultyTextView = itemView.findViewById(R.id.climb_difficulty);
        mNotesTextView = itemView.findViewById(R.id.climb_notes);
        //mCountTextView = itemView.findViewById(R.id.count_text_view);

        difficultyEmoji = itemView.findViewById(R.id.difficulty_emoji);
    }

    public void bind(Climb climb, int position) {
        mClimb = climb;
        mNameTextView.setText("Grade: " + mClimb.getName());
        mDifficultyTextView.setText("Difficulty: " + mClimb.getDifficulty());
        mDateTextView.setText("Date: " + mClimb.getDate().toString());
        mNotesTextView.setText("Notes: " + mClimb.getNotes());
        mCountTextView.setText("Climb: " + mClimb.getId());
        if (mDateTextView != null) {
            mDateTextView.setText("Date: " + mClimb.getDate().toString());
    }
}}
