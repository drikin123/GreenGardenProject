package com.example.green_garden_project_;


import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;


public class PlantListCell extends ListCell<Plants> {
    private ImageView imageView;

    public PlantListCell() {
        imageView = new ImageView();
        imageView.setFitWidth(100); // Ajustez la taille de l'image selon vos besoins
        imageView.setFitHeight(100);
        setGraphic(imageView);
    }

    @Override
    protected void updateItem(Plants plant, boolean empty) {
        super.updateItem(plant, empty);

        if (empty || plant == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Définir le texte de la cellule avec le titre de la plante
            setText(plant.getName());

            // Définir une image pour la cellule
            ImageView imageView = new ImageView(plant.getImage());
            imageView.setFitWidth(100); // Définir la largeur de l'image
            imageView.setFitHeight(100); // Définir la hauteur de l'image
            setGraphic(imageView);
        }
    }

}

