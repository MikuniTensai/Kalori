package com.example.kalori.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Todo 3 add entity Annotation
@Entity(tableName = "bmi_table")
public class BmiEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "BeratBadan")
    private float beratBadan;
    @ColumnInfo(name = "TinggiBadan")
    private float tinggiBadan;
    @ColumnInfo(name = "BMIResult")
    private float bmiResult;
    @ColumnInfo(name = "Kategori")
    private String kategori;

    //Todo 4 Generate Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(float beratBadan) {
        this.beratBadan = beratBadan;
    }

    public float getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(float tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public float getBmiResult() {
        return bmiResult;
    }

    public void setBmiResult(float bmiResult) {
        this.bmiResult = bmiResult;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
