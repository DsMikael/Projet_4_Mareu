package fr.mdasilva.mareu.data.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.data.model.Location;

public abstract class DummyLocationGenerator {

    public static List<Location> DUMMY_LOCATION = Arrays.asList(
            new Location(R.color.MARIO, "Mario"),
            new Location(R.color.LUIGI, "Luigi"),
            new Location(R.color.PEACH, "Peach"),
            new Location(R.color.TOAD, "Toad"),
            new Location(R.color.YOSHI, "Yoshi"),
            new Location(R.color.DONKEY, "Donkey"),
            new Location(R.color.KOOPA, "Koopa"),
            new Location(R.color.BOO, "Boo"),
            new Location(R.color.WARIO, "Wario"),
            new Location(R.color.BOWSER, "Bowser")
    );

    static List<Location> generateLocations() {
        return new ArrayList<>(DUMMY_LOCATION);
    }


}
