package fr.mdasilva.mareu.ui.viewmodel;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.mdasilva.mareu.data.api.DummyMeetingApiService;
import fr.mdasilva.mareu.data.model.LoadingState;

import fr.mdasilva.mareu.data.model.Meeting;

public class ListMeetingActivityViewModel extends ViewModel {

    public MutableLiveData<List<Meeting>> meetings = new MutableLiveData<>();

    public MutableLiveData<LoadingState> loadingState = new MutableLiveData<>(LoadingState.Loading);

    public ListMeetingActivityViewModel() {
        loadingState.postValue(LoadingState.Loading);
        new Handler().postDelayed(() -> {
            meetings.postValue(new DummyMeetingApiService().getMeeting());
            loadingState.postValue(LoadingState.Loaded);
        }, 2000);

    }
}
