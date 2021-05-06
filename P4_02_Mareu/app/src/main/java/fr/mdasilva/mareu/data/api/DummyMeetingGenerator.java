package fr.mdasilva.mareu.data.api;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.mdasilva.mareu.data.model.Meeting;

public abstract class DummyMeetingGenerator {

        public static List<Meeting> DUMMY_MEETINGS;

        static {
                DUMMY_MEETINGS = Arrays.asList(
                        new Meeting(new DateTime(2021, 5, 6, 10, 0, 0),
                                new DateTime(2021, 5, 6, 11, 0, 0), "Meeting A",
                                DummyLocationGenerator.generateLocations().get(6),
                               Arrays.asList("maxime@lamzone.com", "alex@lamzone.com")),
                        new Meeting(new DateTime(2021, 4, 27, 15, 15, 0),
                                new DateTime(2021, 4, 29, 15, 30, 0), "Meeting B",
                                DummyLocationGenerator.generateLocations().get(0),
                                Arrays.asList("paul@lamzone.com", "viviane@lamzone.com")),
                        new Meeting(new DateTime(2021, 4, 30, 10, 30, 0),
                                new DateTime(2021, 4, 30, 14, 30, 0), "Meeting C",
                                DummyLocationGenerator.generateLocations().get(1),
                                Arrays.asList("amandine@lamzone.com", "luc@lamzone.com")));
        }

        static List<Meeting> generateMeetings() {
                return new ArrayList<>(DUMMY_MEETINGS);
        }
}
