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
public class GiveAway {

    private int id;
    final private StringProperty nameProperty = new SimpleStringProperty();

    public GiveAway() {
    }

    public GiveAway(int id, String name) {
        this.id = id;
        this.nameProperty.set(name);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return getNameProperty().get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.getNameProperty().set(name);
    }

    @Override
    public String toString() {
        return getNameProperty().get();
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }
}
