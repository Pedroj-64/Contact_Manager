package co.edu.uniquindio.poo.contact_manager.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedList;

@Data
public class ContactManagement implements ActionsContactList {
    // Instancia única estática y volatile para thread-safety
    private static volatile ContactManagement instance;

    private LinkedList<Contact> contacts;

    // Constructor privado para prevenir instanciación directa
    private ContactManagement() {
        contacts = new LinkedList<>();
    }

    // Método público estático para obtener la instancia única (versión thread-safe)
    public static ContactManagement getInstance() {
        if (instance == null) {
            synchronized (ContactManagement.class) {
                if (instance == null) {
                    instance = new ContactManagement();
                }
            }
        }
        return instance;
    }

    /**
     * @param name
     * @param lastName
     * @param phoneNumber
     * @param email
     * @param profilePicture
     * @param CumFatherDate
     * @param typeContact
     */
    @Override
    public void AddContact(String name, String lastName, String phoneNumber, String email, String profilePicture,
                           LocalDate CumFatherDate, TypeContact typeContact) {
        for (Contact c : contacts) {
            if(c.getPhoneNumber().equals(phoneNumber)){
                System.out.println("This Contact Already Exist");
                return; // Salir del método si el contacto ya existe
            }
        }
        contacts.add(new Contact(name, lastName, phoneNumber, email, CumFatherDate, profilePicture, typeContact));
    }

    /**
     * @param contact
     */
    @Override
    public void DeleteContact(Contact contact) throws Exception {
        if (!contacts.remove(contact)) {
            throw new Exception("Contact Not Found");
        }
    }

    /**
     * @param name
     */
    @Override
    public void UpdateContact(String name, String lastName, String phoneNumber, String email, String profilePicture,
                              LocalDate CumFatherDate, TypeContact typeContact) {
        for (Contact c : contacts) {
            if(c.getPhoneNumber().equals(phoneNumber)){
                c.setName(name);
                c.setLastName(lastName);
                c.setPhoneNumber(phoneNumber);
                c.setEmail(email);
                c.setProfilePicture(profilePicture);
                c.setCumFatherDate(CumFatherDate);
                c.setTypeContact(typeContact);
                return; // Salir después de actualizar
            }
        }
    }

    /**
     * @param name
     */
    @Override
    public Contact SearchContactName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        for (Contact c : contacts) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        throw new RuntimeException("Contact not found");
    }

    /**
     * @param phoneNumber
     */
    @Override
    public Contact SearchContactPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }

        for (Contact c : contacts) {
            if (c.getPhoneNumber().equals(phoneNumber)) {
                return c;
            }
        }

        throw new RuntimeException("Contact not found");
    }
}