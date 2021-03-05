package fr.mdasilva.mareu.ui.adapter;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.data.model.Reunion;
import fr.mdasilva.mareu.databinding.FragmentReunionBinding;

import java.util.List;

public class MyReunionRecyclerViewAdapter extends RecyclerView.Adapter<MyReunionRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;

    public MyReunionRecyclerViewAdapter(List<Reunion> items) { mReunions = items; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final FragmentReunionBinding binding = FragmentReunionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
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

        private final FragmentReunionBinding binding;

        public ViewHolder(FragmentReunionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void update(Reunion reunion)
        {
            binding.itemListAvatar.setBackgroundTintList(ContextCompat.getColorStateList(itemView.getContext(),reunion.getLocation().getColor()));
            binding.itemListLocation.setText(itemView.getContext().getString(R.string.location,reunion.getSubject(),reunion.getDate().toString("HH:mm"),reunion.getLocation().getName()) );
            binding.itemListRecipient.setText(reunion.getStaff());

            itemView.setOnClickListener(v -> Toast.makeText(v.getContext(),
                    reunion.toString(), Toast.LENGTH_SHORT).show());
        }

    }
}