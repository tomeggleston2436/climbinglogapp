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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_climbs);

        // Initialize the ClimbRepository
        mClimbRepository = new ClimbRepository(ClimbingLogApplication.getInstance().getClimbDatabase().climbDao());

        // Initialize the RecyclerView
        mClimbsRecyclerView = findViewById(R.id.climbs_recycler_view);

        // Retrieve the LiveData object containing the list of climbs
        LiveData<List<Climb>> liveData = getClimbs();

        // Create a new instance of the ClimbsAdapter and pass in the LiveData and the ClimbRepository
        mAdapter = new ClimbsAdapter(mClimbRepository, mClimbsRecyclerView);

        // Set the adapter for the RecyclerView
        mClimbsRecyclerView.setAdapter(mAdapter);

        // Set the layout manager for the RecyclerView
        mClimbsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Observe changes in the LiveData object and update the adapter
        liveData.observe(this, new Observer<List<Climb>>() {
            @Override
            public void onChanged(List<Climb> updatedClimbs) {
                mAdapter.setClimbs(updatedClimbs);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private LiveData<List<Climb>> getClimbs() {
        // Retrieve the list of climbs from the ClimbRepository using LiveData
        return ClimbingLogApplication.getInstance().getClimbRepository().getAllClimbs();
    }
}