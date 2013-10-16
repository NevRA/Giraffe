package com.home.giraffe;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.home.giraffe.LauncherActivityTest \
 * com.home.giraffe.tests/android.test.InstrumentationTestRunner
 */
public class LauncherActivityTest extends ActivityInstrumentationTestCase2<LauncherActivity> {

    public LauncherActivityTest() {
        super("com.home.giraffe", LauncherActivity.class);
    }
}
