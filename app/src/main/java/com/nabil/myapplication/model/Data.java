package com.nabil.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("timings")
    @Expose
    private Timings timings;

    @SerializedName("date")
    @Expose
    private Date date;

    @SerializedName("meta")
    @Expose
    private Meta meta;

    public Data(Timings timings, Date date, Meta meta) {
        this.timings = timings;
        this.date = date;
        this.meta = meta;
    }

    public Timings getTimings() {
        return timings;
    }

    public Date getDate(){
        return date;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setTimings(Timings timings) {
        this.timings = timings;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
