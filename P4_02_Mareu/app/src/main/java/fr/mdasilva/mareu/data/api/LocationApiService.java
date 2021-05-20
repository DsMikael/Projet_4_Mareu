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

        Location findLocationByName(String location);
    }
