package com.travel.hackroads;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen.D on 18/4/21.
 */

public class PlaceData {
//    private static PlaceData sData;
    public static List<PlaceResult.PlacesBean> resultPlaces = new ArrayList<>();
    public static List<Integer> reqPlaces = new ArrayList<>();
    public static List<Result.ParamBean.PositionsBean> resultPositions = new ArrayList<>();

    public static void clear() {
        resultPlaces.clear();
        reqPlaces.clear();
        resultPositions.clear();
    }
//    public static PlaceData getInstance() {
//        if (sData == null) {
//            synchronized (PlaceData.class) {
//                if (sData == null) {
//                    sData = new PlaceData();
//                }
//            }
//        }
//        return sData;
//    }
//
//    private PlaceData() {
//
//    }
}
