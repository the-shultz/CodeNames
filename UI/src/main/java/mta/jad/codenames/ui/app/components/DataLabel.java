package mta.jad.codenames.ui.app.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataLabel extends Label {

    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty data = new SimpleStringProperty();

    public DataLabel(){
        textProperty().bind(title.concat(": ").concat(data));
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getData() {
        return data.get();
    }

    public StringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }
}
