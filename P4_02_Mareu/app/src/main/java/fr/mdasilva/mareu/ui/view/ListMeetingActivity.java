package fr.mdasilva.mareu.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.data.api.DummyMeetingApiService;
import fr.mdasilva.mareu.data.api.MeetingApiService;
import fr.mdasilva.mareu.data.event.DeleteMeetingEvent;
import fr.mdasilva.mareu.databinding.ActivityListMeetingBinding;
import fr.mdasilva.mareu.ui.adapter.MyMeetingRecyclerViewAdapter;
import fr.mdasilva.mareu.ui.viewModel.ListMeetingActivityViewModel;

public class ListMeetingActivity extends AppCompatActivity {

    private ListMeetingActivityViewModel viewModel;
    private ActivityListMeetingBinding binding;
    private static final int requestCode = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ListMeetingActivityViewModel.class);
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        binding.addMeeting.setOnClickListener(v -> {
            AddMeetingActivity.navigate(ListMeetingActivity.this, requestCode);
            // Toast.makeText(ListMeetingActivity.this, "whaooooo",
            // Toast.LENGTH_LONG).show();
        });

        observeMeetings();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ListMeetingActivity.requestCode) {
            if (Activity.RESULT_OK==resultCode) {
                viewModel.refreshList();
            }
        }
    }

    private void observeMeetings() {
        viewModel.meetings.observe(this,
                meetings -> binding.recyclerview.setAdapter(new MyMeetingRecyclerViewAdapter(meetings)));
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshList();
    }

 */
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_meeting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
        viewModel.deleteMeeting(event.meeting);
    }

}