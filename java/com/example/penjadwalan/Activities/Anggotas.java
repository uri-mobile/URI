package com.example.penjadwalan.Activities.Activities;

public class Anggotas {
    private int id;
    private String nama;
    private String prodi;
    private String angkatan;
    private String img;


    public Anggotas(int id, String nama, String prodi, String angkatan, String img) {
        this.id = id;
        this.nama = nama;
        this.prodi = prodi;
        this.angkatan = angkatan;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
