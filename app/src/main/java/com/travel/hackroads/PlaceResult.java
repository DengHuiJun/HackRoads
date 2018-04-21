package com.travel.hackroads;

import java.util.List;

/**
 * Created by Allen.D on 18/4/21.
 */

public class PlaceResult {

    /**
     * status : 200
     * places : [{"id":1,"name":"世界之窗"},{"id":2,"name":"欢乐谷"},{"id":3,"name":"大梅沙"}]
     */

    private int status;
    private List<PlacesBean> places;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PlacesBean> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlacesBean> places) {
        this.places = places;
    }

    public static class PlacesBean {
        /**
         * id : 1
         * name : 世界之窗
         */

        private int id;
        private String name;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
