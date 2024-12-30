package com.github.eldred777.dasts.stats.relics;

import com.github.eldred777.dasts.stats.Distribution;
import com.github.eldred777.dasts.stats.Viewable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        VBox node = new VBox();

        // header to modify internals
        HBox header = new HBox();
        // TODO # people
        // TODO different reward rarity
        // TODO add run number
        // TODO remove run number

        // set up calls to runTable
        // TODO

        node.getChildren().addAll(header, runTable.view());

        return node;
    }
}
