package com.pk.campussafety.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.maps.android.PolyUtil;

import com.pk.campussafety.network.CampusSafetyNetwork;
import com.pk.campussafety.utility.MapOverlayUtility;

/**
 * Created by Purvesh on 4/11/2017.
 */

public class LocationService extends Service {

    private static final String TAG = "CAMPUSSAFETYTAG";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 10000;
    private static final float LOCATION_DISTANCE = 0f;

    final Context context = this;

    public class LocalBinder extends Binder {

        public LocationService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocationService.this;
        }

    }

    private class LocationListener implements android.location.LocationListener
    {
        Location mLastLocation;

        public LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: " + location);

            // Sending Location Data to the network using Volley Library
            CampusSafetyNetwork network = new CampusSafetyNetwork();

            // Getting room name based on the location in overlay
            String currentSection = getCurrentSection(location);

            network.makeRequestWithVolley(context, "http://www.sjsu.edu/", location.getLatitude(), location.getLongitude(), currentSection);
            mLastLocation.set(location);
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public String getCurrentSection(Location location){

            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            boolean isLocationInIRS = PolyUtil.containsLocation(latitude,longitude,MapOverlayUtility.irsOverlay,false);
            boolean isLocationInArtsDepartment = PolyUtil.containsLocation(latitude,longitude,MapOverlayUtility.artsDepartmentOverlay,false);
            boolean isLocationInStudentCenter = PolyUtil.containsLocation(latitude,longitude,MapOverlayUtility.studentCenterOverlay,false);
            boolean isLocationInEconomics = PolyUtil.containsLocation(latitude,longitude,MapOverlayUtility.economicsDepartmentOverlay,false);

            if (isLocationInIRS){
                return "Instructional Resource Center";
            } else if (isLocationInArtsDepartment){
                return "Arts Department";
            } else if (isLocationInStudentCenter){
                return "Student Center";
            } else if (isLocationInEconomics){
                return "Economics Department";
            } else {
                return null;
            }

        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {

        initializeLocationManager();

        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }

        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (SecurityException ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {

        Log.e(TAG, "initializeLocationManager");

        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    public int getRandomInt(){
        return 100;
    }

}