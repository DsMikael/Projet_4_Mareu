package fr.mdasilva.mareu.ui.viewmodel;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import fr.mdasilva.mareu.data.api.di.DI;
import fr.mdasilva.mareu.data.api.LocationApiService;
import fr.mdasilva.mareu.data.api.MeetingApiService;
import fr.mdasilva.mareu.data.model.Location;
import fr.mdasilva.mareu.data.model.Meeting;
import timber.log.Timber;

public class AddMeetingActivityViewModel extends ViewModel {

    public MeetingApiService sMeetingApi = DI.getMeetingApiService();
    public LocationApiService sLocationApi = DI.getLocationApiService();

    public void validateForm(String dateStart, String dateEnd, String subject, String location, ArrayList<String> contributor)
            throws DateEndInvalidException, SubjectFieldException, ContributorEmptyException, DateFieldEmptyException, DateEndFieldEmptyException, SpinnerFieldException, MeetingExistException {

 //      Location mLocation = generateLocations().get(positionLocation(location));
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
  /*      if(checkMeetingExist(mdateStart, mdateEnd, mLocation)){
            throw new MeetingExistException();
        }


        sMeetingApi.createMeeting(new Meeting(mdateStart,
                mdateEnd,
                subject,
                mLocation,
                contributor.toString()));
*/
        Timber.d(subject + " " +
                positionLocation(location) + " " +
                dateStart + " " +
                dateEnd + " " +
                contributor.toString());

  //      Timber.d(sMeetingApi.getMeeting().toString());

    }

    public void checkIsEmail(String checkFieldEmpty) throws ContributorEmailException{
        if (!Patterns.EMAIL_ADDRESS.matcher(checkFieldEmpty).matches()) {throw new ContributorEmailException(); }
    }

    private boolean checkMeetingExist(DateTime mdateStart, DateTime mdateEnd, Location location)
            throws MeetingExistException {
        // TODO verifier si la meeting existe comparer si une meetings est deja creer
        throw new MeetingExistException();
    }

    public List<Location> generateLocations() {
        return sLocationApi.getLocation();
    }

    public int positionLocation(String location) {
        int i = 0;
        for(Location c : generateLocations()){
            if(c.getName().equals(location)){ break;}
            i++;
        }
        return i;
    }

    //Exception List
    public static class SubjectFieldException extends Exception {
        public SubjectFieldException() { super("Ce champs ne peut etre vide."); }
    }
    public static class DateFieldEmptyException extends Exception {
        public DateFieldEmptyException() { super("Ce champs ne peut etre vide."); }
    }
    public static class DateEndFieldEmptyException extends Exception {
        public DateEndFieldEmptyException() { super("Ce champs ne peut etre vide."); }
    }
    public static class SpinnerFieldException extends Exception {
        public SpinnerFieldException() { super("Vous devez choisir une salle.");}
    }
    public static class DateEndInvalidException extends Exception {
        public DateEndInvalidException() { super("La date/ l'heure de fin ne peut etre anterieur au debut de la réunion."); }
    }
    public static class ContributorEmailException extends Exception {
        public ContributorEmailException() {super("L'adresse email est invalide");}
    }
    public static class ContributorEmptyException extends Exception {
        public ContributorEmptyException() { super("Il doit y avoir au minimun 1 participant.");}
    }
    public static class MeetingExistException extends Exception {
        public MeetingExistException() { super("Une réunion exite déja dans la salle à cette période");}
    }
}
