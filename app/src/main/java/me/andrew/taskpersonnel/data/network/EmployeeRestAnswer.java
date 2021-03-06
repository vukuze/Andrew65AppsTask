package me.andrew.taskpersonnel.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Хранит полученные REST данные для Employee
 */
public class EmployeeRestAnswer {

    @SerializedName("f_name")
    @Expose
    private String fName;

    @SerializedName("l_name")
    @Expose
    private String lName;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("avatr_url")
    @Expose
    private String avatrUrl;

    @SerializedName("specialty")
    @Expose
    private List<SpecialtyRestAnswer> specialty = null;

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAvatrUrl() {
        return avatrUrl;
    }

    public List<SpecialtyRestAnswer> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<SpecialtyRestAnswer> specialty) {
        this.specialty = specialty;
    }
}
