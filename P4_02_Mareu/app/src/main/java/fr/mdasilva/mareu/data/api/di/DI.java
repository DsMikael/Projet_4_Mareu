package fr.mdasilva.mareu.data.api.di;

import fr.mdasilva.mareu.data.api.DummyLocationApiService;
import fr.mdasilva.mareu.data.api.DummyMeetingApiService;
import fr.mdasilva.mareu.data.api.LocationApiService;
import fr.mdasilva.mareu.data.api.MeetingApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {
    private static LocationApiService slocation = new DummyLocationApiService();
    private static MeetingApiService sMeeting = new DummyMeetingApiService();

    /**
     * Get an instance on @{@link LocationApiService}
     * @return
     */
    public static LocationApiService getLocationApiService(){
        return slocation;
    }
    /**
     * Get an instance on @{@link MeetingApiService}
     * @return
     */
    public static MeetingApiService getMeetingApiService(){
        return sMeeting;
    }

}
