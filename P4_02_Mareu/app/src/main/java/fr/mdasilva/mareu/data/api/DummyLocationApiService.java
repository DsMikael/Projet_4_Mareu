package fr.mdasilva.mareu.data.api;

import java.util.List;

import fr.mdasilva.mareu.data.model.Location;

public class DummyLocationApiService implements LocationApiService {

    private final List<Location> locations = DummyLocationGenerator.generateLocations();

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Location> getLocation() {
        return locations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteLocation(Location location) {
        locations.remove(location);
    }

    /**
     * {@inheritDoc}
     *
     * @param location
     */
    @Override
    public void createLocation(Location location) {
        locations.add(location);
    }
}