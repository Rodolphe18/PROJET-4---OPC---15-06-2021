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
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.francotte.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class FilterTimeTest {

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
    public void myMeetingList_filterTime() {
        int hour = 14;
        int minutes = 30;
        onView(withId(R.id.filter_menu_activity_main)).perform((click()));
        onView(withText("Par heure")).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minutes));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.recyclerview)).check(withItemCount(1));
    }
}
