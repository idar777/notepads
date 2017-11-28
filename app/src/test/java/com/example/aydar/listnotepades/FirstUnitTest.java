package com.example.aydar.listnotepades;

import android.test.ActivityInstrumentationTestCase2;

import com.example.aydar.listnotepades.presentation.view.StartActivity;

/**
 * Created by aydar on 27.11.17.
 */

public class FirstUnitTest extends ActivityInstrumentationTestCase2<StartActivity> {

    public FirstUnitTest() {super(StartActivity.class);}

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

}
