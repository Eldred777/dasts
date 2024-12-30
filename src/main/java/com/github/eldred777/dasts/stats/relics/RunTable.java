package com.github.eldred777.dasts.stats.relics;

import com.github.eldred777.dasts.stats.Viewable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RunTable implements Viewable {

    TableView<Run> runTable;
    int numPeople;
    RewardRarity rarity;
    DropChance[] dropChances;

    public RunTable() {
        runTable = new TableView<>();
        numPeople = 1;
        rarity = RewardRarity.COMMON;

        dropChances = new DropChance[4];
        dropChances[0] = DropChance.INTACT;
        dropChances[1] = DropChance.EXCEPTIONAL;
        dropChances[2] = DropChance.FLAWLESS;
        dropChances[3] = DropChance.RADIANT;
    }

    public void addRun(int n) {
        // TODO check if it exists, if not then add new run
        runTable.sort();
    }

    public Node view() {

        TableColumn<Run, Integer> runNumber = new TableColumn<>("Run Number");
        // TODO: determine how I actually want to display stats
        TableColumn<Run, Integer> intact = new TableColumn<>("Intact");
        intact.setMinWidth(100);
        intact.setCellValueFactory(new PropertyValueFactory<>("probability"));
        // TODO ^^^ what does this line actually do?

        TableColumn<Run, Integer> exceptional = new TableColumn<>("Exceptional");
        // TODO

        TableColumn<Run, Integer> flawless = new TableColumn<>("Flawless");
        // TODO

        TableColumn<Run, Integer> radiant = new TableColumn<>("Radiant");
        // TODO

        runTable.getColumns().addAll(runNumber, intact, exceptional, flawless, radiant);
        runTable.getSortOrder().add(runNumber);
        runTable.sort();

        return runTable;
    }
    // TODO
}
