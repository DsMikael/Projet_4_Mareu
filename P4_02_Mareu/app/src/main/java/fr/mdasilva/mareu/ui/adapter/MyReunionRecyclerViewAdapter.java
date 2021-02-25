package fr.mdasilva.mareu.ui.adapter;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.data.model.Reunion;
import fr.mdasilva.mareu.ui.view.AddReunionActivity;

import java.util.List;

public class MyReunionRecyclerViewAdapter extends RecyclerView.Adapter<MyReunionRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;

    public MyReunionRecyclerViewAdapter(List<Reunion> items) { mReunions = items; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Reunion reunion = mReunions.get(position);
        holder.update(reunion);
    }

    @Override
    public int getItemCount()
    {
        return mReunions.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView colorLocation;
        private final TextView location;
        private final TextView recipient;

        public ViewHolder(View view) {
            super(view);
            colorLocation = view.findViewById(R.id.item_list_avatar);
            location = view.findViewById(R.id.item_list_location);
            recipient = view.findViewById(R.id.item_list_recipient);
        }

        @SuppressLint("SetTextI18n")
        public void update(Reunion reunion)
        {
            colorLocation.setBackgroundTintList(ContextCompat.getColorStateList(itemView.getContext(),reunion.getLocation().getColor()));
            location.setText(reunion.getSubject() + " - " + reunion.getDate().toString("HH:mm") + " - " + reunion.getLocation().getName());
            recipient.setText(reunion.getStaff());

            itemView.setOnClickListener(v -> Toast.makeText(v.getContext(),
                    reunion.toString(), Toast.LENGTH_SHORT).show());
        }

    }
}