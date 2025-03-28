package co.edu.uniquindio.poo.contact_manager;

import co.edu.uniquindio.poo.contact_manager.Model.ContactManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static ContactManagement contactManager; // Referencia al singleton

    @Override
    public void start(Stage stage) {
        try {
            // Inicializar el gestor de contactos (singleton)
            contactManager = ContactManagement.getInstance();

            // Cargar la escena de introducción desde el archivo FXML
            scene = new Scene(loadFXML("StartMenu"), 600, 340);
            stage.setScene(scene); // Establecer la escena en el escenario
            stage.show(); // Mostrar la escena
        } catch (IOException e) {
            showAlert("Error al cargar la interfaz", "No se pudo cargar el archivo FXML: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Obtiene la instancia singleton del gestor de contactos
     * @return Instancia de ContactManagement
     */
    public static ContactManagement getContactManager() {
        if (contactManager == null) {
            contactManager = ContactManagement.getInstance();
        }
        return contactManager;
    }

    // Resto de los métodos permanecen igual...
    public static void loadScene(String fxml, double width, double height) {
        try {
            Parent root = loadFXML(fxml);
            scene.setRoot(root);
            scene.getWindow().setWidth(width);
            scene.getWindow().setHeight(height);
        } catch (IOException e) {
            showAlert("Error al cambiar la vista", "No se pudo cargar el archivo FXML: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showAlertAndRedirect(String title, String message, Alert.AlertType type, String fxml, double width, double height) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setOnHidden(evt -> loadScene(fxml, width, height));
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}