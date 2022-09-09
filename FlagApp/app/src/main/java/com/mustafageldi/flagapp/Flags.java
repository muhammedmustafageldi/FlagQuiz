package com.mustafageldi.flagapp;

public class Flags {

    private int flagId;
    private String flagName;
    private String flagImageName;

    public Flags() {
    }

    public Flags(int flagId, String flagName, String flagImageName) {
        this.flagId = flagId;
        this.flagName = flagName;
        this.flagImageName = flagImageName;
    }

    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public String getFlagImageName() {
        return flagImageName;
    }

    public void setFlagImageName(String flagImageName) {
        this.flagImageName = flagImageName;
    }
}
