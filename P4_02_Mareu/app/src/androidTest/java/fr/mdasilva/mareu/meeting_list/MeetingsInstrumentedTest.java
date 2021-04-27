package fr.mdasilva.mareu;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.mdasilva.mareu.ui.view.ListMeetingActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class MeetingsInstrumentedTest {

    private static final int ITEMS_COUNT = 3;

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> mActivityRule =
            new ActivityScenarioRule<>(ListMeetingActivity.class);

    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        onView(allOf(isDisplayed(), withId(R.id.recyclerview)))
                .check(matches(hasMinimumChildCount(1)));
    }
    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {

    }
}