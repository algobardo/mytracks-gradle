package com.google.android.apps.mytracks.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.Button;

import com.google.android.apps.common.testing.ui.espresso.Espresso;
import com.google.android.apps.common.testing.ui.espresso.ViewAction;
import com.google.android.apps.mytracks.TrackListActivity;
import com.google.android.apps.mytracks.util.EulaUtils;
import com.google.android.maps.mytracks.R;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class Utils {

/*
    public static final int TINY_WAIT_TIME = 200;
    public static final int VERY_SHORT_WAIT_TIME = 500;
    public static final int SHORT_WAIT_TIME = 2000;
    public static final int NORMAL_WAIT_TIME = 8000;
    public static final int LONG_WAIT_TIME = 15000;
    public static final int SUPER_LONG_WAIT_TIME = 100000;

    // Pause 200ms between each send.
    public static final double START_LONGITUDE = 51;
    public static final double START_LATITUDE = -1.3f;
    public static final double DELTA_LONGITUDE = 0.0005f;
    public static final double DELTA_LADITUDE = 0.0005f;
    public static final String WAYPOINT_NAME = "testWaypoint";
    public static final String WAYPOINT_TYPE = "testWaypoinType";
    public static final String WAYPOINT_DESCRIPTION = "testWaypointDesc";
    public static final String DEFAULT_ACTIVITY_TYPE = "TestActivity";
    public static final String TRACK_NAME_PREFIX = "testTrackName";

    private static final String TAG = EndToEndTestUtils.class.getSimpleName();
    private static final String TRACK_DESCRIPTION_PREFIX = "testTrackDesc";

    private static final int ORIENTATION_PORTRAIT = 1;
    private static final int ORIENTATION_LANDSCAPE = 0;

    private static final String ANDROID_LOCAL_IP = "10.0.2.2";
    private static final String NO_GPS_MESSAGE_PREFIX = "GPS is not available";
    private static final String MORE_OPTION_CLASSNAME = "com.android.internal.view.menu.ActionMenuPresenter$OverflowMenuButton";
    private static final String MENUITEM_CLASSNAME = "com.android.internal.view.menu.IconMenuItemView";

    // Following is some check strings in English and Chinese
    private static final HashMap<String, String>
            RELATIVE_START_TIME_SUFFIX_MULTILINGUAL = new HashMap<String, String>();

    static {
        RELATIVE_START_TIME_SUFFIX_MULTILINGUAL.put("en", "mins ago");
        RELATIVE_START_TIME_SUFFIX_MULTILINGUAL.put("de", "Minuten");
        RELATIVE_START_TIME_SUFFIX_MULTILINGUAL.put("fr", "minute");
        RELATIVE_START_TIME_SUFFIX_MULTILINGUAL.put("ar", "دقيقة");
        RELATIVE_START_TIME_SUFFIX_MULTILINGUAL.put("zh", "分钟前");
    }

    public static int emulatorPort = 5554; // usually 5554.
    public static String activityType = DEFAULT_ACTIVITY_TYPE;
    public static String trackName;
    public static String trackDescription;
    public static com.robotium.solo.Solo SOLO;
    public static Instrumentation instrumentation;
    public static TrackListActivity trackListActivity;

    *//*
     * Check whether the UI has an action bar which is related with the version of
     * Android OS.
     *//*
    public static boolean hasActionBar = false;
    public static boolean isEmulator = true;
    public static boolean hasGpsSignal = true;

    private static boolean isCheckedFirstLaunch = false;
    private static boolean isGooglePlayServicesLatest = true;
    private static String language;

    private EndToEndTestUtils() {};

    *//**
     * Checks the language, then sets the fields with right string.
     *//*
    private static void checkLanguage() {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);

        Configuration configuration = trackListActivity.getBaseContext()
                .getResources().getConfiguration();
        configuration.locale = locale;

        language = instrumentation.getContext().getResources().getConfiguration().locale.getLanguage();
    }

    *//**
     * Sends Gps data to emulator, and the start value has an offset.
     *
     * @param number send times
     * @param offset is used to compute the start latitude and longitude
     * @param pause pause interval between each sending
     *//*
    public static void sendGps(int number, int offset, int pause) {
        if (number < 1) {
            return;
        }

        int pauseInterval = TINY_WAIT_TIME;
        if (pause != -1) {
            pauseInterval = pause;
        }

        // If it's a real device, does not send simulated GPS signal.
        if (!isEmulator) {
            return;
        }

        PrintStream out = null;
        Socket socket = null;
        try {
            socket = new Socket(ANDROID_LOCAL_IP, emulatorPort);
            out = new PrintStream(socket.getOutputStream());
            double longitude = START_LONGITUDE + offset * DELTA_LONGITUDE;
            double latitude = START_LATITUDE + offset * DELTA_LADITUDE;
            for (int i = 0; i < number; i++) {
                out.println("geo fix " + longitude + " " + latitude);
                longitude += DELTA_LONGITUDE;
                latitude += DELTA_LADITUDE;
                Thread.sleep(pauseInterval);
            }
            // Wait the GPS signal can be obtained by MyTracks.
            Thread.sleep(SHORT_WAIT_TIME);
        } catch (UnknownHostException e) {
            System.exit(-1);
        } catch (IOException e) {
            System.exit(-1);
        } catch (InterruptedException e) {
            System.exit(-1);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    *//**
     * Send Gps data to emulator.
     *
     * @param number number of signals
     * @param offset is used to compute the start latitude and longitude
     *//*
    public static void sendGps(int number, int offset) {
        sendGps(number, offset, -1);
    }

    *//**
     * Send Gps data to emulator.
     *
     * @param number number of signals
     *//*
    public static void sendGps(int number) {
        sendGps(number, 0, -1);
    }

    *//**
     * Sets the status whether the test is run on an emulator or not.
     *//*
    private static void setIsEmulator() {
        isEmulator = android.os.Build.MODEL.equals("google_sdk");
    }

    *//**
     * Checks whether the Google Play Services need update.
     *//*
    private static boolean isGooglePlayServicesLatest() {
        return findTextView("Google Play services") == null;
    }

*/


    public static void setup(TrackListActivity activity) {
//        if((!EulaUtils.hasAcceptedEula(activity))){
//            // Check the EULA is shown
//            onView(withText())
//        }
//
//
//        setIsEmulator();
//        hasActionBar = setHasActionBar();
//        checkLanguage();
    }
/*
    /**
     * A setup for all end-to-end tests.
     *
     * @param inst the instrumentation
     * @param activity the track list activity
     *//*
    public static void setupForAllTest(Instrumentation inst, TrackListActivity activity) {
        instrumentation = inst;
        trackListActivity = activity;
        SOLO = new Solo(instrumentation, trackListActivity);

        if (!isGooglePlayServicesLatest) {
            SOLO.finishOpenedActivities();
            Assert.fail();
            Log.e(TAG, "Need update Google Play Services");
        }

        // Check if open MyTracks first time after install. If so, there would be a
        // welcome view with accept buttons. And makes sure only check once.
        if (!isCheckedFirstLaunch) {
            isGooglePlayServicesLatest = isGooglePlayServicesLatest();
            if (!isGooglePlayServicesLatest) {
                SOLO.finishOpenedActivities();
                Assert.fail();
                Log.e(TAG, "Need update Google Play Services");
            }
            setIsEmulator();

            verifyFirstLaunch();
            hasActionBar = setHasActionBar();
            checkLanguage();
            isCheckedFirstLaunch = true;

            inst.waitForIdleSync();
            // Check the status of real phone. For emulator, we would fix GPS signal.
            if (!isEmulator) {
                findAndClickMyLocation(activity);
                hasGpsSignal = !SOLO.waitForText(NO_GPS_MESSAGE_PREFIX, 1, SHORT_WAIT_TIME);
                SOLO.goBack();
            }
        }

        int trackNumber = SOLO.getCurrentViews(ListView.class).get(0).getCount();
        // Delete all tracks when there are two many tracks which may make some test
        // run slowly, such as sync test cases.
        if (trackNumber > 3) {
            deleteAllTracks();
        }

        // Check whether is under recording. If previous test failed, the recording
        // may not be recording.
        if (isUnderRecording()) {
            stopRecording(true);
        }

        resetAllSettings(activity, false);
        inst.waitForIdleSync();
    }
/*
    *//**
     * Rotates the given activity.
     *
     * @param activity a given activity
     *//*
    private static void rotateActivity(Activity activity) {
        activity
                .setRequestedOrientation(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? ORIENTATION_LANDSCAPE
                        : ORIENTATION_PORTRAIT);
    }

    *//**
     * Accepts terms and configures units.
     *//*
    private static void verifyFirstLaunch() {
        if ((getButtonOnScreen(trackListActivity.getString(R.string.eula_accept), false, false) != null)) {
            getButtonOnScreen(trackListActivity.getString(R.string.eula_accept), true, true);
        }
    }

    *//**
     * Checks whether an action bar is shown.
     *
     * @return false means can not check failed.
     *//*
    @SuppressLint("NewApi")
    private static boolean setHasActionBar() {
        try {
            return trackListActivity.getActionBar() == null ? false : true;
        } catch (Throwable e) {
            // For in Android which does not has action bar, here will meet a error.
            return false;
        }
    }

    *//**
     * Rotates all activities.
     *//*
    public static void rotateCurrentActivity() {
        rotateActivity(SOLO.getCurrentActivity());
        instrumentation.waitForIdleSync();
    }

*/


}