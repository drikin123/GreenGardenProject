module com.example.green_garden_project_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.green_garden_project_ to javafx.fxml;
    exports com.example.green_garden_project_;
}