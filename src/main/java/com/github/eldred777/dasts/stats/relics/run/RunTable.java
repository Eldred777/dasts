package com.github.eldred777.dasts.stats.relics.run;

import com.github.eldred777.dasts.stats.Viewable;
import com.github.eldred777.dasts.stats.relics.Refinement;
import com.github.eldred777.dasts.stats.relics.RewardRarity;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Responsible for updating and computing run statistics.
 */
public class RunTable implements Viewable {

    TableView<Run> runTable;
    int numPeople;
    RewardRarity rarity;

    public RunTable() {
        runTable = new TableView<>();
        numPeople = 1;
        rarity = RewardRarity.COMMON;
    }

    public void updateNumPeople(int n) {
        // todo verify n
        if (!(1 <= n && n <= 4)) {
            throw new IllegalArgumentException("Expected argument between 1 and 4 (inclusive) but got " + n);
        }
        this.numPeople = n;
    }

    public void updateRarity(@NotNull RewardRarity rarity) {
        this.rarity = rarity;
    }

    public void addRun(int n) {
        // TODO check if it exists, if not then add new run
        runTable.getItems().add(computeRun(n));
        runTable.sort();
    }

    public Run computeRun(int n) {
        return new Run(n, computeProbabilities(n));
    }

    /**
     * Computes geometric distribution pdf at n with probability parameter p
     */
    double computeProbability(double p, int n) {
        return 0.;
    }

    public Probabilities computeProbabilities(int n) {
        return new Probabilities(computeProbability(DropChance.INTACT.get(rarity), n), computeProbability(DropChance.EXCEPTIONAL.get(rarity), n), computeProbability(DropChance.FLAWLESS.get(rarity), n), computeProbability(DropChance.RADIANT.get(rarity), n));
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

        runTable.getColumns().addAll(runNumber, intact, exceptional, flawless, radiant); // TODO check this
        runTable.getSortOrder().add(runNumber);
        runTable.sort();

        return runTable;
    }
}
