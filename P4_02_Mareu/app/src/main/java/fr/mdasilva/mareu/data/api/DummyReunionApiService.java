package fr.mdasilva.mareu.data.api;


import java.util.List;

import fr.mdasilva.mareu.data.model.Location;
import fr.mdasilva.mareu.data.model.Reunion;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class DummyReunionApiService implements ReunionApiService{

    private List<Reunion> reunions = DummyReunionGenerator.generateReunions();
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reunion> getReunion() {
        return reunions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReunion(Reunion reunion) {
        reunions.remove(reunion);
    }

    /**
     * {@inheritDoc}
     * @param reunion
     */
    @Override
    public void createReunion(Reunion reunion) {
        reunions.add(reunion);
    }
}