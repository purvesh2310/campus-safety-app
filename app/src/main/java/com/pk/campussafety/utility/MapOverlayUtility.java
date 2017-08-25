package com.pk.campussafety.utility;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 * Created by Purvesh on 4/9/2017.
 */

public class MapOverlayUtility {

    public static ArrayList<LatLng> irsOverlay = new ArrayList<LatLng>();
    public static ArrayList<LatLng> artsDepartmentOverlay = new ArrayList<LatLng>();
    public static ArrayList<LatLng> studentCenterOverlay = new ArrayList<LatLng>();
    public static ArrayList<LatLng> economicsDepartmentOverlay = new ArrayList<LatLng>();

    public static PolygonOptions getIRSOverlay(){

        irsOverlay.add(new LatLng(37.336080, -121.883652));
        irsOverlay.add(new LatLng(37.336240, -121.883326));
        irsOverlay.add(new LatLng(37.336243, -121.883347));
        irsOverlay.add(new LatLng(37.336264, -121.883308));
        irsOverlay.add(new LatLng(37.336336, -121.883361));
        irsOverlay.add(new LatLng(37.336322, -121.883401));
        irsOverlay.add(new LatLng(37.336386, -121.883457));
        irsOverlay.add(new LatLng(37.336239, -121.883772));

        PolygonOptions irsOverlayOptions = new PolygonOptions();
        irsOverlayOptions.strokeColor(Color.BLUE);
        irsOverlayOptions.strokeWidth(4);
        irsOverlayOptions.addAll(irsOverlay);

        return irsOverlayOptions;
    }

    public static PolygonOptions getArtsDepartmentOverlay(){

        artsDepartmentOverlay.add(new LatLng(37.336239, -121.883772));
        artsDepartmentOverlay.add(new LatLng(37.336122, -121.883999));
        artsDepartmentOverlay.add(new LatLng(37.335979, -121.883877));
        artsDepartmentOverlay.add(new LatLng(37.336073, -121.883695));
        artsDepartmentOverlay.add(new LatLng(37.336128, -121.883729));
        artsDepartmentOverlay.add(new LatLng(37.336147, -121.883694));

        PolygonOptions artsDepartmentOverlayOptions = new PolygonOptions();
        artsDepartmentOverlayOptions.strokeColor(Color.BLUE);
        artsDepartmentOverlayOptions.strokeWidth(4);
        artsDepartmentOverlayOptions.addAll(artsDepartmentOverlay);

        return artsDepartmentOverlayOptions;
    }

    public static PolygonOptions getStudentCenterOverlay(){

        studentCenterOverlay.add(new LatLng(37.336122, -121.883999));
        studentCenterOverlay.add(new LatLng(37.336219, -121.883790));
        studentCenterOverlay.add(new LatLng(37.336406, -121.883925));
        studentCenterOverlay.add(new LatLng(37.336318, -121.884136));

        PolygonOptions studentCenterOverlayOptions = new PolygonOptions();
        studentCenterOverlayOptions.strokeColor(Color.BLUE);
        studentCenterOverlayOptions.strokeWidth(4);
        studentCenterOverlayOptions.addAll(studentCenterOverlay);

        return studentCenterOverlayOptions;

    }

    public static PolygonOptions getEconomicsDepartmentOverlay(){

        economicsDepartmentOverlay.add(new LatLng(37.336318, -121.884136));
        economicsDepartmentOverlay.add(new LatLng(37.336374, -121.884182));
        economicsDepartmentOverlay.add(new LatLng(37.336363, -121.884205));
        economicsDepartmentOverlay.add(new LatLng(37.336434, -121.884258));
        economicsDepartmentOverlay.add(new LatLng(37.336455, -121.884217));
        economicsDepartmentOverlay.add(new LatLng(37.336734, -121.883645));
        economicsDepartmentOverlay.add(new LatLng(37.336576, -121.883526));
        economicsDepartmentOverlay.add(new LatLng(37.336404, -121.883912));

        PolygonOptions economicsDepartmentOverlayOptions = new PolygonOptions();
        economicsDepartmentOverlayOptions.strokeColor(Color.BLUE);
        economicsDepartmentOverlayOptions.strokeWidth(4);
        economicsDepartmentOverlayOptions.addAll(economicsDepartmentOverlay);

        return economicsDepartmentOverlayOptions;

    }

    public static void addDepartmentMarkers(GoogleMap mMap){

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        LatLng ircLatLng = new LatLng(37.336254, -121.883543);
        markerOptions.position(ircLatLng);
        markerOptions.title("Instructional Resource Center");
        mMap.addMarker(markerOptions);

        LatLng artsDepartmentLatLng = new LatLng(37.336098, -121.883858);
        markerOptions.position(artsDepartmentLatLng);
        markerOptions.title("Arts Department");
        mMap.addMarker(markerOptions);

        LatLng studentCenterLatLng = new LatLng(37.336290, -121.883972);
        markerOptions.position(studentCenterLatLng);
        markerOptions.title("Student Center");
        mMap.addMarker(markerOptions);

        LatLng economicsDepartmentLatLng = new LatLng(37.336558, -121.883804);
        markerOptions.position(economicsDepartmentLatLng);
        markerOptions.title("Department of Economics");
        mMap.addMarker(markerOptions);
    }
}
