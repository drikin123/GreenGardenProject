package com.example.green_garden_project_;

import java.time.LocalDate;
import java.util.List;

public class SubTask extends Item {

    private int indexSubtaskToTask;

    public SubTask(String nameSubTask) {
        super(nameSubTask);
        this.indexSubtaskToTask = 0;
    }

    public int getIndexSubtaskToTask() {
        return indexSubtaskToTask;
    }

    public void setIndexSubtaskToTask(int indexSubtaskToTask) {
        this.indexSubtaskToTask = indexSubtaskToTask;
    }

    @Override
    public String toString() {
        return "SubTask : " + getName();
    }
}
