package fr.mdasilva.mareu.data.api;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.mdasilva.mareu.data.model.Reunion;

public abstract class DummyReunionGenerator {

    public static List<Reunion> DUMMY_REUNIONS = Arrays.asList(
            new Reunion(1, new DateTime(2020,3,17,14,0,0), "Reunion A", DummyLocationGenerator.generateLocations().get(2), "maxime@lamzone.com, alex@lamzone.com"),
            new Reunion(2, new DateTime(2020,3,17,15,15,0), "Reunion B", DummyLocationGenerator.generateLocations().get(0), "paul@lamzone.com, viviane@lamzone.com"),
            new Reunion(3, new DateTime(2020,3,18,10,30,0), "Reunion C", DummyLocationGenerator.generateLocations().get(1), "amandine@lamzone.com, luc@lamzone.com")
    );

     static List<Reunion> generateReunions() {
         return new ArrayList<>(DUMMY_REUNIONS);
    }
}
