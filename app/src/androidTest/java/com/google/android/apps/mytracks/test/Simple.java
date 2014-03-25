package com.google.android.apps.mytracks.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Looper;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestRunner;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.clearText;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.matcher.RootMatchers.withDecorView;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isClickable;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withContentDescription;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsAnything.anything;

import com.google.android.apps.common.testing.ui.espresso.Espresso;
import com.google.android.apps.common.testing.ui.espresso.UiController;
import com.google.android.apps.common.testing.ui.espresso.action.ViewActions;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
import com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers;
import com.google.android.apps.mytracks.TrackListActivity;
import com.google.android.apps.mytracks.fragments.PlayMultipleDialogFragment;
import com.google.android.maps.mytracks.R;
import com.google.api.client.util.DateTime;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

public class Simple extends ActivityInstrumentationTestCase2<TrackListActivity> {

    public Simple() {
        super(TrackListActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        getActivity();
    }

    public void testCreateTrack() {

        onView(withId(R.id.track_controller_record))
                .perform(click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.track_controller_stop))
                .perform(click());

        onView(withId(R.id.track_edit_name)).perform(ViewActions.typeText(String.valueOf(System.currentTimeMillis())));

        Espresso.closeSoftKeyboard();

        onView(withText(R.string.generic_save))
                .perform(click());

    }

/*
    //Completely wrong, tests always start in the activity! *//*
    public void testGoBackMain() {

        for(int i = 0; i < 10; i++){
            try {
                onView(withId(R.id.track_list))
                        .check(matches(isDisplayed()));//Not displayed if empty ??
            }
            catch(Error e){
                Espresso.pressBack();
            }
        }
        onView(withId(R.id.track_list))
                .check(matches(isDisplayed()));

    }
*/
    public void testEndRecording(){
        try{
            onView(withId(R.id.track_controller_status))
                    .check(matches(isDisplayed()));
            onView(withId(R.id.track_controller_stop))
                    .perform(click());
        }
        catch(Error err){

        }
        onView(withId(R.id.track_controller_status)).check(matches(not(isDisplayed())));
    }

    public void testResetAllSettings() {

        testEndRecording();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.menu_settings))
                .perform(click());
        onView(withText(R.string.settings_advanced))
                .perform(click());
        onView(withText(R.string.settings_reset))
                .perform(click());
        // TODO : This is unavailable during recording a track,
        // hence all recordings need to be stopped before running
        // this test, see testEndRecording call
        onView(withText(R.string.generic_yes))
                .perform(click());



        onView(withText(R.string.settings_reset_done))
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        onView(withText(R.string.settings_reset)).check(matches(isDisplayed()));

        Espresso.pressBack();//TODO: Without this, the application is not destroyed for the test end
    }

    public void testMultipleTrack(){
        for(int i = 0; i < 10; i++) {
            testCreateTrack();
            Espresso.pressBack();
        }

        onData(anything())
                .atPosition(9)
                .perform(click());

        onView(withText(R.string.track_detail_map_tab))
                .check(matches(isDisplayed()));

//        onData(Matchers.anything()).onChildView(withText("24")).perform(click());
//        //onData(instanceOf(PlayMultipleDialogFragment.class))

    }


    public void testViewMultiple() {

//        Log.v("Trace","View Multiple start");
//        /* Ensuring at least one track to play*/
//        testCreateTrack();
//        Espresso.pressBack();
//        testCreateTrack();
//        Espresso.pressBack();
//
//        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
//
//        onView(withText(R.string.menu_play_multiple))
//                .perform(click());
//
//        onData(Matchers.anything()).onChildView(withText("24")).perform(click());
//        //onData(instanceOf(PlayMultipleDialogFragment.class))

    }

    public void testDeleteAllTracks(){

        testEndRecording();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.menu_delete_all))
                .perform(click());
        onView(allOf(isClickable(),withText(R.string.generic_yes)))
                .perform(click());
        //onView(withText(R.string.generic_progress_title))
        //        .check(matches(isDisplayed()));
        onView(withText(R.string.track_list_empty_message))
                .check(matches(isDisplayed()));
        onView(withId(R.id.track_list))
                .check(matches(not(withAdaptedData(anything()))));
    }

    private static Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                dataMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }
                @SuppressWarnings("rawtypes")
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
