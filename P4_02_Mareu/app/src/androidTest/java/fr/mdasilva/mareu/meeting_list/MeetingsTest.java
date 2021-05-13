package fr.mdasilva.mareu.meeting_list;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.mdasilva.mareu.R;
import fr.mdasilva.mareu.ui.view.ListMeetingActivity;
import fr.mdasilva.mareu.utils.DeleteViewAction;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static fr.mdasilva.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MeetingsTest {

    private static int ITEMS_COUNT = 3;

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> mActivityRule =
            new ActivityScenarioRule<>(ListMeetingActivity.class);
    /**
     * Check that the list contains an element
     */
    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        onView(allOf(isDisplayed(), withId(R.id.list_meetings)))
                .check(matches(hasMinimumChildCount(1)));
    }
    /**
     * Check that the first element is deleted
     */
    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT));
        onView(allOf(isDisplayed(), withId(R.id.list_meetings)))
                .perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(ITEMS_COUNT-1));
        ITEMS_COUNT -= 1;
    }
    /**
     * Check date filter displays an element
     */
    @Test
    public void myMeetingsList_filterLocationAction_shouldNotBeEmpty() {
        onView(withId(R.id.filter)).perform(click());
        onData(anything()).atPosition(2).inRoot(isPlatformPopup()).perform(click());
        onData(anything()).inAdapterView(allOf(isDisplayed(), withId(R.id.select_dialog_listview))).atPosition(0).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(1));
    }
    /**
     * Check Location filter displays an element
     */
    @Test
    public void myMeetingsList_filterDateAction_shouldNotBeEmpty() {
        onView(withId(R.id.filter)).perform(click());
        onData(anything()).atPosition(1).inRoot(isPlatformPopup()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 6, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.list_meetings))).check(withItemCount(1));
    }
}