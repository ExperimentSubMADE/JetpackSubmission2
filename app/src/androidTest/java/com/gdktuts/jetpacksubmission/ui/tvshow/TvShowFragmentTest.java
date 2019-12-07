package com.gdktuts.jetpacksubmission.ui.tvshow;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.viewpager.widget.ViewPager;

import com.gdktuts.jetpacksubmission.R;
import com.gdktuts.jetpacksubmission.testing.SingleFragmentActivity;
import com.gdktuts.jetpacksubmission.ui.utils.RecyclerViewItemCountAssertion;
import com.gdktuts.jetpacksubmission.utils.EspressoIdlingResource;
import com.google.android.material.tabs.TabLayout;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class TvShowFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule = new ActivityTestRule<>(SingleFragmentActivity.class);

    @Before
    public void checkViewPagerTest() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getCountingIdlingResource());
        ViewPager viewPagerTest = activityTestRule.getActivity().findViewById(R.id.viewpagerMainNavigation);
        assertNotNull(viewPagerTest);
        assertEquals(2, Objects.requireNonNull(viewPagerTest.getAdapter()).getCount());
        assertEquals(-1, viewPagerTest.getAdapter().getItemPosition(new TvShowFragment()));
        onView(withId(R.id.tablayoutMainNavigation)).check(matches(isDisplayed()));
        onView(withId(R.id.tablayoutMainNavigation)).perform(selectTabAtPositionTest(1)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getCountingIdlingResource());
    }

    @Test
    public void loadRecyclerViewTvShowTest() {
        onView(withId(R.id.recylerviewTvShowItem)).check(matches(isDisplayed()));
        onView(withId(R.id.recylerviewTvShowItem)).check(new RecyclerViewItemCountAssertion(20));
    }

    @Test
    public void clickRecylerViewTvShowTest() {
        onView(withId(R.id.recylerviewTvShowItem)).check(matches(isDisplayed()));
        onView(withId(R.id.recylerviewTvShowItem)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.relativelayoutTvShowContentLayout)).check(matches(isDisplayed()));
    }

    @NonNull
    private static ViewAction selectTabAtPositionTest(final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
            }
            @Override
            public String getDescription() {
                return "Tab at index : " + position;
            }
            @Override
            public void perform(UiController uiController, View view) {
                if (view instanceof TabLayout) {
                    TabLayout tabLayoutTest = (TabLayout) view;
                    TabLayout.Tab tabTest = tabLayoutTest.getTabAt(position);

                    if (tabTest != null) {
                        tabTest.select();
                    }
                }
            }
        };
    }
}