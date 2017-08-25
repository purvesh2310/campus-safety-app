package com.pk.campussafety;

import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Purvesh on 4/16/2017.
 */

@RunWith(AndroidJUnit4.class)
public class CampusSafetyInstrumentedTest {

    private MainActivity mainActivity;
    private GoogleApiClient mGoogleApiClient;
    private Instrumentation instrumentation;


    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

        mainActivity = (MainActivity) mActivityRule.getActivity();
        instrumentation = InstrumentationRegistry.getInstrumentation();

        mGoogleApiClient = new GoogleApiClient.Builder(instrumentation.getTargetContext())
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Test
    public void testUseAppContext() throws Exception {

        // Context of the app under test.
        Context appContext = instrumentation.getTargetContext();
        assertEquals("com.pk.campussafety", appContext.getPackageName());
    }

    @Test
    public void testAlertProgressBar() {

        final ProgressBar progressBar = (ProgressBar) mainActivity.findViewById(R.id.alertProgressBar);

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                while (progressBar.getProgress() != progressBar.getMax()){
                    progressBar.incrementProgressBy(2);
                }
                assertEquals( 8 ,progressBar.getProgress());
            }
        });
    }

    @Test
    public void testAlertInstruction(){

        TextView textView = (TextView) mainActivity.findViewById(R.id.alertInstructions);
        assertEquals("Alert instruction must match", mainActivity.getResources().getString(R.string.label_alert_instruction), textView.getText());

    }

    @Test
    public void testLocationRuntimePermissionsGranted() {

        instrumentation.runOnMainSync(new Runnable() {
            @Override public void run() {
                assertEquals("NO GPS Permission Granted", PackageManager.PERMISSION_GRANTED,
                        ContextCompat.checkSelfPermission(mainActivity,
                                android.Manifest.permission.ACCESS_FINE_LOCATION));
            }
        });
    }

    @Test
    public void testGoogleApiClientConnected() {
        assertEquals("Google api client not connected", true, mGoogleApiClient.isConnected());
    }

    @After
    public void tearDown() {  }
}
