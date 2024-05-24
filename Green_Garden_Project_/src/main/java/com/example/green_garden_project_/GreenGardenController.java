package com.example.green_garden_project_;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GreenGardenController {

    static int indexList = 0;
    static int indexList2 = 0;
    static int indexList3 = 0;


    private List<Item> listDesItems;
    private List<Plants> listDesPlantes;
    private String imagePath;

    public GreenGardenController() {
        this.listDesItems = new ArrayList<>();
        this.listDesPlantes = new ArrayList<>();
    }

    public List<Item> getListDesItems() {
        return listDesItems;
    }

    public void setListDesItems(List<Item> listDesItems) {
        this.listDesItems = listDesItems;
    }

    public List<Plants> getListDesPlantes() {
        return listDesPlantes;
    }

    public void setListDesPlantes(List<Plants> listDesPlantes) {
        this.listDesPlantes = listDesPlantes;
    }

    @FXML
    private ImageView imageLogo;

    @FXML
    private Button buttonAgenda;

    @FXML
    private Button buttonPlantes;

    @FXML
    private RadioButton rbuttonAgenda;

    @FXML
    private RadioButton rbuttonPlantes;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonDelete;

    @FXML
    private TreeView<Item> treeAgenda;

    @FXML
    private TextField textAdd;

    @FXML
    private TextField textAdd1;

    @FXML
    private DatePicker dateAdd;

    @FXML
    private Button btnajtplants;

    @FXML
    private ListView<Plants> listplants;

    @FXML
    private Pane addPlantForm;

    @FXML
    private TextField plantTitleInput;

    @FXML
    private DatePicker plantDateInput;

    @FXML
    private DatePicker plantRepottingDateInput;

    @FXML
    private DatePicker plantWateringDateInput;

    @FXML
    private DatePicker plantMaintenanceDateInput;

    @FXML
    private DatePicker plantHarvestDateInput;

    @FXML
    private ImageView image;

    @FXML
    private Label letext;

    @FXML
    private  TextArea descrip;

    @FXML
    public void initialize() {

        listplants.setCellFactory(param -> new PlantListCell());


        TreeItem<Item> root = new TreeItem<>(new Task(""));
        treeAgenda.setRoot(root);
        treeAgenda.setShowRoot(false);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Supprimer");
        MenuItem editMenuItem = new MenuItem("Modifier");
        MenuItem addChildMenuItem = new MenuItem("Ajouter une sous tache");

        deleteMenuItem.setOnAction(event -> onDeleteButtonClick(null));
        editMenuItem.setOnAction(event -> onEditButtonClick(null));
        addChildMenuItem.setOnAction(event -> onAddChildButtonClick(null));

        contextMenu.getItems().addAll(addChildMenuItem, editMenuItem, deleteMenuItem);
        treeAgenda.setContextMenu(contextMenu);


        treeAgenda.setCellFactory(new Callback<TreeView<Item>, TreeCell<Item>>() {
            @Override
            public TreeCell<Item> call(TreeView<Item> param) {
                return new TreeCellImpl();
            }
        });

        listplants.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    // Obtenir la plante sélectionnée
                    Plants selectedPlant = listplants.getSelectionModel().getSelectedItem();

                    if (selectedPlant != null) {
                        // Lancer PagePlanteApplication avec le nom de la plante en argument
                        Platform.runLater(() -> {
                            try {
                                PagePlanteApplication.setPlantName(selectedPlant.getName(),selectedPlant.getDescription());
                                new PagePlanteApplication().start(new Stage());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            }
        });

        loadAndDisplayTasks();

    }


    private void loadAndDisplayTasks() {

    }


    @FXML
    private void onAnchorPaneClicked(MouseEvent event) {
        if (!isTreeViewClicked(event)) {
            treeAgenda.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void goToPlants(MouseEvent event){
        if (event.getClickCount() == 2) {
            // Obtenir l'élément sélectionné dans la TreeView
            TreeItem<Item> selectedItem = treeAgenda.getSelectionModel().getSelectedItem();

            // Vérifier si l'élément sélectionné est une plante
            if (selectedItem != null && selectedItem.getValue() instanceof Plants) {
                rbuttonAgenda.setSelected(false);
                rbuttonPlantes.setSelected(true);
                treeAgenda.setVisible(false);
                buttonAdd.setVisible(false);
                textAdd.setVisible(false);
                textAdd1.setVisible(false);
                dateAdd.setVisible(false);
                treeAgenda.getSelectionModel().clearSelection();
                btnajtplants.setVisible(true);
                listplants.setVisible(true);
                btnajtplants.setVisible(true);
                addPlantForm.setVisible(false);
            }
        }
    }

    private boolean isTreeViewClicked(MouseEvent event) {
        Node source = (Node) event.getTarget();
        return source instanceof TreeCell || source instanceof TextField || source instanceof DatePicker;
    }

    @FXML
    void onAgendaButtonClick(javafx.event.ActionEvent event) {
        if (event.getSource() == buttonAgenda) {
            rbuttonAgenda.setSelected(true);
            rbuttonPlantes.setSelected(false);
            treeAgenda.setVisible(true);
            buttonAdd.setVisible(true);
            textAdd.setVisible(true);
            textAdd1.setVisible(true);
            dateAdd.setVisible(true);
            listplants.setVisible(false);
            btnajtplants.setVisible(false);
            addPlantForm.setVisible(false);
        }
    }

    @FXML
    void onPlantesButtonClick(javafx.event.ActionEvent event) {
        if (event.getSource() == buttonPlantes) {
            rbuttonAgenda.setSelected(false);
            rbuttonPlantes.setSelected(true);
            treeAgenda.setVisible(false);
            buttonAdd.setVisible(false);
            textAdd.setVisible(false);
            textAdd1.setVisible(false);
            dateAdd.setVisible(false);
            treeAgenda.getSelectionModel().clearSelection();
            btnajtplants.setVisible(true);
            listplants.setVisible(true);
            btnajtplants.setVisible(true);
            addPlantForm.setVisible(false);
        }
    }



    @FXML
    void onAddButtonClick(javafx.event.ActionEvent event) {
        Task task = new Task(dateAdd.getValue());
        Plants plants = new Plants(textAdd.getText());
        SubTask subTask = new SubTask(textAdd1.getText());


        TreeItem<Item> selectedItem = treeAgenda.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getParent() != null && selectedItem.getParent().getParent() != null && selectedItem.getParent().getParent().getParent() != null) {
            // Ne pas ajouter de sous-tâche si le grand-père est null (c'est-à-dire s'il n'y a que deux niveaux)
            return;
        }

        if (selectedItem != null) {
            if(selectedItem.getParent() != null){
                if(selectedItem.getParent().getParent() != null && textAdd1.getText().isEmpty() == false){
                    // Ajoutez du texte en tant que petit-fils
                    subTask.setIndex(indexList3);
                    indexList3++;
                    subTask.setIndexSubtaskToTask(selectedItem.getParent().getValue().getIndex());
                    listDesItems.add(subTask);
                    TreeItem<Item> newChild = new TreeItem<>(subTask);
                    selectedItem.getChildren().add(newChild);
                    System.out.println("SubTask ID : " +subTask.getIndex() + " ,SubTask ID+ : " +subTask.getIndexSubtaskToTask());
                } else if (textAdd.getText().isEmpty() == false){
                    // Ajouter une plante en tant que fils
                    plants.setIndex(indexList2);
                    indexList2++;
                    plants.setIndexPlantsToSubTask(selectedItem.getParent().getValue().getIndex());
                    listDesItems.add(plants);
                    TreeItem<Item> newChild = new TreeItem<>(plants);
                    selectedItem.getChildren().add(newChild);
                    System.out.println("Plants ID : " +plants.getIndex() + " ,Plants ID+ : " +plants.getIndexPlantsToSubTask() + " ,Task : "+selectedItem.getParent().getValue().getIndex());
                }
            }
        } else if (dateAdd.getValue() != null){
            // Ajouter une tâche à la racine
            task.setIndex(indexList);
            indexList++;
            listDesItems.add(task);
            TreeItem<Item> newChild = new TreeItem<>(task);
            treeAgenda.getRoot().getChildren().add(newChild);
            System.out.println("Task ID : " +task.getIndex());
        }
        sortTasksByDate(treeAgenda.getRoot());
        textAdd.clear(); textAdd1.clear(); dateAdd.setValue(null);
    }

    private void sortTasksByDate(TreeItem<Item> parent) {
        ObservableList<TreeItem<Item>> children = parent.getChildren();

        children.sort((o1, o2) -> {
            LocalDate date1 = null;
            LocalDate date2 = null;

            if (o1.getValue() instanceof Task) {
                date1 = ((Task) o1.getValue()).getDateTask();
            }

            if (o2.getValue() instanceof Task) {
                date2 = ((Task) o2.getValue()).getDateTask();
            }

            if (date1 == null && date2 == null) {
                return 0;
            } else if (date1 == null) {
                return -1;
            } else if (date2 == null) {
                return 1;
            } else {
                return date1.compareTo(date2);
            }
        });

        for (TreeItem<Item> child : children) {
            sortTasksByDate(child);
        }
    }


    @FXML
    void onEditButtonClick(javafx.event.ActionEvent event) {
        TreeItem<Item> selectedItem = treeAgenda.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Item item = selectedItem.getValue();

            if (item instanceof Plants && !textAdd.getText().isEmpty()) {
                item.setName(textAdd.getText());
            } else if (item instanceof Task && dateAdd.getValue() != null) {
                ((Task) item).setDateTask(dateAdd.getValue());
            } else if (item instanceof SubTask && !textAdd1.getText().isEmpty()) {
                item.setName(textAdd1.getText());
            } else {
                return;
            }

            selectedItem.setValue(item);

            // Après la modification, triez les tâches par date
            sortTasksByDate(treeAgenda.getRoot());
        }

        textAdd.clear(); textAdd1.clear(); dateAdd.setValue(null);
    }



    @FXML
    void onDeleteButtonClick(javafx.event.ActionEvent event) {
        TreeItem<Item> selectedItem = treeAgenda.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listDesItems.remove(selectedItem.getValue());
            selectedItem.getParent().getChildren().remove(selectedItem);
            updateParentState(selectedItem.getParent());
        }
    }

    @FXML
    void onAddChildButtonClick(javafx.event.ActionEvent event) {
        Task task = new Task(dateAdd.getValue());
        SubTask subTask = new SubTask(textAdd.getText());
        Plants plants = new Plants(textAdd.getText());

        TreeItem<Item> selectedItem = treeAgenda.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getParent() != null && selectedItem.getParent().getParent() != null && selectedItem.getParent().getParent().getParent() != null) {
            // Ne pas ajouter de sous-tâche si le grand-père est null (c'est-à-dire s'il n'y a que deux niveaux)
            return;
        }

        if (selectedItem != null) {
            if(selectedItem.getParent() != null){
                if(selectedItem.getParent().getParent() != null){
                    // Ajoutez du texte en tant que petit-fils
                    subTask.setIndex(indexList3);
                    indexList3++;
                    subTask.setIndexSubtaskToTask(selectedItem.getParent().getValue().getIndex());
                    listDesItems.add(subTask);
                    TreeItem<Item> newChild = new TreeItem<>(subTask);
                    selectedItem.getChildren().add(newChild);
                    System.out.println("SubTask ID : " +subTask.getIndex() + " ,SubTask ID+ : " +subTask.getIndexSubtaskToTask());
                } else {
                    // Ajouter une plante en tant que fils
                    plants.setIndex(indexList2);
                    indexList2++;
                    plants.setIndexPlantsToSubTask(selectedItem.getParent().getValue().getIndex());
                    listDesItems.add(plants);
                    TreeItem<Item> newChild = new TreeItem<>(plants);
                    selectedItem.getChildren().add(newChild);
                    System.out.println("Plants ID : " +plants.getIndex() + " ,Plants ID+ : " +plants.getIndexPlantsToSubTask());
                }
            }
        } else {
            // Ajouter une tâche à la racine
            task.setIndex(indexList);
            indexList++;
            listDesItems.add(task);
            TreeItem<Item> newChild = new TreeItem<>(task);
            treeAgenda.getRoot().getChildren().add(newChild);
            System.out.println("Task ID : " +task.getIndex());
        }

        textAdd.clear(); textAdd1.clear(); dateAdd.setValue(null);
    }


    private class TreeCellImpl extends TreeCell<Item> {
        private CheckBox checkBox;

        public TreeCellImpl() {
            checkBox = new CheckBox();
            checkBox.setOnAction(event -> {
                TreeItem<Item> item = getTreeItem();
                if (item != null) {
                    Item item2 = item.getValue();
                    item2.setChecked(checkBox.isSelected());
                    updateChildrenState(item, checkBox.isSelected());
                    updateParentState(item.getParent());
                }
            });
        }

        @Override
        protected void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(checkBox);
                setText(item.toString());
                checkBox.setSelected(item.isChecked());
            }
        }



        private void updateChildrenState(TreeItem<Item> parent, boolean selected) {
            for (TreeItem<Item> child : parent.getChildren()) {
                child.getValue().setChecked(selected);
                updateChildrenState(child, selected);
            }
        }
    }

    private void updateParentState(TreeItem<Item> parent) {
        if (parent != null && parent != treeAgenda.getRoot()) {
            boolean allChildrenChecked = true;
            for (TreeItem<Item> child : parent.getChildren()) {
                if (!child.getValue().isChecked()) {
                    allChildrenChecked = false;
                    break;
                }
            }
            parent.getValue().setChecked(allChildrenChecked);
            updateParentState(parent.getParent());
        }
    }

    @FXML
    void btnajtplantsClick(ActionEvent event) {
        resetPlantForm();
        addPlantForm.setVisible(true);
        image.setImage(null);
        letext.setVisible(true);

    }

    @FXML
    void addPlant(ActionEvent event) {
        String title = plantTitleInput.getText();
        LocalDate plantingDate = plantDateInput.getValue();
        LocalDate repottingDate = plantRepottingDateInput.getValue();
        LocalDate wateringDate = plantWateringDateInput.getValue();
        LocalDate maintenanceDate = plantMaintenanceDateInput.getValue();
        LocalDate harvestDate = plantHarvestDateInput.getValue();
        String description = descrip.getText();


        // Vérifie si toutes les dates sont sélectionnées
        if (plantingDate != null && repottingDate != null && wateringDate != null && maintenanceDate != null && harvestDate != null) {
            // Vérifie si imagePath n'est pas null
            if (imagePath != null) {
                // Crée une instance d'image à partir du chemin
                Image selectedImage = new Image(imagePath);

                // Crée une instance de plante avec l'image sélectionnée
                Plants newPlant = new Plants(title, selectedImage, plantingDate, repottingDate, wateringDate, maintenanceDate, harvestDate,description);

                // Ajoute la plante à la liste
                listDesPlantes.add(newPlant);

                // Rafraîchit la vue des plantes
                refreshPlantsListView();

                // Masque le formulaire d'ajout de plante
                addPlantForm.setVisible(false);
            } else {
                // Affiche un message d'erreur si imagePath est null
                System.err.println("Erreur : Le chemin de l'image est null.");
            }
        } else {
            // Affiche un message d'erreur si toutes les dates ne sont pas sélectionnées
            System.err.println("Erreur : Toutes les dates doivent être sélectionnées.");
        }
    }


    @FXML
    void cancelAddPlant(ActionEvent event) {
        addPlantForm.setVisible(false);
    }

    private void refreshPlantsListView() {
        listplants.setCellFactory(param -> new PlantListCell());
        listplants.getItems().setAll(listDesPlantes);
    }

    @FXML
    private void selectImage(MouseEvent event) {
        letext.setVisible(false);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString();
            Image selectedImage = new Image(imagePath);
            image.setImage(selectedImage);
        }else{
            letext.setVisible(true);
        }
    }

    private void resetPlantForm() {
        plantTitleInput.clear();
        plantDateInput.setValue(null);
        plantRepottingDateInput.setValue(null);
        plantWateringDateInput.setValue(null);
        plantMaintenanceDateInput.setValue(null);
        plantHarvestDateInput.setValue(null);
        // Ajouter la réinitialisation de la date de plantation
        plantDateInput.setValue(null);
        descrip.clear();
    }





}
