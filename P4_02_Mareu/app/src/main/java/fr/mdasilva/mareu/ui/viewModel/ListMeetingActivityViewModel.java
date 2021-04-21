package fr.mdasilva.mareu.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.mdasilva.mareu.data.api.MeetingApiService;
import fr.mdasilva.mareu.data.api.di.DI;
import fr.mdasilva.mareu.data.model.Meeting;

public class ListMeetingActivityViewModel extends AndroidViewModel {

    private final MeetingApiService sApiMeeting = DI.getMeetingApiService();
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

}
