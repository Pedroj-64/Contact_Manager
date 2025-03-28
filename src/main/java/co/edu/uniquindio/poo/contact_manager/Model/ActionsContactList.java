package co.edu.uniquindio.poo.contact_manager.Model;

import co.edu.uniquindio.poo.contact_manager.Model.Contact;
import co.edu.uniquindio.poo.contact_manager.Model.TypeContact;

import java.time.LocalDate;

public interface ActionsContactList {

    public void AddContact(String name, String lastName, String phoneNumber, String email, String profilePicture,
    LocalDate CumFatherDate,
    TypeContact typeContact);
    public void DeleteContact(Contact contact) throws Exception;
    public void UpdateContact(String name, String lastName, String  phoneNumber, String email, String profilePicture,
                              LocalDate CumFatherDate,
                              TypeContact typeContact);
    public Contact SearchContactName(String name);
    public Contact SearchContactPhoneNumber(String phoneNumber);
}
