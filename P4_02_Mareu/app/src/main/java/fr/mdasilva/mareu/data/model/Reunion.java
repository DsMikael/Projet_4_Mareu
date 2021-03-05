package fr.mdasilva.mareu.data.model;

import java.io.Serializable;
import org.joda.time.DateTime;

public class Reunion implements Serializable {

    private int id;
    private DateTime date;
    private String subject;
    private Location location;
    private String staff;

    /**
     * Constructor
     * @param id
     * @param date
     * @param subject
     * @param location
     */
    public Reunion(int id, DateTime date, String subject, Location location, String staff){
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.location = location;
        this.staff = staff;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
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

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Reunion{" +
                "id=" + id +
                ", date=" + date +
                ", subject='" + subject + '\'' +
                ", location='" + location + '\'' +
                ", staff=" + staff +
                '}';
    }
}
