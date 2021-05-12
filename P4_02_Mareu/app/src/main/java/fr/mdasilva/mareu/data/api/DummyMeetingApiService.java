package fr.mdasilva.mareu.data.api;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
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

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public boolean findMeeting(String location, DateTime dateStart, DateTime dateEnd) {
        for (Meeting meeting : meetings) {
            if (meeting.getLocation().getName().equals(location)) {
                Interval meetingInteval = new Interval(meeting.getDateStart(), meeting.getDateEnd());
                Interval meetingInteval1 = new Interval(dateStart, dateEnd);
              if(meetingInteval.contains(dateStart) || meetingInteval.contains(dateEnd) ||
                      meetingInteval1.contains(meeting.getDateStart()) || meetingInteval1.contains( meeting.getDateEnd()))
              {
                  if(!meeting.getDateStart().isEqual(dateEnd) && !meeting.getDateEnd().isEqual(dateStart))
                  {
                      return true;
                  }
              }
            }
        }
        return false;
    }

    public List<Meeting> getFilterDateMeetings(DateTime dateTime) {
        List<Meeting> meetingDateList = new ArrayList<>();
        for (Meeting meeting : meetings) {
            DateTime mdateStart = new DateTime(meeting.getDateStart().getYear(),meeting.getDateStart().getMonthOfYear(),meeting.getDateStart().getDayOfMonth(),0,0);
            DateTime mdateEnd = new DateTime(meeting.getDateEnd().getYear(),meeting.getDateEnd().getMonthOfYear(),meeting.getDateEnd().getDayOfMonth(),0,0);
            Timber.d(String.valueOf(meeting));
            Interval meetingInteval = new Interval(mdateStart, mdateEnd);
            Timber.d(String.valueOf(meetingInteval));
            if (meetingInteval.contains(dateTime) || mdateStart.isEqual(dateTime) || mdateEnd.isEqual(dateTime)) {
                meetingDateList.add(meeting);
            }
        }
        return meetingDateList;
    }

    public List<Meeting> getFilterLocationMeetings(String location) {
        List<Meeting> meetingLocationList = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getLocation().getName().equals(location)) {
                meetingLocationList.add(meeting);
            }
        }
        return meetingLocationList;
    }
}