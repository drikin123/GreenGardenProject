package com.example.green_garden_project_;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class Plants extends Item {
    private final LocalDate wateringDate;
    private final LocalDate repottingDate;
    private final LocalDate maintenanceDate;
    private final LocalDate harvestDate;

    private String description;

    private Image image;
    private LocalDate plantingDate;

    private int indexPlantsToSubTask;

    public Plants(String nomPlant, Image image, LocalDate plantingDate, LocalDate repottingDate, LocalDate wateringDate, LocalDate maintenanceDate, LocalDate harvestDate,String description) {
        super(nomPlant);
        this.image = image;
        this.plantingDate = plantingDate;
        this.repottingDate = repottingDate;
        this.wateringDate = wateringDate;
        this.maintenanceDate = maintenanceDate;
        this.harvestDate = harvestDate;
        this.indexPlantsToSubTask = 0;
        this.description=description;
    }

    public Plants(String nomPlant) {
        super(nomPlant);
        this.image = null;
        this.plantingDate = null;
        this.repottingDate = null;
        this.wateringDate = null;
        this.maintenanceDate = null;
        this.harvestDate = null;
        this.indexPlantsToSubTask = 0;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public int getIndexPlantsToSubTask() {
        return indexPlantsToSubTask;
    }

    public void setIndexPlantsToSubTask(int indexPlantsToSubTask) {
        this.indexPlantsToSubTask = indexPlantsToSubTask;
    }

    public LocalDate getWateringDate() {
        return wateringDate;
    }

    public LocalDate getRepottingDate() {
        return repottingDate;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    @Override
    public String toString() {
        return "Plant : " + getName();
    }

    public String getDescription() { return description; }
}
