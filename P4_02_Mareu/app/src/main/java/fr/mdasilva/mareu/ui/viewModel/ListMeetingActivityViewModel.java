package fr.mdasilva.mareu.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.joda.time.DateTime;

import java.util.List;

import fr.mdasilva.mareu.data.api.LocationApiService;
import fr.mdasilva.mareu.data.api.MeetingApiService;
import fr.mdasilva.mareu.data.api.di.DI;
import fr.mdasilva.mareu.data.model.Location;
import fr.mdasilva.mareu.data.model.Meeting;

public class ListMeetingActivityViewModel extends AndroidViewModel {

    private final MeetingApiService sApiMeeting = DI.getMeetingApiService();
    private final LocationApiService sLocationApi = DI.getLocationApiService();
    public MutableLiveData<List<Meeting>> meetings = new MutableLiveData<>(sApiMeeting.getMeeting());

    public ListMeetingActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void refreshList() {
        meetings.postValue(sApiMeeting.getMeeting());
    }

    public void deleteMeeting(Meeting meeting) {
        sApiMeeting.deleteMeeting(meeting);
        refreshList();
    }

    public void dateFilter(DateTime dateTime) {
        meetings.postValue(sApiMeeting.getFilterDateMeetings(dateTime));
    }

    public void locationFilter(String location) {
        meetings.postValue(sApiMeeting.getFilterLocationMeetings(location));
    }

    public CharSequence[] generateLocations() {
        List<Location> locations = sLocationApi.getLocation();
        CharSequence[] myArray = new CharSequence[locations.size()];
        for(int i = 0; i < locations.size(); i++){
            myArray[i] = locations.get(i).getName(); // Whichever string you wanna store here from custom object
        }
        return myArray;

    }

    public List<Location> getLocation() {
        return sLocationApi.getLocation();
    }
}
