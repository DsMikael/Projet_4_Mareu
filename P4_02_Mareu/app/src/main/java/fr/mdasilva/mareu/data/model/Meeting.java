package fr.mdasilva.mareu.data.model;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class Meeting implements Serializable {

    private DateTime dateStart;
    private DateTime dateEnd;
    private String subject;
    private Location location;
    private List<String> staff;

    /**
     * Constructor
     *  @param dateStart
     * @param dateEnd
     * @param subject
     * @param location
     * @param staff
     */
    public Meeting(DateTime dateStart, DateTime dateEnd, String subject, Location location, List<String> staff) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.subject = subject;
        this.location = location;
        this.staff = staff;
    }

    public DateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(DateTime dateStart) {
        this.dateStart = dateStart;
    }

    public DateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(DateTime dateEnd) {
        this.dateEnd = dateStart;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getStaff() {
        return staff;
    }

    public void setStaff(List<String> staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Meeting{" + "dateStart=" + dateStart + ", dateEnd=" + dateStart + ", subject='" + subject + '\'' + ", location='" + location
                + '\'' + ", staff=" + staff + '}';
    }

    public String getStafftoString() {
        return TextUtils.join(", " , staff);
    }
}
