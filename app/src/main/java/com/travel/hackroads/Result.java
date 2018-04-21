package com.travel.hackroads;

import java.util.List;

/**
 * Created by Allen.D on 18/4/21.
 */

public class Result {

    /**
     * msg : OK
     * method : POST
     * param : {"positions":[{"lat":116.397972,"lng":23.397972,"place":"美食"},{"lat":116.397972,"lng":23.397972,"place":"美食"},{"lat":116.397972,"lng":23.397972,"place":"美食"}],"days":1,"strategy":1}
     */

    private String msg;
    private String method;
    private ParamBean param;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public static class ParamBean {
        /**
         * positions : [{"lat":116.397972,"lng":23.397972,"place":"美食"},{"lat":116.397972,"lng":23.397972,"place":"美食"},{"lat":116.397972,"lng":23.397972,"place":"美食"}]
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

        public static class PositionsBean {
            /**
             * lat : 116.397972
             * lng : 23.397972
             * place : 美食
             */

            private double lat;
            private double lng;
            private String place;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }
        }
    }

}
