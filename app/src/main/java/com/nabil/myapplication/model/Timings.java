package com.nabil.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timings {

    @SerializedName("Fajr")
    @Expose
    private String Fajr;

    @SerializedName("Dhuhr")
    @Expose
    private String Dhuhr;

    @SerializedName("Asr")
    @Expose
    private String Asr;

    @SerializedName("Maghrib")
    @Expose
    private String Maghrib;

    @SerializedName("Isha")
    @Expose
    private String Isha;

    public Timings(String fajr, String dhuhr, String asr, String maghrib, String isha) {
        Fajr = fajr;
        Dhuhr = dhuhr;
        Asr = asr;
        Maghrib = maghrib;
        Isha = isha;
    }

    public String getFajr() {
        return Fajr;
    }

    public void setFajr(String fajr) {
        Fajr = fajr;
    }

    public String getDhuhr() {
        return Dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        Dhuhr = dhuhr;
    }

    public String getAsr() {
        return Asr;
    }

    public void setAsr(String asr) {
        Asr = asr;
    }

    public String getMaghrib() {
        return Maghrib;
    }

    public void setMaghrib(String maghrib) {
        Maghrib = maghrib;
    }

    public String getIsha() {
        return Isha;
    }

    public void setIsha(String isha) {
        Isha = isha;
    }

}
