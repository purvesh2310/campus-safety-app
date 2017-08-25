package com.pk.campussafety;

import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.pk.campussafety.utility.MapOverlayUtility;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by Purvesh on 4/16/2017.
 */

public class CampusSafetyUnitTest {

    @Test
    public void testIRSOverlayOptionCreation(){

        PolygonOptions IRSPolygonOptions = null;
        IRSPolygonOptions = MapOverlayUtility.getIRSOverlay();
        assertNotNull("IRS Polygon option could not be null" , IRSPolygonOptions);
    }

    @Test
    public void testEconomicDepartmentOverlayOptionCreation(){

        PolygonOptions economicDepartmentPolygonOptions = null;
        economicDepartmentPolygonOptions = MapOverlayUtility.getEconomicsDepartmentOverlay();
        assertNotNull("Economics Department Polygon could not be null" , economicDepartmentPolygonOptions);

    }
}
