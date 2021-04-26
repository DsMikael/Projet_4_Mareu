package fr.mdasilva.mareu.ui.viewModel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.data.api.di.DI;
import fr.mdasilva.mareu.data.api.LocationApiService;
import fr.mdasilva.mareu.data.api.MeetingApiService;
import fr.mdasilva.mareu.data.model.Location;
import fr.mdasilva.mareu.data.model.Meeting;

public class AddMeetingActivityViewModel extends AndroidViewModel {

    public MeetingApiService sMeetingApi = DI.getMeetingApiService();
    public LocationApiService sLocationApi = DI.getLocationApiService();

    public AddMeetingActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void validateForm(String dateStart, String dateEnd, String subject, String location, ArrayList<String> contributor)
            throws DateEndInvalidException, SubjectFieldException, ContributorEmptyException, DateFieldEmptyException, DateEndFieldEmptyException, SpinnerFieldException, MeetingExistException {

        if (TextUtils.isEmpty(subject)) {
            throw new SubjectFieldException();
        }
        if (TextUtils.isEmpty(location)) {
            throw new SpinnerFieldException();
        }
        if (TextUtils.isEmpty(dateStart)) {
            throw new DateFieldEmptyException();
        }
        if (TextUtils.isEmpty(dateEnd)) {
            throw new DateEndFieldEmptyException();
        }
        if(contributor.size() == 0 ){
            throw new ContributorEmptyException();
        }

        DateTime mdateStart = DateTimeFormat.forPattern("dd/MM/yy HH:mm").parseDateTime(dateStart);
        DateTime mdateEnd = DateTimeFormat.forPattern("dd/MM/yy HH:mm").parseDateTime(dateEnd);
        if (mdateStart.isAfter(mdateEnd)) {
            throw new DateEndInvalidException();
        }
        if(sMeetingApi.findMeeting(location,mdateStart, mdateEnd)) {
            throw new MeetingExistException();
        }

        sMeetingApi.createMeeting(new Meeting(mdateStart,
                mdateEnd,
                subject,
                sLocationApi.findLocationByName(location),
                contributor));
    }

    public void checkIsEmail(String checkFieldEmpty) throws ContributorEmailException{
        if (!Patterns.EMAIL_ADDRESS.matcher(checkFieldEmpty).matches()) {throw new ContributorEmailException(); }
    }

    public List<Location> generateLocations() {
        return sLocationApi.getLocation();
    }

    public String getErrorMessage(Exception e) {
        if(e instanceof SubjectFieldException || e instanceof DateFieldEmptyException || e instanceof DateEndFieldEmptyException){
            return getApplication().getString(R.string.e_is_empty);
        }
        if(e instanceof ContributorEmptyException){ return getApplication().getString(R.string.e_min_contibutor); }
        if(e instanceof SpinnerFieldException){ return getApplication().getString(R.string.e_choose_location); }
        if(e instanceof DateEndInvalidException){ return getApplication().getString(R.string.e_time_before); }
        if(e instanceof ContributorEmailException){ return getApplication().getString(R.string.e_email_invalid); }
        if(e instanceof MeetingExistException){ return getApplication().getString(R.string.e_meeting_exist); }
        return null;
    }

    public static class SubjectFieldException extends Exception {
        public SubjectFieldException() { super("This field cannot be empty."); }
    }
    public static class DateFieldEmptyException extends Exception {
        public DateFieldEmptyException() { super("This field cannot be empty."); }
    }
    public static class DateEndFieldEmptyException extends Exception {
        public DateEndFieldEmptyException() { super("This field cannot be empty."); }
    }
    public static class SpinnerFieldException extends Exception {
        public SpinnerFieldException() { super("You have to choose a room.");}
    }
    public static class DateEndInvalidException extends Exception {
        public DateEndInvalidException() { super("The end date / time cannot be earlier than the start of the meeting."); }
    }
    public static class ContributorEmailException extends Exception {
        public ContributorEmailException() {super("Email is invalid");}
    }
    public static class ContributorEmptyException extends Exception {
        public ContributorEmptyException() { super("There must be at least 1 participant.");}
    }
    public static class MeetingExistException extends Exception {
        public MeetingExistException() { super("\n" + "A meeting exists in the room at this time.");}
    }
}
