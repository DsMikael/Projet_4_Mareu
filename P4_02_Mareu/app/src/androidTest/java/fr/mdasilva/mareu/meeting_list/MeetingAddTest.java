package fr.mdasilva.mareu.meeting_list;

import android.os.SystemClock;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.ui.view.ListMeetingActivity;

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
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static fr.mdasilva.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MeetingAddTest {

    private static int ITEMS_COUNT = 3;

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> mActivityRule =
            new ActivityScenarioRule<>(ListMeetingActivity.class);
    /**
     * Add a meeting and verify that an element has been added
     */
    @Test
    public void myMeetingsList_addMeeting_shouldAddAndRemoveItem() {
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.add_meeting_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.form_subject)).perform(replaceText("Meeting D"), closeSoftKeyboard());
        // Select Spinner
        onView(withId(R.id.spinner)).perform(click());
        onData(anything()).atPosition(4).inRoot(isPlatformPopup()).perform(scrollTo(), click());
        // Select Date Start
        onView(withId(R.id.time_picker_start)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 29));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(12, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_start)).check(matches(allOf(withText("29/06/21 12:00"),
                isDisplayed())));
        // Select Date end
        onView(withId(R.id.time_picker_end)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2021, 6, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(15, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.time_picker_end)).check(matches(allOf(withText("30/06/21 15:00"),
                isDisplayed())));
        // Add Contributor
        onView(withId(R.id.form_subject2)).perform(click());
        onView(withId(R.id.form_subject2)).perform(replaceText("mickael@lamzone.com"), closeSoftKeyboard())
                .perform(pressImeActionButton());
        SystemClock.sleep(1000);
        // Click Add Meeting button
        onView(withId(R.id.containedButton)).perform(scrollTo(), click());
        //Check that the list contains one more item
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT + 1));
    }
}
