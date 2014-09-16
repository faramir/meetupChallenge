package pl.jug.torun.meetup.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member {

    final private StringProperty nameProperty = new SimpleStringProperty();

    @JsonProperty("member_id")
    private int memberId;

    public String getName() {
        return nameProperty.get();
    }

    public void setName(String name) {
        this.nameProperty.set(name);
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return nameProperty.get();
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

}
