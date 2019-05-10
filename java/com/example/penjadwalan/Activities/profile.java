package com.example.penjadwalan.Activities.Activities;

public class profile {
    String nama;
    String profil;

    public profile() {
    }

    public profile(String nama, String profil) {
        this.nama = nama;
        this.profil = profil;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}
