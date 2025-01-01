package com.github.eldred777.dasts.stats.relics;

import com.github.eldred777.dasts.stats.Distribution;
import com.github.eldred777.dasts.stats.Viewable;
import com.github.eldred777.dasts.stats.relics.run.RunTable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class Relics extends Distribution<Integer, Double> implements Viewable {

    private RunTable runTable;

    public Relics() {
        super();
        runTable = new RunTable();
    }
    // TODO implement
    // TODO multiple people
    // TODO dynamically add lines based off # of runs and desired probability
    // Geometric distribution

    public Node view() {

        VBox mainNode = new VBox();
        HBox header = new HBox(); // header to modify settings etc

        // TODO # people
        ComboBox<Integer> squadSizeDropDown = new ComboBox<>();
        // TODO change internal value when combo box is edited
        // TODO set default


        // TODO different reward rarity
        ComboBox<String> rarityDropDown = new ComboBox<>();
        // TODO implement as above

        // TODO add run number
        TextField addRunField = new TextField();
        Button addRunButton = new Button();
        addRunButton.setOnAction(e -> {
            addRunField.getCharacters();
            String s = "";
            runTable.addRun(addRun(s));
        });

        // TODO remove run number
        Button removeRunButton = new Button();
        removeRunButton.setOnAction(e -> {
            // TODO use selected cell feature of TableView to remove item
        });

        header.getChildren().add(squadSizeDropDown);
        header.getChildren().add(rarityDropDown);
        header.getChildren().addAll(addRunField, addRunButton, removeRunButton);

        mainNode.getChildren().addAll(header, runTable.view());

        return mainNode;
    }

    /**
     * To be called on call to add run to RunTable via GUI input.
     *
     * @param strings
     *         API unstable, do not take this to be correct yet!
     *
     * @return int for run to be added to the table
     */
    int addRun(String strings) {
        // show error message on failure to parse additional run to be added etc.
        Alert alert = new Alert(Alert.AlertType.ERROR, "Bad content");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // DO STUFF
        }
        return 0; // TODO
    }
}
