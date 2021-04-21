package fr.mdasilva.mareu.data.api;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.List;

import fr.mdasilva.mareu.data.model.Meeting;
import timber.log.Timber;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class DummyMeetingApiService implements MeetingApiService {

    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeeting() {
        return meetings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * {@inheritDoc}
     * 
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public boolean findMeeting(String location, DateTime dateStart, DateTime dateEnd) {
        for (Meeting meeting1 : meetings) {
            if(meeting1.getLocation().getName().equals(location)){
                Interval meetingInteval = new Interval(meeting1.getDateStart(),meeting1.getDateEnd());
                if(meetingInteval.contains(dateStart) || meetingInteval.contains(dateEnd)){
                    Timber.d("date || "+ meeting1.toString());
                    return true;
                }
            }
        }
        return false;
    }
}