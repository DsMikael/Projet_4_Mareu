package fr.mdasilva.mareu.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(ListReunionActivity activity) {
        Intent intent = new Intent(activity, AddReunionActivity.class);
        ActivityCompat.startActivity(activity, intent, null);

    }
}
