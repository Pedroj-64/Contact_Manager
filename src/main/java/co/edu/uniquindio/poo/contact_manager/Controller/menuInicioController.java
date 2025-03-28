package co.edu.uniquindio.poo.contact_manager.Controller;

import co.edu.uniquindio.poo.contact_manager.App;
import co.edu.uniquindio.poo.contact_manager.Model.Contact;
import co.edu.uniquindio.poo.contact_manager.Model.ContactManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;
import lombok.Getter;

@Data
public class menuInicioController {

    ContactManagement contactManagement= App.getContactManager();
    @Getter
    Contact selectedContact = null;

    public ObservableList<Contact> getContacts() {
        if (contactManagement == null) {
            throw new IllegalStateException("El ContactManager no está inicializado.");
        }
        return FXCollections.observableList(contactManagement.getContacts());
    }

}
