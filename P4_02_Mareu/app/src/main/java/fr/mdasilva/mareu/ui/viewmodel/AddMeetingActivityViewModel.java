package fr.mdasilva.mareu.ui.viewmodel;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import fr.mdasilva.mareu.data.api.DummyLocationGenerator;
import fr.mdasilva.mareu.data.api.MeetingApiService;
import fr.mdasilva.mareu.data.model.Location;

public class AddMeetingActivityViewModel extends ViewModel {

    public MeetingApiService smeetingApi ;

    public void validateForm(String dateStart, String dateEnd, String subject, String location, ArrayList contributor)
            throws FieldEmptyException, DateInvalidException {
       List<Location> mLocation =  generateLocations();
        Log.i("AHHHHHHHH",subject + " " +
                location + " " +
                mLocation  + " " +
                dateStart + " " +
                dateEnd + " " +
                contributor.toString());

        if (checkIsEmpty(subject)
                && checkContributor(contributor) && checkDateIsValid(dateStart, dateEnd)){

            // TODO verifier si la meeting existe checkMeetingExist
            // sinon Ajouter la reunion
/*
          smeetingApi.createMeeting(new Meeting(DateTimeFormat.forPattern("dd/MM/yy HH:mm").parseDateTime(dateStart),
                    DateTimeFormat.forPattern("dd/MM/yy HH:mm").parseDateTime(dateEnd),
                    subject,
                    mLocation,
                    contributor.toString()));
*/
        }

    }

    private boolean checkIsEmpty(String checkFieldEmpty) throws FieldEmptyException {
        if (TextUtils.isEmpty(checkFieldEmpty)) throw new FieldEmptyException();

        return true;
    }

    public void checkIsEmail(String checkFieldEmpty) throws FieldEmailException{
        if (!Patterns.EMAIL_ADDRESS.matcher(checkFieldEmpty).matches()) {
            throw new FieldEmailException();
        }
    }

    private boolean checkContributor(ArrayList contributor) throws FieldEmptyException {

        if(contributor.size() == 0 ){
            throw new FieldEmptyException();
        }
        // TODO verifier si la chaque éléments de la liste est un email
        return true;
    }

    private boolean checkDateIsValid(String dateStart, String dateEnd) throws FieldEmptyException, DateInvalidException {
  /*      if (checkIsEmpty(dateStart) && checkIsEmpty(dateEnd)) {
            throw new FieldEmptyException();
        }*/
        DateTime mdateStart = DateTimeFormat.forPattern("dd/MM/yy HH:mm").parseDateTime(dateStart);
        DateTime mdateEnd = DateTimeFormat.forPattern("dd/MM/yy HH:mm").parseDateTime(dateEnd);
        if (mdateStart.isBefore(mdateEnd)) {
            throw new DateInvalidException();
        }
        return true;
    }

    private boolean checkMeetingExist() throws MeetingExisteException {
        // TODO verifier si la meeting existe comparer si une meetings est deja creer
        throw new MeetingExisteException();
    }


    public List<Location> generateLocations() {
        return DummyLocationGenerator.generateLocations();
    }

    public static class FieldEmptyException extends Exception {
        public FieldEmptyException() {
            super("Un champ est vide.");
        }
    }

    public static class DateInvalidException extends Exception {
        public DateInvalidException() {
            super("La date de fin ne peut etre anterieur");
        }
    }

    public static class MeetingExisteException extends Exception {
        public MeetingExisteException() {
            super("Une réunion exite déja dans la salle à cette période");
        }
    }

    public static class FieldEmailException extends Exception {
        public FieldEmailException() {
            super("L'adresse email est invalide");
        }
    }
}
