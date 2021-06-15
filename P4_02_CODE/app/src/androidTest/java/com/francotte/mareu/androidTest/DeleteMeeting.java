package com.francotte.mareu.androidTest;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.francotte.mareu.R;
import com.francotte.mareu.ListMeetingActivity;
import com.francotte.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.francotte.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DeleteMeeting {

    private int ITEMS_COUNT = 6;
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
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new DeleteViewAction()));
        // Then : the number of element is 4
        onView(withId(R.id.recyclerview)).check(withItemCount(ITEMS_COUNT - 1));
    }

}
