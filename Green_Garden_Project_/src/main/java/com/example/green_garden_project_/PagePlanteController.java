package com.example.green_garden_project_;



import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PagePlanteController {

    @FXML
    private Button returnbutton;

    @FXML
    ImageView retourimage;



    @FXML
    private TextArea nom_plantes;
    @FXML
    private Button buttonGeneral;
    @FXML
    private Button buttonGraphique;
    @FXML
    private Button buttonTaches;
    @FXML
    private RadioButton rbuttonGeneral;
    @FXML
    private RadioButton rbuttonGraphique;
    @FXML
    private RadioButton rbuttonTaches;



    // Général

    @FXML
    private Text textdescriptionplante;

    @FXML

    private HBox boxdescription;

    @FXML
    private TextArea descriptionplante;

    @FXML

    private Text textimageplantes;

    @FXML

    private HBox boximage;
    @FXML
    private HBox conteneurplante;

    @FXML
    private ImageView imageplante1;
    @FXML
    private ImageView imageplante2;
    @FXML
    private ImageView imageplante3;
    @FXML
    private ImageView imageplante4;

    @FXML

    private Button ajouterimage;

    //Taches

    @FXML

    private HBox plantationbox;
    private TextArea plantationtext;


    @FXML

    private HBox recoltebox;

    @FXML
    private TextArea recoltetext;


    @FXML

    private HBox entretienbox;
    @FXML
    private TextArea entretientext;



    @FXML

    private HBox arrosagebox;

    @FXML
    private TextArea arrosagetext;


    @FXML

    private HBox rembottagebox;
    @FXML
    private TextArea rembottagetext;






    private List<ImageView> imageViews;

    @FXML
    public void initialize() {

        imageViews = Arrays.asList(imageplante1, imageplante2, imageplante3, imageplante4);

        returnbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Fermer la fenêtre actuelle
                Stage stage = (Stage) returnbutton.getScene().getWindow();
                stage.close();
            }
        });


        buttonGraphique.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // Activer le bouton radio correspondant
                rbuttonGeneral.setSelected(false);
                rbuttonGraphique.setSelected(true);
                rbuttonTaches.setSelected(false);
                hideGeneralElements();


            }
        });

        buttonTaches.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Rendre la page générale invisible
                buttonGeneral.setVisible(true);
                rbuttonGeneral.setSelected(false);
                rbuttonGraphique.setSelected(false);
                // Activer le bouton radio correspondant
                rbuttonTaches.setSelected(true);
                hideGeneralElements();
                showTaskElements();




            }
        });

        buttonGeneral.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Rendre la page générale invisible
                buttonGeneral.setVisible(true);
                rbuttonGeneral.setSelected(true);
                rbuttonGraphique.setSelected(false);
                // Activer le bouton radio correspondant
                rbuttonTaches.setSelected(false);
                showGeneralElements();
                hideTaskElements();



            }
        });
    }








    // ...





public void setPlantName(String plantName, String description){
    nom_plantes.setText(plantName);
    descriptionplante.setText(description);
}






public void hideGeneralElements() {
    if (textdescriptionplante != null) {
        textdescriptionplante.setVisible(false);
    }
    if (boxdescription != null) {
        boxdescription.setVisible(false);
    }
    if (descriptionplante != null) {
        descriptionplante.setVisible(false);
    }
    if (textimageplantes != null) {
        textimageplantes.setVisible(false);
    }
    if (boximage != null) {
        boximage.setVisible(false);
    }
    if (conteneurplante != null) {
        conteneurplante.setVisible(false);
    }
    if (imageplante1 != null) {
        imageplante1.setVisible(false);
    }
    if (imageplante2 != null) {
        imageplante2.setVisible(false);
    }
    if (imageplante3 != null) {
        imageplante3.setVisible(false);
    }
    if (imageplante4 != null) {
        imageplante4.setVisible(false);
    }
    if (ajouterimage != null) {
        ajouterimage.setVisible(false);
    }
}

public void showGeneralElements() {
    if (textdescriptionplante != null) {
        textdescriptionplante.setVisible(true);
    }
    if (boxdescription != null) {
        boxdescription.setVisible(true);
    }
    if (descriptionplante != null) {
        descriptionplante.setVisible(true);
    }
    if (textimageplantes != null) {
        textimageplantes.setVisible(true);
    }
    if (boximage != null) {
        boximage.setVisible(true);
    }
    if (conteneurplante != null) {
        conteneurplante.setVisible(true);
    }
    if (imageplante1 != null) {
        imageplante1.setVisible(true);
    }
    if (imageplante2 != null) {
        imageplante2.setVisible(true);
    }
    if (imageplante3 != null) {
        imageplante3.setVisible(true);
    }
    if (imageplante4 != null) {
        imageplante4.setVisible(true);
    }
    if (ajouterimage != null) {
        ajouterimage.setVisible(true);
    }
}

    public void hideTaskElements() {
    if (plantationbox != null) {
        plantationbox.setVisible(false);
    }
    if (plantationtext != null) {
        plantationtext.setVisible(false);
    }
    if (recoltebox != null) {
        recoltebox.setVisible(false);
    }
    if (recoltetext != null) {
        recoltetext.setVisible(false);
    }
    if (entretienbox != null) {
        entretienbox.setVisible(false);
    }
    if (entretientext != null) {
        entretientext.setVisible(false);
    }
    if (arrosagebox != null) {
        arrosagebox.setVisible(false);
    }
    if (arrosagetext != null) {
        arrosagetext.setVisible(false);
    }
    if (rembottagebox != null) {
        rembottagebox.setVisible(false);
    }
    if (rembottagetext != null) {
        rembottagetext.setVisible(false);
    }
}

public void showTaskElements() {
    if (plantationbox != null) {
        plantationbox.setVisible(true);
    }
    if (plantationtext != null) {
        plantationtext.setVisible(true);
    }
    if (recoltebox != null) {
        recoltebox.setVisible(true);
    }
    if (recoltetext != null) {
        recoltetext.setVisible(true);
    }
    if (entretienbox != null) {
        entretienbox.setVisible(true);
    }
    if (entretientext != null) {
        entretientext.setVisible(true);
    }
    if (arrosagebox != null) {
        arrosagebox.setVisible(true);
    }
    if (arrosagetext != null) {
        arrosagetext.setVisible(true);
    }
    if (rembottagebox != null) {
        rembottagebox.setVisible(true);
    }
    if (rembottagetext != null) {
        rembottagetext.setVisible(true);
    }
}



    @FXML
    void addImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            Image selectedImage = new Image(selectedFile.toURI().toString());
            for (ImageView imageView : imageViews) {
                if (imageView.getImage() == null) {
                    imageView.setImage(selectedImage);
                    return; // Arrête la méthode après l'ajout de l'image
                }
            }
            System.out.println("Vous ne pouvez ajouter que jusqu'à 4 images.");
        }
    }

}


