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
        List<Meeting> expectedNeighbours = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Meeting meetingToDelete = sMeetingApi.getMeeting().get(0);
        sMeetingApi.deleteMeeting(meetingToDelete);
        assertFalse(sMeetingApi.getMeeting().contains(meetingToDelete));
    }
 //   List<Meeting> getFilterDateMeetings(DateTime dateTime);

    @Test
    public void getDateFilterWithSuccess() {

    }


//    List<Meeting> getFilterLocationMeetings(String location);
}