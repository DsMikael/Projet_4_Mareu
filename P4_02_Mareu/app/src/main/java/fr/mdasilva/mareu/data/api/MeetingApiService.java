package fr.mdasilva.mareu.data.api;

import java.util.List;

import fr.mdasilva.mareu.data.model.Location;
import fr.mdasilva.mareu.data.model.Meeting;

/**
 * Reuinion API client
 */
public interface MeetingApiService {

    /**
     ** Get all Meetings
     * 
     * @return {@link List}
     */
    List<Meeting> getMeeting();

    /**
     * Deletes a meeting
     * 
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * 
     * @param meeting
     */
    void createMeeting(Meeting meeting);


}