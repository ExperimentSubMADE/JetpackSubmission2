package com.gdktuts.jetpacksubmission.ui.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class RecyclerViewItemCountAssertion implements ViewAssertion {

    private final int expectedCount;

    public RecyclerViewItemCountAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recylerViewTest = (RecyclerView) view;
        RecyclerView.Adapter adapterTest = recylerViewTest.getAdapter();
        assertNotNull(adapterTest);
        assertThat(adapterTest.getItemCount(), is(expectedCount));

    }
}
