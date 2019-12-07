package com.gdktuts.jetpacksubmission.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTest {

    @SuppressWarnings("unchecked")
    public static <T> T getValueTest(LiveData<T> liveDataTest) {

        Object[] dataTest = new Object[1];
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Observer<T> observerTest = new Observer<T>() {
            @Override
            public void onChanged(T t) {
                dataTest[0] = t;
                countDownLatch.countDown();
                liveDataTest.removeObserver(this);
            }
        };

        liveDataTest.observeForever(observerTest);

        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return (T) dataTest[0];

    }

}
