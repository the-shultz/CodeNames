package mta.jad.codenames.ui.app.components;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

public class CapacityLabel extends Label {

    private final StringProperty title = new SimpleStringProperty();
    private final IntegerProperty amount = new SimpleIntegerProperty(0);
    private final IntegerProperty total = new SimpleIntegerProperty(1);
    private final BooleanProperty full = new SimpleBooleanProperty(true);

    public CapacityLabel() {
        textProperty().bind(title.concat(" (").concat(amount).concat("/").concat(total).concat(")"));
        full.bind(total.isEqualTo(amount));
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

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getTotal() {
        return total.get();
    }

    public IntegerProperty totalProperty() {
        return total;
    }

    public void setTotal(int total) {
        this.total.set(total);
    }

    public boolean isFull() {
        return full.get();
    }

    public ReadOnlyBooleanProperty fullProperty() {
        return full;
    }
}
