package com.example.andrew65appstask.util;

import android.util.Log;

import com.example.andrew65appstask.db.Employee;
import com.example.andrew65appstask.db.Specialty;
import com.example.andrew65appstask.network.EmployeeRestAnswer;
import com.example.andrew65appstask.network.SpecialtyRestAnswer;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Содержит статические функции для перевода объектов из формата,
 * полученного при помощи Retrofit в формат requery.io
 */
public class JsonToEntityConverter {
    private static final Pattern p1 = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
    private static final Pattern p2 = Pattern.compile("(\\d{2})-(\\d{2})-(\\d{4})");

    /**
     * Конвертирует объект Specialty из формата, полученного при помощи Retrofit в формат requery.io
     */
    private static Specialty convertSpecialty(SpecialtyRestAnswer specialtyRestAnswer) {
        Specialty specialty = new Specialty();
        specialty.setId(specialtyRestAnswer.getSpecialtyId());
        specialty.setName(specialtyRestAnswer.getName());
        return specialty;
    }

    /**
     * Конвертирует объект Employee из формата, полученного при помощи Retrofit в формат requery.io
     */
    public static Employee convertEmployee(EmployeeRestAnswer employeeRestAnswer) {
        Employee employee = new Employee();
        employee.setFName(correctName(employeeRestAnswer.getFName()));
        employee.setLName(correctName(employeeRestAnswer.getLName()));
        employee.setBirthday(toDate(employeeRestAnswer.getBirthday()));

        // Пустые строки зануляются
        String url = employeeRestAnswer.getAvatrUrl();
        if (url != null && url.trim().length() == 0) {
            url = null;
        }
        employee.setAvatrUrl(url);

        List<SpecialtyRestAnswer> employeeSpecialties = employeeRestAnswer.getSpecialty();
        if (employeeSpecialties != null && !employeeSpecialties.isEmpty())
            for (SpecialtyRestAnswer employeeSpecialty : employeeSpecialties) {
                Specialty specialty = convertSpecialty(employeeSpecialty);
                employee.getSpecialties().add(specialty);
            }

        return employee;
    }

    /**
     * Конвертирует дату из формата, полученного при помощи Retrofit в формат requery.io.
     * Проверяется два встречающихся варианта получаемой даты
     */
    private static Date toDate(final String stringDate) {
        if (stringDate == null || stringDate.isEmpty())
            return null;

        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        Matcher m1 = p1.matcher(stringDate);
        if (m1.matches() && m1.groupCount() == 3) {
            String result = m1.group(1) + m1.group(2) + m1.group(3);
            try {
                date = new Date(format.parse(result).getTime());
            } catch (ParseException e) {
                Log.e("ERROR", "stringToDateParser yyyyMMdd format");
            }
            return date;
        }
        Matcher m2 = p2.matcher(stringDate);
        if (m2.matches() && m2.groupCount() == 3) {
            String result = m2.group(3) + m2.group(2) + m2.group(1);
            try {
                date = new Date(format.parse(result).getTime());
            } catch (ParseException e) {
                Log.e("ERROR", "stringToDateParser ddMMyyyy format");
            }
            return date;
        }
        return date;
    }

    /**
     * Корректируется имя и фамилия в соответствии с заданием к виду:
     * первая буква - заглавная, остальные - прописные
     */
    private static String correctName(final String name) {
        if (name == null || name.isEmpty())
            return "";

        return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1).toLowerCase();
    }
}
