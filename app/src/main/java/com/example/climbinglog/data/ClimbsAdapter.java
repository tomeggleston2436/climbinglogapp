package com.example.climbinglog.data;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClimbsAdapter extends RecyclerView.Adapter<ClimbsAdapter.ClimbViewHolder> {

    private List<Climb> mClimbs;
    private ClimbRepository mClimbRepository;
    private RecyclerView mClimbsRecyclerView;

    public ClimbsAdapter(ClimbRepository climbRepository, RecyclerView recyclerView) {
        mClimbs = new ArrayList<>();
        mClimbRepository = climbRepository;
        mClimbsRecyclerView = recyclerView;
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
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Climb climbToDelete = mClimbs.get(holder.getBindingAdapterPosition());
                mClimbRepository.deleteClimb(climbToDelete);

                int deletedPosition = holder.getBindingAdapterPosition();
                mClimbs.remove(deletedPosition);
                notifyDataSetChanged();

                Toast.makeText(holder.itemView.getContext(), "Climb deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setClimbs(List<Climb> newClimbs) {
        mClimbs.clear();
        if (newClimbs != null) {
            // Sort the list based on the id property in descending order
            Collections.sort(newClimbs, new Comparator<Climb>() {
                @Override
                public int compare(Climb o1, Climb o2) {
                    return Long.compare(o2.getId(), o1.getId());
                }
            });
            mClimbs.addAll(newClimbs);
        }
        notifyDataSetChanged();
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
            int climbNumber = getItemCount() - position;
            mCountTextView.setText("Climb: " + climbNumber);
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