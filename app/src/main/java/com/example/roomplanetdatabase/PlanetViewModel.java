package com.example.roomplanetdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class PlanetViewModel extends AndroidViewModel {
    private PlanetRepository mRepository;
    private LiveData<List<Planet>> pAllPlanets;

    public PlanetViewModel (Application application) {
        super(application);
        mRepository = new PlanetRepository(application);
        pAllPlanets = mRepository.getAllWords();
    }
    LiveData<List<Planet>> getAllWords() { return pAllPlanets; }
    public void insert(Planet planet) { mRepository.insert(planet); }

}
