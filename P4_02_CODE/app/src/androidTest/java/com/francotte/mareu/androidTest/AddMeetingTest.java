package com.francotte.mareu.androidTest;

import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.rule.ActivityTestRule;

import com.francotte.mareu.R;
import com.francotte.mareu.ListMeetingActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.francotte.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class AddMeetingTest {

    private int ITEMS_COUNT = 5;
    private ListMeetingActivity mActivity;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void nyMeetingList_addNewMeeting() {
        int hour = 19;
        int minutes = 30;
        onView(withId(R.id.add_meeting)).perform(click());
        onView(withId(R.id.object)).perform(scrollTo(), replaceText("Applications"));
        onView(withId(R.id.participants)).perform(scrollTo(), replaceText("jean@lamzone.com\n , jeanne@lamzone.com\n "));
        onView(withId(R.id.meeting_room_spinner)).perform(click());
        onView(withText("Salle C")).perform((click()));
        onView(withId(R.id.timePicker)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minutes));
        onView(withId(R.id.create)).perform(scrollTo(), click());
        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT + 1));
    }
}
