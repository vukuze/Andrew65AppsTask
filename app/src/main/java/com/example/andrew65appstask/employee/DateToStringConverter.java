package com.example.andrew65appstask.employee;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Содержит статические функции для перевода объектов
 * из формата Date базы данных в требуемый формат String
 */

public class DateToStringConverter {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Переводит дату из формата БД в требуемый для приложения
     */
    public static String getBirthday(Date birthday) {
        return birthday != null ? formatter.format(birthday) : "-";
    }

    /**
     * Переводит возраст из формата БД в требуемый для приложения
     */
    public static String getAge(Date birthday) {
        LocalDate now = new LocalDate();
        int age = Years.yearsBetween(new LocalDate(birthday), now).getYears();
        return age > 0 ? Integer.toString(age) : "-";
    }
}
