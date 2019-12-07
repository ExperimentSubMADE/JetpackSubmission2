package com.gdktuts.jetpacksubmission.ui.tvshow;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.gdktuts.jetpacksubmission.R;
import com.gdktuts.jetpacksubmission.ui.utils.FakeContentDataDummy;
import com.gdktuts.jetpacksubmission.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class TvShowDetailActivityTest {

    private TvShowDataModel tvShowDataModelTest = FakeContentDataDummy.tvShowDataDummies().get(0);

    @Rule
    public ActivityTestRule<TvShowDetailActivity> activityTestRule = new ActivityTestRule<TvShowDetailActivity>
            (TvShowDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context contextTargetTest = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intentTest = new Intent(contextTargetTest, TvShowDetailActivity.class);
            intentTest.putExtra(TvShowDetailActivity.EXTRA_INFO_TV_SHOW, tvShowDataModelTest.getTvShowId());
            return intentTest;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getCountingIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getCountingIdlingResource());
    }

    @Test
    public void loadContentTvShowTest() {

        onView(withId(R.id.imageviewTvShowImage)).check(matches(isDisplayed()));

        onView(withId(R.id.textviewTvShowTitle)).check(matches(isDisplayed()));
        onView(withText(R.id.textviewTvShowTitle)).check(matches(withText(tvShowDataModelTest.getTvShowTitle())));

        onView(withId(R.id.textviewTvShowReleaseDate)).check(matches(isDisplayed()));
        onView(withId(R.id.textviewTvShowReleaseDate)).check(matches(withText(tvShowDataModelTest.getTvShowReleaseDate())));

        onView(withId(R.id.textviewTvShowOverview)).check(matches(isDisplayed()));
        onView(withId(R.id.textviewTvShowOverview)).check(matches(withText(tvShowDataModelTest.getTvShowOverview())));

        onView(withId(R.id.textviewTvShowRating)).check(matches(isDisplayed()));
    }

}