package com.travel.hackroads;

import java.io.Serializable;
import java.util.List;

public class reqBean implements Serializable {

    private int days;
    private int strategy;
    private List<Integer> places;

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

    public List<Integer> getPlaces() {
        return places;
    }

    public void setPlaces(List<Integer> places) {
        this.places = places;
    }
}
