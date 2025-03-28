package co.edu.uniquindio.poo.contact_manager.ViewController;

import co.edu.uniquindio.poo.contact_manager.App;
import co.edu.uniquindio.poo.contact_manager.Controller.menuInicioController;
import co.edu.uniquindio.poo.contact_manager.Model.Contact;
import co.edu.uniquindio.poo.contact_manager.Model.TypeContact;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.time.LocalDate;

public class StartMenuViewController {

    @FXML
    private DatePicker Date_CumFatherDay;

    @FXML
    private Button btn_ChangePicture, btn_addContact, btn_delete, btn_searchContact;

    @FXML
    private ComboBox<String> combo_searchBy;

    @FXML
    private TableView<Contact> tbl_ContactList;

    @FXML
    private TableColumn<Contact, String> tcl_Name, tcl_lastName, tcl_number;

    @FXML
    private TextField txt_email, txt_lastName, txt_name, txt_phoneNumber, txt_searchContact, txt_type;

    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    menuInicioController menuInicioControllerN = new menuInicioController();

    @FXML
    void initialize() {
        configureColumns();
        loadContacts();
        configureComboBox();
        configureTableListener();
        configureButtons();
    }

    private void configureColumns() {
        tcl_Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        tcl_lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        tcl_number.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
    }

    private void loadContacts() {
        contacts.setAll(menuInicioControllerN.getContacts());
        tbl_ContactList.setItems(contacts);
    }

    private void configureComboBox() {
        combo_searchBy.setItems(FXCollections.observableArrayList(TypeContact.Professional.name(), TypeContact.NoProfessional.name()));
    }

    private void configureTableListener() {
        tbl_ContactList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txt_name.setText(newSelection.getName());
                txt_lastName.setText(newSelection.getLastName());
                txt_phoneNumber.setText(newSelection.getPhoneNumber());
                txt_email.setText(newSelection.getEmail());
                txt_type.setText(newSelection.getTypeContact().name());
                Date_CumFatherDay.setValue(newSelection.getCumFatherDate());
            }
        });
    }

    private void configureButtons() {
        btn_addContact.setOnAction(event -> addContact());
        btn_ChangePicture.setOnAction(event -> changePicture());
        btn_delete.setOnAction(event -> deleteContact());
        btn_searchContact.setOnAction(event -> searchContact());
    }

    private void addContact() {
        try {
            String profilePicture = "resources/ProfileDefault.png";
            Contact newContact = new Contact(txt_name.getText(), txt_lastName.getText(), txt_phoneNumber.getText(), txt_email.getText(), Date_CumFatherDay.getValue(), profilePicture, TypeContact.valueOf(txt_type.getText()));
            menuInicioControllerN.getContacts().add(newContact);
            loadContacts();
        } catch (Exception e) {
            App.showAlert("Error", "Failed to add contact: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void changePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Contact selectedContact = tbl_ContactList.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                selectedContact.setProfilePicture(selectedFile.getAbsolutePath());
                App.showAlert("Success", "Profile picture updated!", Alert.AlertType.INFORMATION);
            } else {
                App.showAlert("Warning", "Please select a contact first.", Alert.AlertType.WARNING);
            }
        }
    }

    private void deleteContact() {
        Contact selectedContact = tbl_ContactList.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            contacts.remove(selectedContact);
            App.showAlert("Success", "Contact deleted successfully!", Alert.AlertType.INFORMATION);
        } else {
            App.showAlert("Warning", "Please select a contact to delete.", Alert.AlertType.WARNING);
        }
    }

    private void searchContact() {
        String searchName = txt_searchContact.getText();
        String searchType = combo_searchBy.getValue();

        if (searchName.isEmpty() || searchType == null) {
            App.showAlert("Warning", "Please enter a name and select a category.", Alert.AlertType.WARNING);
            return;
        }

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName) && contact.getTypeContact().name().equals(searchType)) {
                App.showAlert("Contact Found", "Name: " + contact.getName() + "\nLast Name: " + contact.getLastName() + "\nPhone: " + contact.getPhoneNumber() + "\nEmail: " + contact.getEmail(), Alert.AlertType.INFORMATION);
                return;
            }
        }

        App.showAlert("Not Found", "No contact found with the given criteria.", Alert.AlertType.WARNING);
    }
}