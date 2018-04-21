package com.travel.hackroads;

import java.io.Serializable;
import java.util.List;

public class Bean implements Serializable {

    /**
     * positions : [{"lng":23.397972,"lat":116.397972,"place":"美食"},{"lng":23.397972,"lat":116.397972,"place":"美食"},{"lng":23.397972,"lat":116.397972,"place":"美食"}]
     * days : 1
     * strategy : 1
     */

    private int days;
    private int strategy;
    private List<PositionsBean> positions;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getStrategy() {
        return strategy;
    }

    public void setStrategy(int strategy) {
        this.strategy = strategy;
    }

    public List<PositionsBean> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionsBean> positions) {
        this.positions = positions;
    }

    public static class PositionsBean implements Serializable {
        /**
         * lng : 23.397972
         * lat : 116.397972
         * place : 美食
         */

        private double lng;
        private double lat;
        private String place;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }
    }

}
