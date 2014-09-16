package pl.jug.torun.meetup.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private String id;

    final private StringProperty nameProperty = new SimpleStringProperty();

    final private StringProperty statusProperty = new SimpleStringProperty();

    final private LongProperty timeProperty = new SimpleLongProperty();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return getNameProperty().get();
    }

    public void setName(String name) {
        this.getNameProperty().set(name);
    }

    public String getStatus() {
        return getStatusProperty().get();
    }

    public void setStatus(String status) {
        this.getStatusProperty().set(status);
    }

    public long getTime() {
        return getTimeProperty().get();
    }

    public void setTime(long time) {
        this.getTimeProperty().set(time);
    }

    @Override
    public String toString() {
        return getNameProperty().get();
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public StringProperty getStatusProperty() {
        return statusProperty;
    }

    public LongProperty getTimeProperty() {
        return timeProperty;
    }

}
