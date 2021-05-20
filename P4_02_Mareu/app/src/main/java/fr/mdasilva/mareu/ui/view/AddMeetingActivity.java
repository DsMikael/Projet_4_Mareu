package fr.mdasilva.mareu.ui.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import fr.mdasilva.mareu.data.model.Location;
import fr.mdasilva.mareu.databinding.ActivityAddMeetingBinding;
import fr.mdasilva.mareu.ui.viewModel.AddMeetingActivityViewModel;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private AddMeetingActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(AddMeetingActivityViewModel.class);

        initSpinner();
        initDateTimePicker();

        binding.addContributor.setEndIconOnClickListener(v -> initChipsEdit());

        binding.contributorEmail.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                initChipsEdit();
            }
            return true;
        });
        binding.containedButton.setOnClickListener(v -> {

            binding.formSubjectLayout.setError(null);
            binding.spinnerLayout.setError(null);
            binding.timePickerStartLayout.setError(null);
            binding.timePickerEndLayout.setError(null);
            binding.addContributor.setError(null);

            ArrayList<String> chipGroupList = new ArrayList<>();
            for (int i=0; i< binding.chipGroup.getChildCount(); i++){
                Chip chip = (Chip) binding.chipGroup.getChildAt(i);
                chipGroupList.add(i,chip.getText().toString());
            }
            try {

                viewModel.validateForm( binding.timePickerStart.getText().toString(),
                        binding.timePickerEnd.getText().toString(), binding.formSubject.getText().toString(),
                        binding.spinner.getText().toString(),
                        chipGroupList);
                setResult(Activity.RESULT_OK);
                finish();
            }catch (AddMeetingActivityViewModel.SubjectFieldException e) {
                binding.formSubjectLayout.setError(viewModel.getErrorMessage(e));
            } catch (AddMeetingActivityViewModel.DateFieldEmptyException e) {
                binding.timePickerStartLayout.setError(viewModel.getErrorMessage(e));
            } catch (AddMeetingActivityViewModel.DateEndFieldEmptyException | AddMeetingActivityViewModel.DateEndInvalidException e) {
                binding.timePickerEndLayout.setError(viewModel.getErrorMessage(e));
            } catch (AddMeetingActivityViewModel.ContributorEmptyException e) {
                binding.addContributor.setError(viewModel.getErrorMessage(e));
            } catch (AddMeetingActivityViewModel.SpinnerFieldException e) {
                binding.spinnerLayout.setError(viewModel.getErrorMessage(e));
            } catch (AddMeetingActivityViewModel.MeetingExistException e) {
                Toast.makeText(this, viewModel.getErrorMessage(e), Toast.LENGTH_SHORT).show();
            }
        });

        binding.contributorEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                binding.addContributor.setError(null);
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void initSpinner() {
        List<Location> locations = viewModel.generateLocations();

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

    public void initDateTimePicker() {
        DateTime dateTime = new DateTime();

        if (TextUtils.isEmpty(binding.timePickerStart.getText())) {
            binding.timePickerEnd.setEnabled(false);
        }

        binding.timePickerStart.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(AddMeetingActivity.this,
                                (view1, hourOfDay, minute) -> binding.timePickerStart
                                        .setText(new DateTime(year, monthOfYear + 1, dayOfMonth , hourOfDay, minute, 0)
                                                .toString(DateTimeFormat.forPattern("dd/MM/yy HH:mm"))),
                                dateTime.getHourOfDay(), dateTime.getMinuteOfHour(), true);
                        timePickerDialog.show();
                    }, dateTime.getYear(), dateTime.getMonthOfYear() - 1, dateTime.getDayOfMonth());
            datePickerDialog.getDatePicker().setMinDate(DateTime.now().getMillis());
            datePickerDialog.show();
            binding.timePickerEnd.setEnabled(true);
        });

        binding.timePickerEnd.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(AddMeetingActivity.this,
                                (view1, hourOfDay1, minute1) -> binding.timePickerEnd
                                        .setText(new DateTime(year, monthOfYear + 1, dayOfMonth, hourOfDay1, minute1, 0)
                                                .toString(DateTimeFormat.forPattern("dd/MM/yy HH:mm"))),
                                dateTime.getHourOfDay(), dateTime.getMinuteOfHour(), true);
                        timePickerDialog.show();
                    }, dateTime.getYear(), dateTime.getMonthOfYear() - 1 , dateTime.getDayOfMonth());
            datePickerDialog.getDatePicker().setMinDate(DateTime.now().getMillis());
            datePickerDialog.show();
        });
    }

    private void initChipsEdit() {
        String chipText = binding.contributorEmail.getText().toString().trim();
        if (!TextUtils.isEmpty(chipText)) {
            Chip chip = new Chip(AddMeetingActivity.this);
            chip.setText(chipText);
            chip.setCloseIconVisible(true);
            try {
                viewModel.checkIsEmail(chipText);
                binding.chipGroup.addView(chip);
                binding.contributorEmail.setText("");
                chip.setOnCloseIconClickListener(v -> binding.chipGroup.removeView(chip));
            }catch (AddMeetingActivityViewModel.ContributorEmailException e){
                binding.addContributor.setError(viewModel.getErrorMessage(e));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void navigate(Activity context,int requestCode) {
        Intent intent = new Intent(context, AddMeetingActivity.class);
        ActivityCompat.startActivityForResult(context,  intent, requestCode ,null);
    }

}