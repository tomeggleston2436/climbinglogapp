package com.example.climbinglog;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.climbinglog.data.Climb;
import com.example.climbinglog.data.ClimbingLogApplication;
import com.example.climbinglog.data.ClimbDao;
import com.example.climbinglog.data.ClimbRepository;
import com.example.climbinglog.data.ViewClimbsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ClimbRepository climbRepository;
    private int selectedEmojiValue;
    private List<ImageView> emojiImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emojiImageViews = new ArrayList<>();
        selectedEmojiValue = 1; // Default value

        EditText dateEditText = findViewById(R.id.date_edit_text);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        dateEditText.setText(currentDate);

        Button viewClimbsButton = findViewById(R.id.view_climbs_button);
        viewClimbsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewClimbsActivity.class);
                startActivity(intent);
            }
        });

        // Retrieve the ClimbDao instance from the database
        ClimbDao climbDao = ClimbingLogApplication.getInstance().getClimbDatabase().climbDao();

        // Create a new ClimbRepository instance with the ClimbDao
        climbRepository = new ClimbRepository(climbDao);

        // Emoji code
        ImageView emoji1 = findViewById(R.id.emoji_1);
        ImageView emoji2 = findViewById(R.id.emoji_2);
        ImageView emoji3 = findViewById(R.id.emoji_3);
        ImageView emoji4 = findViewById(R.id.emoji_4);
        ImageView emoji5 = findViewById(R.id.emoji_5);
        // Add more ImageViews for other emojis

        emojiImageViews.add(emoji1);
        emojiImageViews.add(emoji2);
        emojiImageViews.add(emoji3);
        emojiImageViews.add(emoji4);
        emojiImageViews.add(emoji5);

        View.OnClickListener emojiClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedEmojiValue = Integer.parseInt(v.getTag().toString());
                // You can save the value, use it for your logic, or display it somewhere
                highlightSelectedEmoji((ImageView) v);
            }
        };

        emoji1.setOnClickListener(emojiClickListener);
        emoji2.setOnClickListener(emojiClickListener);
        emoji3.setOnClickListener(emojiClickListener);
        emoji4.setOnClickListener(emojiClickListener);
        emoji5.setOnClickListener(emojiClickListener);
        // Set the click listener for other ImageViews

        // Save button click listener
        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText fields and insert new climb record into database
                EditText nameEditText = findViewById(R.id.name_edit_text);
                EditText dateEditText = findViewById(R.id.date_edit_text);
                EditText notesEditText = findViewById(R.id.notes_edit_text);

                String name = nameEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String notes = notesEditText.getText().toString();
                //dropdown grade
                Spinner climbNameSpinner = findViewById(R.id.climb_name_spinner);
                String selectedClimbName = climbNameSpinner.getSelectedItem().toString();

                // Use selectedEmojiValue for the difficulty value when creating a new Climb object
                Climb climb = new Climb(selectedClimbName, date, String.valueOf(selectedEmojiValue), notes);

                // Insert the new climb into the database using the repository
                climbRepository.insertClimb(climb);

                // Show a toast message to indicate success
                Toast.makeText(MainActivity.this, "Climb saved", Toast.LENGTH_SHORT).show();

                selectedEmojiValue = 0;
                for (ImageView emoji :emojiImageViews){
                    emoji.setBackground(null); // Remove highlight from all emojis
                }

                notesEditText.setText("");

                climbNameSpinner.setSelection(0);
                }
            });
        }


    private void highlightSelectedEmoji(ImageView selectedImageView) {
        // Remove highlight from all emojis
        for (ImageView emoji : emojiImageViews) {
            if (emoji == selectedImageView) {
                emoji.setBackground(ContextCompat.getDrawable(this, R.drawable.emoji_border));
            } else {
                emoji.setBackground(null);
            }
        }
    }
}