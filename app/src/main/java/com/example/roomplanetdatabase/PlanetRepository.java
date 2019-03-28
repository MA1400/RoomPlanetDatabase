package com.example.roomplanetdatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PlanetRepository {
    private PlanetDao pPlanetDao;
    private LiveData<List<Planet>> pAllPlanets;

    PlanetRepository(Application application) {
        PlanetRoomDatabase db = PlanetRoomDatabase.getDatabase(application);
        pPlanetDao = db.planetDao();
        pAllPlanets = pPlanetDao.getAllPlanets();
    }
    LiveData<List<Planet>> getAllWords() {
        return pAllPlanets;
    }
    public void insert (Planet planet) {
        new insertAsyncTask(pPlanetDao).execute(planet);
    }
    private static class insertAsyncTask extends AsyncTask<Planet, Void, Void> {

        private PlanetDao mAsyncTaskDao;

        insertAsyncTask(PlanetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Planet... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
