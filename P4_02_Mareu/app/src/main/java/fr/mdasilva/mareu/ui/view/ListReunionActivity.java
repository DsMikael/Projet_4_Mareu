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
import fr.mdasilva.mareu.databinding.ActivityListReunionBinding;
import fr.mdasilva.mareu.ui.adapter.MyReunionRecyclerViewAdapter;
import fr.mdasilva.mareu.ui.viewmodel.ListReunionActivityViewModel;
import fr.mdasilva.mareu.data.model.LoadingState;

public class ListReunionActivity extends AppCompatActivity {

    private ListReunionActivityViewModel viewModel;
    private ActivityListReunionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        viewModel = new ViewModelProvider(this).get(ListReunionActivityViewModel.class);
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        binding.addReunion.setOnClickListener(v -> {
            AddReunionActivity.navigate(ListReunionActivity.this);
         //   Toast.makeText(ListReunionActivity.this, "whaooooo", Toast.LENGTH_LONG).show();
        });

       observeLoadingState();
       observeReunions();
    }

    private void observeReunions()
    {
        viewModel.reunions.observe(this, reunions -> binding.recyclerview.setAdapter(new MyReunionRecyclerViewAdapter(reunions)));
    }

    private void observeLoadingState()
    { viewModel.loadingState.observe(this, loadingState ->
        {if (loadingState == LoadingState.Loading)
            { binding.loaderContainer.setVisibility(View.VISIBLE); }
            else {
                binding.loaderContainer.setVisibility(View.INVISIBLE);}
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_reunion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}