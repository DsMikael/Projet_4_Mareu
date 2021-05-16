package fr.mdasilva.mareu.meeting_list;

import android.os.SystemClock;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.ui.view.AddMeetingActivity;

import static android.os.SystemClock.*;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MeetingAddExceptionTest {


    @Rule
    public ActivityScenarioRule<AddMeetingActivity> mActivityRule =
            new ActivityScenarioRule<>(AddMeetingActivity.class);

    private View decorView;

    @Before
    public void setUp() {
        mActivityRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    /**
     * Add a meeting and verify that an element has been added
     */
    @Test
    public void myMeetingsList_checkException() {
        onView(withId(R.id.add_meeting_layout)).check(matches(isDisplayed()));
        SystemClock.sleep(700);
        onView(withId(R.id.containedButton)).perform(scrollTo(), click());
        onView(withText(R.string.e_is_empty)).check(matches(isDisplayed()));
        onView(withId(R.id.form_subject)).perform(scrollTo(), replaceText("Meeting D"), closeSoftKeyboard());

        onView(withId(R.id.containedButton)).perform(scrollTo(), click());
        onView(withText(R.string.e_choose_location)).check(matches(isDisplayed()));
        // Select Spinner
        onView(withId(R.id.spinner)).perform(scrollTo(), click());
        onData(anything()).atPosition(6).inRoot(isPlatformPopup()).perform(scrollTo(), click());

        onView(withId(R.id.containedButton)).perform(scrollTo(), click());
        onView(withText(R.string.e_is_empty)).check(matches(isDisplayed()));
        // Select Date Start
        onView(withId(R.id.time_picker_start)).perform(scrollTo(), click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(10, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_start)).check(matches(allOf(withText("30/06/21 10:30"),
                isDisplayed())));

        onView(withId(R.id.containedButton)).perform(scrollTo(), click());
        onView(withText(R.string.e_is_empty)).check(matches(isDisplayed()));
        // Select Date end
        onView(withId(R.id.time_picker_end)).perform(scrollTo(),click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 28));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(11, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_end)).check(matches(allOf(withText("28/06/21 11:00"),
                isDisplayed())));

        onView(withId(R.id.containedButton)).perform(scrollTo(), click());
        onView(withText(R.string.e_time_before)).check(matches(isDisplayed()));
        // Select Date end
        onView(withId(R.id.time_picker_end)).perform(scrollTo(), click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(15, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_end)).check(matches(allOf(withText("30/06/21 15:00"),
                isDisplayed())));

        onView(withId(R.id.containedButton)).perform(scrollTo(), click());
        onView(withText(R.string.e_min_contibutor)).check(matches(isDisplayed()));
        // Add Contributor
        onView(withId(R.id.form_subject2)).perform(scrollTo(), click());
        onView(withId(R.id.form_subject2)).perform(replaceText("mickael"), closeSoftKeyboard())
                .perform(pressImeActionButton());
        onView(withText(R.string.e_email_invalid)).check(matches(isDisplayed()));

        onView(withId(R.id.form_subject2)).perform(click());
        onView(withId(R.id.form_subject2)).perform(replaceText("mickael@lamzone.com"), closeSoftKeyboard())
                .perform(pressImeActionButton());
        onView(withId(R.id.containedButton)).perform(scrollTo(), click());
        onView(withText(R.string.e_meeting_exist))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
        SystemClock.sleep(2000);
        // Select Date Start
        onView(withId(R.id.time_picker_start)).perform(scrollTo(), click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(13, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_start)).check(matches(allOf(withText("30/06/21 13:00"),
                isDisplayed())));
        onView(withId(R.id.containedButton)).perform(scrollTo(), click());


    }


}