package com.example;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.beattrack.HomeActivity;
import com.example.beattrack.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UItest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityRule =
            new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void testAll(){
        testAddData();
        testEditData();
        testDeleteData();
    }

    @Test
    public void testAddData(){
        onView(withId(R.id.fabAdd)).perform(click());
        onView(withId(R.id.systolic)).perform(ViewActions.typeText("70"));
        onView(withId(R.id.dia)).perform(ViewActions.typeText("100"));
        onView(withId(R.id.Heart)).perform(ViewActions.typeText("65"));
        Espresso.pressBack();
        onView(withId(R.id.Date2)).perform(click());
        onView(withId(R.id.Date2)).perform(click());
        onView(ViewMatchers.withText("OK")).perform(click());

        onView(withId(R.id.Time)).perform(click());
        onView(withId(R.id.Time)).perform(click());
        onView(ViewMatchers.withText("OK")).perform(click());

        onView(withId(R.id.comment)).perform(ViewActions.typeText("UI test"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

    }

    @Test
    public void testEditData(){

        SystemClock.sleep(4000);
        onView(withId(R.id.rvList)).perform(RecyclerViewActions.actionOnItemAtPosition(
                0,MyViewAction.clickChildViewWithId(R.id.edit)));
        SystemClock.sleep(1500);

        onView(withId(R.id.systolic)).perform(ViewActions.replaceText("80"));
        onView(withId(R.id.dia)).perform(ViewActions.replaceText("110"));
        onView(withId(R.id.Heart)).perform(ViewActions.replaceText("85"));
        //Espresso.pressBack();
        onView(withId(R.id.Date2)).perform(click());
        onView(withId(R.id.Date2)).perform(click());
        onView(ViewMatchers.withText("OK")).perform(click());

        onView(withId(R.id.Time)).perform(click());
        onView(withId(R.id.Time)).perform(click());
        onView(ViewMatchers.withText("OK")).perform(click());

        onView(withId(R.id.comment)).perform(ViewActions.replaceText("UI test"));
        //Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        SystemClock.sleep(4000);

        onView(withId(R.id.rvList)).perform(RecyclerViewActions.actionOnItemAtPosition(
                0,MyViewAction.clickChildViewWithId(R.id.buttonDetails)));
        SystemClock.sleep(1000);
        Espresso.pressBack();

    }

    @Test
    public void testDeleteData(){

        SystemClock.sleep(4000);
        onView(withId(R.id.rvList)).perform(RecyclerViewActions.actionOnItemAtPosition(
                0,MyViewAction.clickChildViewWithId(R.id.delete)));
        SystemClock.sleep(4000);

    }

    public static class MyViewAction {

        public static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return ViewMatchers.isAssignableFrom(RecyclerView.class);
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }

    }
}

