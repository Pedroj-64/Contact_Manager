module co.edu.uniquindio.poo.contact_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;

    opens co.edu.uniquindio.poo.contact_manager to javafx.fxml;
    exports co.edu.uniquindio.poo.contact_manager;

    opens co.edu.uniquindio.poo.contact_manager.ViewController to javafx.fxml;
    exports co.edu.uniquindio.poo.contact_manager.ViewController;

    opens co.edu.uniquindio.poo.contact_manager.Controller to javafx.fxml;
    exports co.edu.uniquindio.poo.contact_manager.Controller;
}