package fr.mdasilva.mareu.data.api;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.mdasilva.mareu.data.model.Meeting;

public abstract class DummyMeetingGenerator {

        public static List<Meeting> DUMMY_REUNIONS;

        static {
                DUMMY_REUNIONS = Arrays.asList(
                        new Meeting(new DateTime(2021, 4, 20, 14, 0, 0),
                                new DateTime(2021, 4, 21, 14, 0, 0), "Meeting A",
                                DummyLocationGenerator.generateLocations().get(6),
                               "maxime@lamzone.com, alex@lamzone.com"),
                        new Meeting(new DateTime(2021, 4, 20, 15, 15, 0),
                                new DateTime(2021, 4, 25, 15, 30, 0), "Meeting B",
                                DummyLocationGenerator.generateLocations().get(0),
                                "paul@lamzone.com, viviane@lamzone.com"),
                        new Meeting(new DateTime(2021, 4, 21, 10, 30, 0),
                                new DateTime(2021, 4, 22, 10, 30, 0), "Meeting C",
                                DummyLocationGenerator.generateLocations().get(1),
                                "amandine@lamzone.com, luc@lamzone.com"));
        }

        static List<Meeting> generateMeetings() {
                return new ArrayList<>(DUMMY_REUNIONS);
        }
}
