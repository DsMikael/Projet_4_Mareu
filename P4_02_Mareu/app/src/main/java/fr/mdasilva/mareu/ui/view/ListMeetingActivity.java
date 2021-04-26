package fr.mdasilva.mareu.ui.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import fr.mdasilva.mareu.R;
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

    private void dateFilter(){
        DateTime dateTime = new DateTime();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    DateTime date = new DateTime(year, monthOfYear + 1, dayOfMonth , 0, 0);
                    Interval dateInteval = new Interval(date, date.plusDays(1));
                    if (dateInteval.contains(date)) {
                        viewModel.dateFilter(date);
                    }
                }, dateTime.getYear(), dateTime.getMonthOfYear() - 1, dateTime.getDayOfMonth());
        datePickerDialog.getDatePicker().setMinDate(DateTime.now().getMillis());
        datePickerDialog.show();
    }

    private void locationFilter(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.a_choose_location).setItems(viewModel.generateLocations(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String location = viewModel.getLocation().get(which).getName();
                        viewModel.locationFilter(location);
                    }
                });
        builder.create().show();
    }

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { switch (item.getItemId()) {
            case R.id.filter_date:
                dateFilter();
                return true;
            case R.id.filter_location:
                locationFilter();
                return true;
            case R.id.filter_all:
                viewModel.refreshList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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