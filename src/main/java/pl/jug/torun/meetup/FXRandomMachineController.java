/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.meetup;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import pl.jug.torun.meetup.api.MeetupClient;
import pl.jug.torun.meetup.api.MeetupClientImpl;
import pl.jug.torun.meetup.api.model.Event;
import pl.jug.torun.meetup.model.GiveAway;

/**
 *
 * @author faramir
 */
public class FXRandomMachineController implements Initializable {

    final private MeetupClient meetupClient;
    final private DerbyConnection derbyDB;

    public FXRandomMachineController() {
        derbyDB = new DerbyConnection();
        derbyDB.initialize();
        meetupClient = new MeetupClientImpl();
    }

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane definedGiveAwaysPane;

    @FXML
    private TitledPane eventsPane;

    @FXML
    private ComboBox<GiveAway> giveAwayCombo;

    @FXML
    private Label memberLabel;

    @FXML
    private Label giveAwayLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<GiveAway> giveAwaysListView;

    @FXML
    private ListView<GiveAway> definedGiveAwaysListView;

    @FXML
    private TextField defineGiveAwayType;

    @FXML
    private ListView<Event> eventsListView;

    @FXML
    private ListView<?> eventMembersListView;

    @FXML
    private Button drawButton;

    @FXML
    private Button acceptButton;

    @FXML
    private TextField giveAwayCount;

    @FXML
    void handleDeleteDefinedGiveAway(ActionEvent event) {
        if (definedGiveAwaysListView.getSelectionModel().isEmpty() == false) {
            GiveAway selectedGiveAway = definedGiveAwaysListView.getSelectionModel().getSelectedItem();
            derbyDB.deleteDefinedGiveAway(selectedGiveAway);
            definedGiveAwaysListView.getItems().remove(selectedGiveAway);
        }
    }

    @FXML
    void handleDefineGiveAway(ActionEvent event) {
        String name = defineGiveAwayType.getText();
        GiveAway newGiveAway = derbyDB.defineGiveAway(name);
        definedGiveAwaysListView.getItems().add(newGiveAway);
        defineGiveAwayType.clear();
    }

    @FXML
    void handleDeleteGiveAway(ActionEvent event) {
        if (giveAwaysListView.getSelectionModel().isEmpty() == false) {
            GiveAway selectedGiveAway = giveAwaysListView.getSelectionModel().getSelectedItem();
            derbyDB.deleteDefinedGiveAway(selectedGiveAway);
            giveAwaysListView.getItems().remove(selectedGiveAway);
        }
    }

    @FXML
    void handleAddGiveAway(ActionEvent event) {
        try {
            int count = Integer.parseInt(giveAwayCount.getText());

            GiveAway selectedGiveAway = giveAwayCombo.getSelectionModel().getSelectedItem();
            while (count-- > 0) {
                giveAwaysListView.getItems().add(selectedGiveAway);
            }
        } catch (NumberFormatException ex) {
            Dialogs.create()
                    .owner((Stage) accordion.getScene().getWindow())
                    .title(((Stage) accordion.getScene().getWindow()).getTitle())
                    .message("Niepoprawna liczba!")
                    .showError();
        }
    }

    @FXML
    void handleRefreshEventMembersListView(ActionEvent event) {

    }

    @FXML
    void handleRefreshEventsListView(ActionEvent event) {
        eventsListView.setItems(FXCollections.observableArrayList(meetupClient.getEvents("Torun-JUG")));
    }

    @FXML
    void handleDraw(ActionEvent event) {

    }

    @FXML
    void handleAcceptButton(ActionEvent event) {

    }

    @FXML
    void handleCancelButton(ActionEvent event) {

    }

    @FXML
    private void handleEventsListViewClicked(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            System.out.println("onClicked: " + event.getClickCount());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<GiveAway> definedGiveAways = derbyDB.getGiveAwaysList();
        ObservableList<GiveAway> definedGiveAwaysObservableList = FXCollections.observableArrayList(
                definedGiveAways);
        definedGiveAwaysListView.setItems(definedGiveAwaysObservableList);
        giveAwayCombo.setItems(definedGiveAwaysObservableList);
        giveAwayCombo.getSelectionModel().select(0);

        accordion.setExpandedPane(eventsPane);

        handleRefreshEventsListView(null);
    }

}
