package fr.mdasilva.mareu.data.event;

import fr.mdasilva.mareu.data.model.Meeting;
import timber.log.Timber;

public class DeleteMeetingEvent {
    /**
     * Meeting to delete
     */
    public Meeting meeting;

    /**
     * Constructor.
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
        Timber.d(meeting.toString());
    }
}
