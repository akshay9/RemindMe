package com.sidthak.remindme.models;

import com.google.firebase.firestore.FirebaseFirestore;
import com.touchboarder.weekdaysbuttons.WeekdaysDataItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmModel implements Serializable {

    String id;
    String owner;
    String name;
    String description;
    int hours;
    int minutes;
    List<Boolean> days;
    boolean repeat;
    boolean activated;

    public AlarmModel() {

    }

    public AlarmModel(String owner, String name, String description, int hours, int minutes, List<Boolean> days, boolean repeat, boolean activated) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
        this.days = days;
        this.repeat = repeat;
        this.activated = activated;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public List<Boolean> getDays() {
        return days;
    }

    public void setDays(List<Boolean> days) {
        this.days = days;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void addToFireStore() {
        FirebaseFirestore.getInstance().collection("alarms").add(this);
    }
}
