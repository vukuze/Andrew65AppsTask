package me.andrew.taskpersonnel.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Хранит REST ответ с сервера 65apps.com
 */
public class Response {

    @SerializedName("response")
    @Expose
    private List<EmployeeRestAnswer> employees = null;

    public List<EmployeeRestAnswer> getEmployees() {
        return employees;
    }
}

