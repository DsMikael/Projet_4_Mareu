package fr.mdasilva.mareu.data.api;

import java.util.List;

import fr.mdasilva.mareu.data.model.Location;

    /**
     * Location API client
     */
    public interface LocationApiService {

        /**
         ** Get all location
         *
         * @return {@link List}
         */
        List<Location> getLocation();

        /**
         * Deletes a location
         *
         * @param location
         */
        void deleteLocation(Location location);

        /**
         * Create a location
         *
         * @param location
         */
        void createLocation(Location location);

        Location findLocationByName(String location);
    }
