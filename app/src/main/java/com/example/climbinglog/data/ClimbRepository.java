package com.example.climbinglog.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ClimbRepository {

    private ClimbDao climbDao;
    private LiveData<List<Climb>> allClimbs;

    public ClimbRepository(ClimbDao climbDao) {
        this.climbDao = climbDao;
        allClimbs = climbDao.getAllClimbs();
    }

    public void insertClimb(Climb climb) {
        new InsertAsyncTask(climbDao).execute(climb);
    }

    public void updateClimb(Climb climb) {
        new UpdateAsyncTask(climbDao).execute(climb);
    }

    public void deleteClimb(Climb climb) {
        new DeleteAsyncTask(climbDao).execute(climb);
    }

    public void deleteAllClimbs() {
        new DeleteAllAsyncTask(climbDao).execute();
    }

    public LiveData<List<Climb>> getAllClimbs() {
        return allClimbs;
    }

    private static class InsertAsyncTask extends AsyncTask<Climb, Void, Void> {

        private ClimbDao climbDao;

        public InsertAsyncTask(ClimbDao climbDao) {
            this.climbDao = climbDao;
        }

        @Override
        protected Void doInBackground(Climb... climbs) {
            climbDao.insertClimb(climbs[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Climb, Void, Void> {

        private ClimbDao climbDao;

        public UpdateAsyncTask(ClimbDao climbDao) {
            this.climbDao = climbDao;
        }

        @Override
        protected Void doInBackground(Climb... climbs) {
            climbDao.updateClimb(climbs[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Climb, Void, Void> {

        private ClimbDao climbDao;

        public DeleteAsyncTask(ClimbDao climbDao) {
            this.climbDao = climbDao;
        }

        @Override
        protected Void doInBackground(Climb... climbs) {
            climbDao.deleteClimb(climbs[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private ClimbDao climbDao;

        public DeleteAllAsyncTask(ClimbDao climbDao) {
            this.climbDao = climbDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            climbDao.deleteAllClimbs();
            return null;
        }
    }
}
