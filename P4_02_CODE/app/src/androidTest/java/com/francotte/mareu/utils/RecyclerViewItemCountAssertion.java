package com.francotte.mareu.utils;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class RecyclerViewItemCountAssertion implements ViewAssertion {
    private final Matcher<Integer> matcher;

    public static com.francotte.mareu.utils.RecyclerViewItemCountAssertion withItemCount(int expectedCount) {
        return withItemCount(Matchers.is(expectedCount));
    }

    public static com.francotte.mareu.utils.RecyclerViewItemCountAssertion withItemCount(Matcher<Integer> matcher) {
        return new com.francotte.mareu.utils.RecyclerViewItemCountAssertion(matcher);
    }

    private RecyclerViewItemCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Assert.assertThat(adapter.getItemCount(), matcher);
    }
}