package co.edu.uniquindio.poo.contact_manager.Model;

import co.edu.uniquindio.poo.contact_manager.Model.TypeContact;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
public class Contact{
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDate CumFatherDate;
    private String profilePicture;
    private TypeContact typeContact;


    public Contact(String name, String lastName, String phoneNumber, String email, LocalDate CumFatherDate, String profilePicture, TypeContact typeContact) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.CumFatherDate = CumFatherDate;
        this.profilePicture = profilePicture;
        this.typeContact = typeContact;
    }



}
