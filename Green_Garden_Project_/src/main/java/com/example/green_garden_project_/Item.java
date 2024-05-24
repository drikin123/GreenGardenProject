package com.example.green_garden_project_;

import java.time.LocalDate;

public abstract class Item {
    protected String name;
    protected int index;
    protected boolean checked;

    public Item(String name) {
        this.name = name;
        this.index = 0;
        this.checked = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isChecked() {
        return checked;
    }

    public void decrementeIndex() {
        this.index--;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Item : " + name;
    }
}

