package mta.jad.codenames.ui.app.components;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import mta.jad.codenames.ui.app.data.Color;
import mta.jad.codenames.ui.app.data.Word;

import java.io.IOException;
import java.util.Random;

public class GameBoard extends GridPane {

    private final IntegerProperty rows = new SimpleIntegerProperty(5);
    private final IntegerProperty cols = new SimpleIntegerProperty(5);

    public GameBoard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/component/GameBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void initialize() {
        for (int row = 0; row < rows.get(); row++) {
            for (int col = 0; col < cols.get(); col++) {
                Color[] enumValues = Color.values();
                int randomIndex = new Random().nextInt(enumValues.length);
                WordCard wordCard = new WordCard(new Word("Word "+row+" "+col, enumValues[randomIndex]));
                this.add(wordCard, col, row);
            }
        }

        for (int i = 0; i < cols.get(); i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHalignment(HPos.CENTER); // Set horizontal alignment to center
            columnConstraints.setHgrow(Priority.ALWAYS); // Allow the column to grow
            columnConstraints.setMaxWidth(Double.MAX_VALUE); // Set max width to Double.MAX_VALUE for no limit
            this.getColumnConstraints().add(columnConstraints); // Add the constraints to your GridPane

        }

        for (int i = 0; i < rows.get(); i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMaxHeight(Double.MAX_VALUE); // Set max height to Double.MAX_VALUE for no limit
            rowConstraints.setValignment(VPos.CENTER); // Set vertical alignment to center
            rowConstraints.setVgrow(Priority.ALWAYS); // Allow the row to grow
            this.getRowConstraints().add(rowConstraints); // Add the constraints to your GridPane
        }
    }

    public int getRows() {
        return rows.get();
    }

    public IntegerProperty rowsProperty() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows.set(rows);
    }

    public int getCols() {
        return cols.get();
    }

    public IntegerProperty colsProperty() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols.set(cols);
    }
}
