package fr.mdasilva.mareu.ui.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.mdasilva.mareu.data.api.DummyLocationGenerator;
import fr.mdasilva.mareu.data.api.DummyReunionApiService;
import fr.mdasilva.mareu.data.api.ReunionApiService;
import fr.mdasilva.mareu.data.model.Location;
import fr.mdasilva.mareu.databinding.ActivityAddReunionBinding;

public class AddReunionActivity extends AppCompatActivity {

    private ActivityAddReunionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReunionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initSpinner();
    }

    public void initSpinner(){
        List<Location> locations = DummyLocationGenerator.generateLocations();
        ArrayList<String> nameLocations = new ArrayList<>();

        for (int i = 0; i < locations.size(); i++) {
            nameLocations.add(locations.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameLocations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.locationSpinner.setAdapter(adapter);
        binding.locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                Location location = (Location) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),location.getName(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, AddReunionActivity.class);
        ActivityCompat.startActivity(context, intent, null);
    }
}