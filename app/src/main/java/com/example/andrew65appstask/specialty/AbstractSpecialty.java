package com.example.andrew65appstask.specialty;

import com.example.andrew65appstask.employee.AbstractEmployee;

import java.util.List;

import io.requery.CascadeAction;
import io.requery.Entity;
import io.requery.JunctionTable;
import io.requery.Key;
import io.requery.ManyToMany;

@Entity
public abstract class AbstractSpecialty {

    @Key
    int id;

    String name;

    @JunctionTable
    @ManyToMany(cascade = {CascadeAction.DELETE, CascadeAction.SAVE})
    List<AbstractEmployee> employees;
}
