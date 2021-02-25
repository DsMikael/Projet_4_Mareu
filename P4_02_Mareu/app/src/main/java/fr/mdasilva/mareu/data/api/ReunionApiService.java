package fr.mdasilva.mareu.data.api;

import java.util.List;

import fr.mdasilva.mareu.data.model.Reunion;
/**
 * Reuinion API client
 */
public interface ReunionApiService {

    /**
     ** Get all Reunions
     * @return {@link List}
     */
    List<Reunion> getReunion();

    /**
     * Deletes a reunion
     * @param reunion
     */
    void deleteReunion(Reunion reunion);

    /**
     * Create a reunion
     * @param reunion
     */
    void createReunion(Reunion reunion);


}