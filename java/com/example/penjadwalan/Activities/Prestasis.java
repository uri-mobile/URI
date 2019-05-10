package com.example.penjadwalan.Activities.Activities;

public class Prestasis {
    private int id;
    private String event;
    private String emas;
    private String perak;
    private String perunggu;

    public Prestasis(int id, String event, String emas, String perak, String perunggu) {
        this.id = id;
        this.event = event;
        this.emas = emas;
        this.perak = perak;
        this.perunggu = perunggu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEmas() {
        return emas;
    }

    public void setEmas(String emas) {
        this.emas = emas;
    }

    public String getPerak() {
        return perak;
    }

    public void setPerak(String perak) {
        this.perak = perak;
    }

    public String getPerunggu() {
        return perunggu;
    }

    public void setPerunggu(String perunggu) {
        this.perunggu = perunggu;
    }
}
