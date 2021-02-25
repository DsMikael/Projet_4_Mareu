package fr.mdasilva.mareu.ui.viewmodel;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.mdasilva.mareu.data.api.DummyReunionApiService;
import fr.mdasilva.mareu.data.model.LoadingState;

import fr.mdasilva.mareu.data.model.Reunion;

public class ListReunionActivityViewModel extends ViewModel {

    public MutableLiveData<List<Reunion>> reunions = new MutableLiveData<>();

    public MutableLiveData<LoadingState> loadingState = new MutableLiveData<>(LoadingState.Loading);

    public void initList() {
        loadingState.postValue(LoadingState.Loading);
        new Handler().postDelayed(() -> {
            reunions.postValue(new DummyReunionApiService().getReunion());
            loadingState.postValue(LoadingState.Loaded);
        }, 2000);
    }
}
