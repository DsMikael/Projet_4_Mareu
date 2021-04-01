package fr.mdasilva.mareu.data.events;

import fr.mdasilva.mareu.data.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;

    /**
     * Constructor.
     * 
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
