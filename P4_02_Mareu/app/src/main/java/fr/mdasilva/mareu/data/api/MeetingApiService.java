package fr.mdasilva.mareu.data.api;

import org.joda.time.DateTime;

import java.util.List;

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
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     *
     */
    void createMeeting(Meeting meeting);

    boolean findMeeting(String location, DateTime dateStart, DateTime dateEnd);

    List<Meeting> getFilterDateMeetings(DateTime dateTime);

    List<Meeting> getFilterLocationMeetings(String location);
}