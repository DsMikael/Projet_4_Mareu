package fr.mdasilva.mareu.ui.viewmodel;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.mdasilva.mareu.data.api.DummyMeetingApiService;

import fr.mdasilva.mareu.data.model.Meeting;

public class ListMeetingActivityViewModel extends ViewModel {

    public MutableLiveData<List<Meeting>> meetings = new MutableLiveData<>();


    public ListMeetingActivityViewModel() {
        new Handler().postDelayed(() -> {
            meetings.postValue(new DummyMeetingApiService().getMeeting());
        }, 2000);

    }
}
