package com.francotte.mareu.androidTest;

import androidx.test.rule.ActivityTestRule;

import com.francotte.mareu.R;
import com.francotte.mareu.ListMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.francotte.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class FilterRoomTest {

    private int ITEMS_COUNT = 5;
    private ListMeetingActivity mActivity;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule = new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void myMeetingList_filterRoom() {
        onView(withId(R.id.filter_menu_activity_main)).perform((click()));
        onView(withText("Par local")).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.recyclerview)).check(withItemCount(1));
    }
}
