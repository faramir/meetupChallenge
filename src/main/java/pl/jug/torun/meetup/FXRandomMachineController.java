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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import pl.jug.torun.meetup.api.MeetupClient;
import pl.jug.torun.meetup.api.MeetupClientImpl;
import pl.jug.torun.meetup.api.model.Event;
import pl.jug.torun.meetup.api.model.Member;
import pl.jug.torun.meetup.model.EventGiveAway;
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
    private Tab eventsTab;
    @FXML
    private Tab giveAwaysTab;
    @FXML
    private Tab membersTab;
    @FXML
    private Tab drawTab;

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
    private TableView<EventGiveAway> giveAwaysTableView;
    @FXML
    private TableColumn<EventGiveAway, String> giveAwaysNameColumn;
    @FXML
    private TableColumn<EventGiveAway, String> giveAwaysMemberColumn;

    @FXML
    private ListView<GiveAway> definedGiveAwaysListView;

    @FXML
    private TextField defineGiveAwayType;

    @FXML
    private ListView<Event> eventsListView;

    @FXML
    private ListView<Member> eventMembersListView;

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
        if (giveAwaysTableView.getSelectionModel().isEmpty() == false) {
            EventGiveAway selectedGiveAway = giveAwaysTableView.getSelectionModel().getSelectedItem();
//            derbyDB.deleteEventGiveAway(selectedGiveAway);
            giveAwaysTableView.getItems().remove(selectedGiveAway);
        }
    }

    @FXML
    void handleAddGiveAway(ActionEvent event) {
        try {
            int count = Integer.parseInt(giveAwayCount.getText());

            GiveAway selectedGiveAway = giveAwayCombo.getSelectionModel().getSelectedItem();
            EventGiveAway eventGiveAway = new EventGiveAway(selectedGiveAway.getName(), null);
            while (count-- > 0) {
                giveAwaysTableView.getItems().add(eventGiveAway);
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
        eventsPane.setDisable(true);
        eventsListView.setItems(FXCollections.observableArrayList(meetupClient.getEvents("Torun-JUG")));
        eventsPane.setDisable(false);
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
    private void handleEventsListViewClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() < 2) {
            return;
        }

        giveAwaysTab.setDisable(true);
        membersTab.setDisable(true);
        drawTab.setDisable(true);

        Event event = eventsListView.getSelectionModel().getSelectedItem();
        eventMembersListView.setItems(FXCollections.observableArrayList(meetupClient.getMembers(event.getId())));

        giveAwaysTab.setDisable(false);
        membersTab.setDisable(false);
        drawTab.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<GiveAway> definedGiveAways = derbyDB.getGiveAwaysList();
        ObservableList<GiveAway> definedGiveAwaysObservableList = FXCollections.observableArrayList(
                definedGiveAways);
        definedGiveAwaysListView.setItems(definedGiveAwaysObservableList);
        giveAwayCombo.setItems(definedGiveAwaysObservableList);
        giveAwayCombo.getSelectionModel().select(0);

        giveAwaysNameColumn.setCellValueFactory(value -> value.getValue().getGiveAwayNameProperty());
        giveAwaysMemberColumn.setCellValueFactory(value -> value.getValue().getMemberNameProperty());

        accordion.setExpandedPane(eventsPane);

        handleRefreshEventsListView(null);

        giveAwaysTab.setDisable(true);
        membersTab.setDisable(true);
        drawTab.setDisable(true);
    }

}
