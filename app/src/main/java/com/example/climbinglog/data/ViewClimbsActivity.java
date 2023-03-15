package com.example.climbinglog.data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.example.climbinglog.R;

import java.util.Collections;
import java.util.List;


public class ViewClimbsActivity extends AppCompatActivity {

    private RecyclerView mClimbsRecyclerView;
    private ClimbsAdapter mAdapter;
    private ClimbRepository mClimbRepository;
    private ClimbRepository climbRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_climbs);



        // Initialize the RecyclerView
        mClimbsRecyclerView = findViewById(R.id.climbs_recycler_view);

        // Create a new instance of the ClimbsAdapter and pass in the list of climbs
        mAdapter = new ClimbsAdapter(getClimbs());

        // Set the adapter for the RecyclerView
        mClimbsRecyclerView.setAdapter(mAdapter);

        // Set the layout manager for the RecyclerView
        mClimbsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ClimbRepository
        mClimbRepository = new ClimbRepository(ClimbingLogApplication.getInstance().getClimbDatabase().climbDao());


    }

    private List<Climb> getClimbs() {
        // Retrieve the list of climbs from the ClimbRepository using LiveData
        List<Climb> climbs = new ArrayList<>();
        LiveData<List<Climb>> liveData = ClimbingLogApplication.getInstance().getClimbRepository().getAllClimbs();

        liveData.observe(this, new Observer<List<Climb>>()
        {
            @Override
            public void onChanged(List<Climb> updatedClimbs) {
                // Update the list of climbs and notify the adapter of the changes
                climbs.clear();
                climbs.addAll(updatedClimbs);

                mAdapter.notifyDataSetChanged();
            }
        });
        return climbs;
    }
}