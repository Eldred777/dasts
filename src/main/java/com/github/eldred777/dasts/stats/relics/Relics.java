package com.github.eldred777.dasts.stats.relics;

import com.github.eldred777.dasts.stats.Distribution;
import com.github.eldred777.dasts.stats.Viewable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * View statistics for Warframe relics.
 */
public class Relics extends Distribution<Integer, Double> implements Viewable {

    TableView<Run> runTable;
    Calculator calculator;

    public Relics() {
        super();
        runTable = new TableView<>();
        calculator = new Calculator();
    }
    // TODO implement
    // TODO multiple people
    // TODO dynamically add lines based off # of runs and desired probability

    public Node view() {

        VBox mainNode = new VBox();
        // TODO centre the header
        HBox header = new HBox(); // header to modify settings etc

        // TODO # people
        ComboBox<Integer> squadSizeDropDown = new ComboBox<>();
        // TODO change internal value when combo box is edited
        // TODO set default
        squadSizeDropDown.getItems().addAll(1, 2, 3, 4);
        squadSizeDropDown.setOnAction(event -> {
            calculator.setNumPeople(squadSizeDropDown.getValue());
            updateAllRuns();
        });
        squadSizeDropDown.getSelectionModel().selectFirst();
        header.getChildren().addAll(squadSizeDropDown);

        // TODO: add space here


        // ComboBox to set the desired reward rarity.
        ComboBox<RewardRarity> rarityDropDown = new ComboBox<>();
        rarityDropDown.getItems().addAll(RewardRarity.COMMON, RewardRarity.UNCOMMON, RewardRarity.RARE);
        rarityDropDown.setOnAction(event -> {
            calculator.setRarity(rarityDropDown.getValue());
            updateAllRuns();
        });
        rarityDropDown.getSelectionModel().selectFirst();
        header.getChildren().add(rarityDropDown);

        // TODO: add space here

        TextField addRunField = new TextField();
        Button addRunButton = new Button("Add Run");
        addRunButton.setOnAction(e -> {
            OptionalInt n = addRunCB(addRunField.getText());
            // If valid string parsed
            if (n.isPresent()) {
                addRunToTable(n.getAsInt());
            }
        });

        // TODO remove run number
        Button removeRunButton = new Button("Delete Selected Run");
        removeRunButton.setOnAction(e -> {
            // TODO use selected cell feature of TableView to remove item
        });

        header.getChildren().addAll(addRunField, addRunButton, removeRunButton);

        // Set up table
        TableColumn<Run, Integer> runNumber = new TableColumn<>("Run Number");
        runNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Run, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Run, Integer> param) {
                return param.getValue().getRunNumProperty().asObject();
            }
        });

        // Below are candidates for refactoring in terms of refinement enum.
        TableColumn<Run, Double> intactCol = new TableColumn<>("Intact");
        intactCol.setCellValueFactory(value -> Bindings.valueAt(value.getValue().probabilities, 0));
        // TODO: see if column accepts a formatter for the input; restrict to 2dp. Else put these as strings and add an intermediate observer that outputs a string.

        TableColumn<Run, Double> exceptionalCol = new TableColumn<>("Exceptional");
        exceptionalCol.setCellValueFactory(value -> Bindings.valueAt(value.getValue().probabilities, 1));

        TableColumn<Run, Double> flawlessCol = new TableColumn<>("Flawless");
        flawlessCol.setCellValueFactory(value -> Bindings.valueAt(value.getValue().probabilities, 2));

        TableColumn<Run, Double> radiantCol = new TableColumn<>("Radiant");
        radiantCol.setCellValueFactory(value -> Bindings.valueAt(value.getValue().probabilities, 3));

        runTable.getColumns().add(runNumber);
        runTable.getColumns().add(intactCol);
        runTable.getColumns().add(exceptionalCol);
        runTable.getColumns().add(flawlessCol);
        runTable.getColumns().add(radiantCol);
        runTable.getSortOrder().add(runNumber);
        addRunToTable(1);
        runTable.sort();

        mainNode.getChildren().addAll(header, runTable);

        return mainNode;
    }

    void updateAllRuns() {
        for (Run run : runTable.getItems()) {
            calculator.updateRun(run);
        }
    }

    /**
     * Callback to add run.
     *
     * @param string
     *         API unstable, do not take this to be correct yet!
     *
     * @return An OptionalInt, returning some integer to be added to the run table, or flagging an error with parsing by
     * returning nothing (i.e. an empty optional).
     */
    OptionalInt addRunCB(String string) {
        // Parse
        Alert alert = new Alert(Alert.AlertType.ERROR, "ADDING RUNS NOT IMPLEMENTED");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // DO STUFF
        }
        return OptionalInt.empty(); // TODO
    }

    void addRunToTable(int n) {       // TODO check if it exists, if not then add new run
        List<Double> probabilities = calculator.computeProbabilities(n);
        runTable.getItems().add(calculator.computeRun(n));
        runTable.sort();
    }
}
