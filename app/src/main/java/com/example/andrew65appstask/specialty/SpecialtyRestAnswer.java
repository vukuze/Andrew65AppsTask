package com.example.andrew65appstask.specialty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Хранит полученные REST данные для Specialty
 */

public class SpecialtyRestAnswer {

    @SerializedName("specialty_id")
    @Expose
    private Integer specialtyId;

    @SerializedName("name")
    @Expose
    private String name;

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}