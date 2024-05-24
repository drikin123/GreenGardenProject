package com.example.green_garden_project_;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class PagePlanteApplication extends Application {

    private static String plantName;
    private static String description;

    public static void setPlantName(String plantName, String description) {
        PagePlanteApplication.plantName = plantName;
        PagePlanteApplication.description = description;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("page_plante.fxml"));
        Parent root = loader.load();

        // Passer le nom de la plante à PagePlanteController
        PagePlanteController controller = loader.getController();
        controller.setPlantName(plantName, description);

        primaryStage.setTitle("Page Plante");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    // ...
}

