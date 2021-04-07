package fr.mdasilva.mareu.ui.adapter;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.data.api.MeetingApiService;
import fr.mdasilva.mareu.data.api.di.DI;
import fr.mdasilva.mareu.data.model.Meeting;
import fr.mdasilva.mareu.databinding.FragmentMeetingBinding;

import java.util.List;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final FragmentMeetingBinding binding = FragmentMeetingBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting meeting = mMeetings.get(position);
        holder.update(meeting);
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentMeetingBinding binding;
        protected MeetingApiService sMeetingApi = DI.getMeetingApiService();

        public ViewHolder(FragmentMeetingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void update(Meeting meeting) {
            binding.itemListAvatar.setBackgroundTintList(
                    ContextCompat.getColorStateList(itemView.getContext(), meeting.getLocation().getColor()));
            binding.itemListLocation.setText(itemView.getContext().getString(R.string.location, meeting.getSubject(),
                    meeting.getDateStart().toString("HH:mm"), meeting.getLocation().getName()));
            binding.itemListRecipient.setText(meeting.getStaff());

            itemView.setOnClickListener(
                    v -> Toast.makeText(v.getContext(), meeting.toString(), Toast.LENGTH_SHORT).show());

            binding.itemListDeleteButton.setOnClickListener(v -> sMeetingApi.deleteMeeting(meeting));
        }

    }
}