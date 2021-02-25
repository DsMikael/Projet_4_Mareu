package fr.mdasilva.mareu.data.events;

import fr.mdasilva.mareu.data.model.Reunion;

public class DeleteReunionEvent {

    public Reunion reunion;
    /**
     * Constructor.
     * @param reunion
     */
    public DeleteReunionEvent(Reunion reunion){ this.reunion = reunion; }
}
