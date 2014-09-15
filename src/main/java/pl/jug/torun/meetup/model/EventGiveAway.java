/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.jug.torun.meetup.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author faramir
 */
public class EventGiveAway {

    private int id;
    private StringProperty giveAwayNameProperty;
    private StringProperty memberNameProperty;

    public EventGiveAway(String giveAwayName, String memberName) {
        this.giveAwayNameProperty = new SimpleStringProperty(giveAwayName);
        this.memberNameProperty = new SimpleStringProperty(memberName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGiveAwayName() {
        return getGiveAwayNameProperty().get();
    }

    public void setGiveAwayName(String giveAwayName) {
        this.getGiveAwayNameProperty().set(giveAwayName);
    }

    public String getMemberName() {
        return getMemberNameProperty().get();
    }

    public void setMemberName(String memberName) {
        this.getMemberNameProperty().set(memberName);
    }

    public StringProperty getGiveAwayNameProperty() {
        return giveAwayNameProperty;
    }

    public StringProperty getMemberNameProperty() {
        return memberNameProperty;
    }

}
