package fr.mdasilva.mareu.ui.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.chip.Chip;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.data.api.DummyLocationGenerator;
import fr.mdasilva.mareu.data.model.Location;
import fr.mdasilva.mareu.databinding.ActivityAddReunionBinding;

import static fr.mdasilva.mareu.R.drawable.ic_error;

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
        initDatePicker();
        initTimePicker();
        initChipsEdit();
    }

    private void initChipsEdit() {
        binding.addContributor.setEndIconOnClickListener(v -> {
            if(TextUtils.isEmpty(binding.formSubject2.getText().toString().trim())){
                Toast.makeText(AddReunionActivity.this, "champs vide", Toast.LENGTH_LONG).show();
            }else {
                Chip chip = new Chip(AddReunionActivity.this);
                chip.setText(binding.formSubject2.getEditableText().toString().trim());
                chip.setCloseIconVisible(true);
                binding.chipGroup.addView(chip);
                binding.formSubject2.setText("");
             }
        });
        binding.formSubject2.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId== EditorInfo.IME_ACTION_DONE){
                Toast.makeText(AddReunionActivity.this, "youu", Toast.LENGTH_LONG).show();
            }
            return true;
        });
    }

    public void initSpinner(){
        List<Location> locations = DummyLocationGenerator.generateLocations();

        ArrayAdapter<Location> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void initDatePicker() {
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) ->
                binding.datePicker.setText(new DateTime(year,month,day,0,0,0).toString(DateTimeFormat.forPattern("dd/MM/yy")));
        binding.datePicker.setOnClickListener(v -> {
            DateTime dt = new DateTime();
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddReunionActivity.this, date, dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
            datePickerDialog.getDatePicker().setMinDate(dt.getMillis());
            datePickerDialog.show();
        });
    }

    public void initTimePicker() {
        DateTime dt = new DateTime();
        if(TextUtils.isEmpty(binding.timePickerStart.getText())) {
            binding.timePickerEnd.setEnabled(false);
        }

        TimePickerDialog.OnTimeSetListener timeStart = ((view, hourOfDay, minute) ->
                binding.timePickerStart.setText(new DateTime(DateTime.now().getYear(),DateTime.now().getMonthOfYear() - 1,DateTime.now().getDayOfMonth(),hourOfDay,minute,0).toString(DateTimeFormat.forPattern("HH:mm"))));

        binding.timePickerStart.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddReunionActivity.this, timeStart, dt.getHourOfDay(), dt.getMinuteOfHour(),true);
            timePickerDialog.show();
            binding.timePickerEnd.setEnabled(true);
        });

        TimePickerDialog.OnTimeSetListener timeEnd = ((view, hourOfDay, minute) ->
                binding.timePickerEnd.setText(new DateTime(DateTime.now().getYear(),DateTime.now().getMonthOfYear() - 1,DateTime.now().getDayOfMonth(),hourOfDay,minute,0).toString(DateTimeFormat.forPattern("HH:mm"))));
        binding.timePickerEnd.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddReunionActivity.this, timeEnd, dt.getHourOfDay(), dt.getMinuteOfHour(),true);
            timePickerDialog.show();
        });
        
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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