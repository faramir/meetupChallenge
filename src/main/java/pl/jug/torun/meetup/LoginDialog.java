/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.meetup;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.AbstractAction;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

/**
 *
 * @author http://code.makery.ch/blog/javafx-8-dialogs/
 */
public class LoginDialog {

    final TextField username = new TextField();
    final PasswordField password = new PasswordField();
    final Action actionLogin = new AbstractAction("Login") {
        // This method is called when the login button is clicked ...
        public void handle(ActionEvent ae) {
            Dialog d = (Dialog) ae.getSource();
            // Do the login here.
            d.hide();
        }
    };

    public void start(Stage stage) {
        // Create the custom dialog.
        Dialog dlg = new Dialog(stage, "Login Dialog");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        username.setPromptText("Username");
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        ButtonBar.setType(actionLogin, ButtonType.OK_DONE);
        actionLogin.disabledProperty().set(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            actionLogin.disabledProperty().set(newValue.trim().isEmpty());
        });

        dlg.setMasthead("Look, a Custom Login Dialog");
        dlg.setContent(grid);
        dlg.getActions().addAll(actionLogin, Dialog.Actions.CANCEL);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        dlg.show();
    }
}
