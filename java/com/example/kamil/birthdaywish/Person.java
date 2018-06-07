package com.example.kamil.birthdaywish;

import io.realm.RealmObject;

public class Person extends RealmObject
{
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
}
