package com.example.calc531;

import java.io.Serializable;

public class PersonalStats implements Serializable {
    private String name;
    private double dead1Rm;
    private double squat1Rm;
    private double bench1Rm;

    private long serialVersionUID=1L;

    public void setName(String name) {
        this.name = name;
    }

    public void setDead1Rm(double dead1Rm) {
        this.dead1Rm = dead1Rm;
    }

    public void setSquat1Rm(double squat1Rm) {
        this.squat1Rm = squat1Rm;
    }

    public void setBench1Rm(double bench1Rm) {
        this.bench1Rm = bench1Rm;
    }

    public String getName() {
        return name;
    }

    public double getDead1Rm() {
        return dead1Rm;
    }

    public double getSquat1Rm() {
        return squat1Rm;
    }

    public double getBench1Rm() {
        return bench1Rm;
    }
}
