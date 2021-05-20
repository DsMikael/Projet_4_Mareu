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

    @Override
    public Location findLocationByName(String location) {
        for (Location location1 : locations) {
            if(location1.getName().equals(location)){
                return location1;
            }
        }
        return null;
    }

}
