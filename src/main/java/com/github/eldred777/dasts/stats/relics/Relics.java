package com.github.eldred777.dasts.stats.relics;

import com.github.eldred777.dasts.stats.Distribution;
import com.github.eldred777.dasts.stats.Viewable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

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
    // TODO dynamically add lines based off # of runs and desired probability

    public Node view() {

        VBox mainNode = new VBox();
        // TODO centre the header
        HBox header = new HBox(); // header to modify settings etc

        ComboBox<Integer> squadSizeDropDown = new ComboBox<>();
        squadSizeDropDown.getItems().addAll(1, 2, 3, 4);
        squadSizeDropDown.setOnAction(event -> {
            calculator.setNumPeople(squadSizeDropDown.getValue());
            updateAllRuns();
        });
        squadSizeDropDown.getSelectionModel().selectFirst(); // set default
        header.getChildren().add(squadSizeDropDown);

        // TODO: add horizontal space


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

        Spinner<Integer> addRunSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1);
        Button addRunButton = new Button("Add Run");
        addRunButton.setOnAction(e -> {
            addRunToTable(addRunSpinner.getValue());
        });
        addRunSpinner.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                addRunButton.fire();
            }
        });
        addRunSpinner.setOnScroll(e -> {
            var dy = e.getDeltaY();
            if (dy > 0) {
                addRunSpinner.increment();
            } else if (dy < 0) {
                addRunSpinner.decrement();
            }
        });

        // TODO remove run number logic
        Button removeRunButton = new Button("Delete Selected Run");
        removeRunButton.setOnAction(this::deleteRunCB);

        header.getChildren().addAll(addRunSpinner, addRunButton, removeRunButton);

        // Set up table
        TableColumn<Run, Integer> runNumber = new TableColumn<>("Run Number");
        runNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Run, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Run, Integer> param) {
                return param.getValue().getRunNumProperty().asObject();
            }
        });

        var intactCol = generateRefinementColumn(Refinement.INTACT);
        var exceptionalCol = generateRefinementColumn(Refinement.EXCEPTIONAL);
        var flawlessCol = generateRefinementColumn(Refinement.FLAWLESS);
        var radiantCol = generateRefinementColumn(Refinement.RADIANT);

        runTable.getColumns().add(runNumber);
        runTable.getColumns().add(intactCol);
        runTable.getColumns().add(exceptionalCol);
        runTable.getColumns().add(flawlessCol);
        runTable.getColumns().add(radiantCol);
        runTable.getSortOrder().add(runNumber);
        addRunToTable(1); // default
        runTable.sort();

        mainNode.getChildren().addAll(header, runTable);

        return mainNode;
    }

    private TableColumn<Run, Double> generateRefinementColumn(Refinement refinement) {
        TableColumn<Run, Double> col = new TableColumn<>(refinement.name);
        col.setCellValueFactory(value -> Bindings.valueAt(value.getValue().probabilities, refinement.index));
        col.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%4.2f", value) + "%");
                }
            }
        });
        return col;
    }

    void updateAllRuns() {
        for (Run run : runTable.getItems()) {
            calculator.updateRun(run);
        }
    }

    void addRunToTable(int n) {
        // Verify input1
        if (n > 0) {
            // TODO check if it exists, if not then add new run
            var items = runTable.getItems();
            if (items.stream().anyMatch(run -> run.getRunNum().equals(n))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "This run is already in the table.");
                var result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    return;
                }
            }
            runTable.getItems().add(calculator.computeRun(n));
            runTable.sort();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid range, please input a positive integer");
            var result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
    }

    void deleteRunCB(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Deleting runs is not implemented yet.");
        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return;
        }
    }
}
