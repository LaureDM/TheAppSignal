package com.theappsignal.lauresoft.theappsignal.domain;

import java.io.Serializable;

/**
 * Created by Laure on 9/11/2014.
 */
public class Contact implements Serializable
{
    private String name;
    private String number;
    private boolean selected;

    public Contact(String name, String number, boolean selected) {
        setName(name);
        setNumber(number);
        setSelected(selected);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
