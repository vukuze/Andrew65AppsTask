package com.andrew.personnel.data;

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
