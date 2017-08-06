package com.example.andrew65appstask.data;

import java.sql.Date;
import java.util.List;

import io.requery.CascadeAction;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.ManyToMany;
import io.requery.Nullable;

@Entity
public abstract class AbstractEmployee {

    @Key
    @Generated
    int id;

    String fName;
    String lName;
    @Nullable
    Date birthday;
    @Nullable
    String avatrUrl;

    @ManyToMany(cascade = {CascadeAction.DELETE, CascadeAction.SAVE})
    List<AbstractSpecialty> specialties;
}

