package fr.mdasilva.mareu.ui.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.databinding.ActivityListMeetingBinding;
import fr.mdasilva.mareu.ui.adapter.MyMeetingRecyclerViewAdapter;
import fr.mdasilva.mareu.ui.viewmodel.ListMeetingActivityViewModel;

public class ListMeetingActivity extends AppCompatActivity {

    private ListMeetingActivityViewModel viewModel;
    private ActivityListMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        viewModel = new ViewModelProvider(this).get(ListMeetingActivityViewModel.class);
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        binding.addMeeting.setOnClickListener(v -> {
            AddMeetingActivity.navigate(ListMeetingActivity.this);
            // Toast.makeText(ListMeetingActivity.this, "whaooooo",
            // Toast.LENGTH_LONG).show();
        });
        observeMeetings();

    }

    private void observeMeetings() {
        viewModel.meetings.observe(this,
                meetings -> binding.recyclerview.setAdapter(new MyMeetingRecyclerViewAdapter(meetings)));
    }


    @Override
    protected void onResume() {
        super.onResume();
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

}