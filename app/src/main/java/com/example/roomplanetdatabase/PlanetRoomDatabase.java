package com.example.roomplanetdatabase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

public abstract class PlanetRoomDatabase extends RoomDatabase {

    public abstract PlanetDao planetDao();

    private static volatile PlanetRoomDatabase INSTANCE;

    static PlanetRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlanetRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlanetRoomDatabase.class, "planet_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlanetDao mDao;

        PopulateDbAsync(PlanetRoomDatabase db) {
            mDao = db.planetDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
           // mDao.deleteAll();
            Planet planet;

            planet p1 = new Planet("Earth",9.807f );
            mDao.insert(planet);
            Planet planet2 = new Planet("Jupiter",24.8f );
            mDao.insert(planet2);
            planet = new Planet("Mars",3.71f );
            mDao.insert(planet);
            planet = new Planet("Mercury",3.7f );
            mDao.insert(planet);
            planet = new Planet("Venus",8.87f );
            mDao.insert(planet);

            return null;
        }
    }
}
