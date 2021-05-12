package fr.mdasilva.mareu;

import fr.mdasilva.mareu.data.api.DummyMeetingGenerator;
import fr.mdasilva.mareu.data.api.MeetingApiService;
import fr.mdasilva.mareu.data.api.di.DI;
import fr.mdasilva.mareu.data.model.Meeting;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {
    private MeetingApiService sMeetingApi;

    @Before
   public void setup(){
       sMeetingApi = DI.getMeetingApiService();
   }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = sMeetingApi.getMeeting();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Meeting meetingToDelete = sMeetingApi.getMeeting().get(0);
        sMeetingApi.deleteMeeting(meetingToDelete);
        assertFalse(sMeetingApi.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void getDateFilterWithSuccess() {
        List<Meeting> meetings = sMeetingApi.getMeeting();
        DateTime meetingDate = meetings.get(0).getDateStart();
        Meeting meetingsFilter = sMeetingApi.getFilterDateMeetings(meetingDate).get(0);
        assertTrue(meetings.contains(meetingsFilter));

        //verifier que le 2elements soit False
    }

    @Test
    public void getLocationFilterWithSuccess() {
        List<Meeting> meetings = sMeetingApi.getMeeting();
        String meetinglocation = meetings.get(0).getLocation().getName();
        Meeting meetingsFilter = sMeetingApi.getFilterLocationMeetings(meetinglocation).get(0);
        assertTrue(meetings.contains(meetingsFilter));
    }

}