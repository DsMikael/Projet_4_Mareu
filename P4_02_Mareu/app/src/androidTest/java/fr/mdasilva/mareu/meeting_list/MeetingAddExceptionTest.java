package fr.mdasilva.mareu.meeting_list;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.ui.view.AddMeetingActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MeetingAddExceptionTest {


    @Rule
    public ActivityScenarioRule<AddMeetingActivity> mActivityRule =
            new ActivityScenarioRule<>(AddMeetingActivity.class);

    /**
     * Add a meeting and verify that an element has been added
     */
    @Test
    public void myMeetingsList_checkException() {
        onView(withId(R.id.add_meeting_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.containedButton)).perform(click());
        onView(withText(R.string.e_is_empty)).check(matches(isDisplayed()));
        onView(withId(R.id.form_subject)).perform(typeText("Meeting D"));

        onView(withId(R.id.containedButton)).perform(click());
        onView(withText(R.string.e_choose_location)).check(matches(isDisplayed()));
        // Select Spinner
        onView(withId(R.id.spinner)).perform(click());
        onData(anything()).atPosition(4).inRoot(isPlatformPopup()).perform(click());

        onView(withId(R.id.containedButton)).perform(click());
        onView(withText(R.string.e_is_empty)).check(matches(isDisplayed()));
        // Select Date Start
        onView(withId(R.id.time_picker_start)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 29));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(12, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_start)).check(matches(allOf(withText("29/06/21 12:00"),
                isDisplayed())));

        onView(withId(R.id.containedButton)).perform(click());
        onView(withText(R.string.e_is_empty)).check(matches(isDisplayed()));
        // Select Date end
        onView(withId(R.id.time_picker_end)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 28));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(11, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_end)).check(matches(allOf(withText("28/06/21 11:00"),
                isDisplayed())));

        onView(withId(R.id.containedButton)).perform(click());
        onView(withText(R.string.e_time_before)).check(matches(isDisplayed()));
        // Select Date end
        onView(withId(R.id.time_picker_end)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 29));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(13, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_end)).check(matches(allOf(withText("29/06/21 13:00"),
                isDisplayed())));

        onView(withId(R.id.containedButton)).perform(click());
        onView(withText(R.string.e_min_contibutor)).check(matches(isDisplayed()));
        // Add Contributor
        onView(withId(R.id.form_subject2)).perform(click());
        onView(withId(R.id.form_subject2)).perform(typeText("mickael"))
                .perform(pressImeActionButton());
        onView(withText(R.string.e_email_invalid)).check(matches(isDisplayed()));

        onView(withId(R.id.form_subject2)).perform(click());
        onView(withId(R.id.form_subject2)).perform(typeText("mickael@lamzone.com"))
                .perform(pressImeActionButton());
        onView(withId(R.id.containedButton)).perform(click());


    }

}