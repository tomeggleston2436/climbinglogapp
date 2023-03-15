package com.example.climbinglog.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.climbinglog.R;
import com.example.climbinglog.data.Climb;

import java.util.ArrayList;
import java.util.List;

public class ClimbsAdapter extends RecyclerView.Adapter<ClimbsAdapter.ClimbViewHolder> {

    private List<Climb> mClimbs;

    private ClimbRepository climbRepository;
    private ClimbRepository mClimbRepository;

    public ClimbsAdapter(ClimbRepository climbRepository) {
        mClimbs = new ArrayList<>();
        mClimbRepository = climbRepository;
    }

    @Override
    public ClimbViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_climb, parent, false);
        return new ClimbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClimbViewHolder holder, int position) {
        Climb climb = mClimbs.get(position);
        holder.bind(climb, position);

        Button deleteButton = holder.itemView.findViewById(R.id.delete_button);
        // Set the click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the Climb object to be deleted
                Climb climbToDelete = mClimbs.get(holder.getBindingAdapterPosition());

                // Delete the climb from the database
                mClimbRepository.deleteClimb(climbToDelete);

                // Update the adapter's data and notify the change
                mClimbs.remove(holder.getBindingAdapterPosition());
                notifyDataSetChanged();

                // Show a toast message to indicate success
                Toast.makeText(holder.itemView.getContext(), "Climb deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setClimbs(List<Climb> newClimbs) {
        mClimbs.clear();
        if (newClimbs != null) {
            mClimbs.addAll(newClimbs);
        }
    }

    @Override
    public int getItemCount() {
        return mClimbs.size();
    }

    class ClimbViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mDateTextView;
        private ImageView mDifficultyEmoji;
        private TextView mNotesTextView;
        private TextView mCountTextView;
        private Climb mClimb;

        public ClimbViewHolder(View itemView) {
            super(itemView);

            mNameTextView = itemView.findViewById(R.id.climb_name);
            mDateTextView = itemView.findViewById(R.id.climb_date);
            mDifficultyEmoji = itemView.findViewById(R.id.difficulty_emoji);
            mNotesTextView = itemView.findViewById(R.id.climb_notes);
            mCountTextView = itemView.findViewById(R.id.climb_count);
        }

        public void bind(Climb climb, int position) {
            mClimb = climb;
            mNameTextView.setText("Grade: " + mClimb.getName());
            mDateTextView.setText("Date: " + mClimb.getDate().toString());
            mNotesTextView.setText("Notes: " + mClimb.getNotes());
            mCountTextView.setText("Climb: " + mClimb.getId());
            if (mDateTextView != null) {
                mDateTextView.setText("Date: " + mClimb.getDate().toString());
            }

            int difficulty = Integer.parseInt(mClimb.getDifficulty());

            int emojiResource;

            switch (difficulty) {
                case 1:
                    emojiResource = R.drawable.emoji_1;
                    break;
                case 2:
                    emojiResource = R.drawable.emoji_2;
                    break;
                case 3:
                    emojiResource = R.drawable.emoji_3;
                    break;
                case 4:
                    emojiResource = R.drawable.emoji_4;
                    break;
                case 5:
                    emojiResource = R.drawable.emoji_5;
                    break;
                default:
                    emojiResource = R.drawable.emoji_1;
                    break;
            }

            mDifficultyEmoji.setImageResource(emojiResource);
        }
    }
}